package org.flying.bird.protocol.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.flying.bird.protocol.exception.BirdDecodingException;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

public class HessianCodec extends BirdCodec {
	private static final Logger LOG = Logger.getLogger(HessianCodec.class);

	@Override
	public Object decodeBody(byte[] data) {
		if (null == data) {
			return null;
		}
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		HessianInput hessianIn = new HessianInput(in);
		Object body = null;
		try {
			body = hessianIn.readObject();
		} catch (IOException e) {
			throw new BirdDecodingException(e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
			}
			hessianIn.close();
		}
		return body;
	}

	@Override
	byte[] doEncode(Object body) throws IOException {
		if (null == body) {
			return null;
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		HessianOutput hessianOut = new HessianOutput(os);
		try {
			hessianOut.writeObject(body);
		} finally {
			os.close();
			hessianOut.close();
		}
		return os.toByteArray();
	}

}
