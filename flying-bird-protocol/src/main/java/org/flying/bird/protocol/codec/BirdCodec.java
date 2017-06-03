package org.flying.bird.protocol.codec;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.log4j.Logger;
import org.flying.bird.protocol.Codec;
import org.flying.bird.protocol.Header;
import org.flying.bird.protocol.Message;
import org.flying.bird.protocol.exception.BirdDecodingException;
import org.flying.bird.protocol.exception.BirdEncodingException;
import org.flying.bird.protocol.exception.UnkonwnMagicCodeException;
import org.flying.bird.protocol.packet.BirdMessage;
import org.flying.bird.protocol.packet.MessageHeader;

/**
 *
 * @author nieyanshun
 *
 */
public abstract class BirdCodec implements Codec {

	private static final Logger LOG = Logger.getLogger(BirdCodec.class);

	/**
	 * getAndIncrement()增长到MAX_VALUE时，再增长会变为MIN_VALUE，负数也可以做为ID
	 */
	private AtomicInteger serialCounter = new AtomicInteger(0);

	byte[] encodeHeader(Header header) {

		final byte[] headerPacket = new byte[Header.HEADER_LENGTH];

		headerPacket[0] = Header.MAGIC >> 8;

		if (header.isReq()) {
			headerPacket[1] = (byte) (Header.MAGIC & Header.REQ_MASK & 0x00FF);
		} else if (header.isRet()) {
			headerPacket[1] = (byte) (Header.MAGIC & Header.RET_MASK & 0x00FF);
		} else {
			throw new UnkonwnMagicCodeException();
		}

		final byte version = header.version();
		final byte ext = header.ext();
		final int serialId = header.serialId();
		final int timeStamp = header.timeStamp();
		final int bodyLength = header.bodyLength();

		headerPacket[2] = version;
		headerPacket[3] = ext;
		headerPacket[4] = (byte) (serialId >> 24);
		headerPacket[5] = (byte) (serialId >> 16);
		headerPacket[6] = (byte) (serialId >> 8);
		headerPacket[7] = (byte) (serialId);

		headerPacket[8] = (byte) (timeStamp >> 24);
		headerPacket[9] = (byte) (timeStamp >> 16);
		headerPacket[10] = (byte) (timeStamp >> 8);
		headerPacket[11] = (byte) (timeStamp);

		headerPacket[12] = (byte) (bodyLength >> 24);
		headerPacket[13] = (byte) (bodyLength >> 16);
		headerPacket[14] = (byte) (bodyLength >> 8);
		headerPacket[15] = (byte) (bodyLength);

		return headerPacket;
	}

	public Header decodeHeader(byte[] data) throws IOException {
		if (null == data || data.length < Header.HEADER_LENGTH)
			throw new BirdDecodingException();

		DataInputStream in = null;
		try {
			in = new DataInputStream(new ByteArrayInputStream(data));
			final short magic = in.readShort();

			boolean isReq = false;
			if (magic == Header.REQ_CODE) {
				isReq = true;
			} else if (magic != Header.RET_CODE) {
				throw new UnkonwnMagicCodeException();
			}

			final byte version = in.readByte();
			final byte ext = in.readByte();
			final int serialId = in.readInt();
			final int timeStamp = in.readInt();
			final int bodyLength = in.readInt();

			MessageHeader.Builder builder = new MessageHeader.Builder().ext(ext).version(version).serialId(serialId)
					.timeStamp(timeStamp).bodyLength(bodyLength);

			return (isReq ? builder.buildReq() : builder.buildRet());

		} finally {
			in.close();
		}

	}

	@Override
	public Message decode(byte[] data) {
		if (null == data)
			return null;
		Header header = null;
		try {
			header = decodeHeader(data);
		} catch (IOException e) {
			LOG.error(e);
		}

		int length = Header.HEADER_LENGTH + header.bodyLength();
		if (data.length != length) {
			throw new BirdDecodingException("Binary data broken.");
		}
		byte[] body = new byte[header.bodyLength()];
		System.arraycopy(data, Header.HEADER_LENGTH, body, 0, header.bodyLength());

		try {
			return new BirdMessage(header, decodeBody(body));

		} catch (IOException e) {
			throw new BirdDecodingException(e.getMessage());
		}

	}

	/**
	 * 对body报文进行解码
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public abstract Object decodeBody(byte[] data) throws IOException;

	abstract byte[] doEncode(Object msg) throws IOException;

	@Override
	public byte[] encode(Object msg, CodecType type) {
		byte[] body;
		try {
			body = doEncode(msg);
		} catch (IOException e) {
			throw new BirdEncodingException();
		}

		byte[] header = encodeHeader(createHeader(type, body.length));
		byte[] binaryMsg = new byte[Header.HEADER_LENGTH + body.length];
		System.arraycopy(header, 0, binaryMsg, 0, header.length);
		System.arraycopy(body, 0, binaryMsg, Header.HEADER_LENGTH, body.length);

		return binaryMsg;
	}

	private Header createHeader(CodecType type, int bodyLength) {

		MessageHeader.Builder builder = new MessageHeader.Builder().serialId(serialCounter.getAndIncrement())
				.timeStamp((int) (System.currentTimeMillis() / 1000)).bodyLength(bodyLength);
		return (CodecType.REQUEST == type) ? builder.buildReq() : builder.buildRet();
	}

}