package org.flying.bird.remoting.codec;

import java.util.List;
import org.flying.bird.protocol.Header;
import org.flying.bird.protocol.codec.BirdCodec;
import org.flying.bird.protocol.packet.BirdMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class BinaryMessageDecoder extends ByteToMessageDecoder {

    private BirdCodec codec;

    private static final int HEADER_LENGTH = Header.HEADER_LENGTH;

    public BinaryMessageDecoder() {
        super();
        codec = new BirdCodec();
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
            throws Exception {
        if (!in.isReadable())
            return;
        if (in.readableBytes() < HEADER_LENGTH)
            return;
        final byte[] headerData = new byte[HEADER_LENGTH];
        in.readBytes(headerData, 0, HEADER_LENGTH);
        Header header = codec.decodeHeader(headerData);
        final int bodyLength = header.bodyLength();

        if (in.readableBytes() < bodyLength) {
            in.resetReaderIndex();
            return;
        }
        final byte[] bodyArr = new byte[bodyLength];
        in.readBytes(bodyArr, 0, bodyLength);
        Object body = codec.decodeBody(bodyArr);
        out.add(new BirdMessage(header, body));
    }

}
