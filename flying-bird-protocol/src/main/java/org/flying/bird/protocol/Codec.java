package org.flying.bird.protocol;

public interface Codec {

	Object decode(byte[] data);

	byte[] encode(Object obj, CodecType type);

	public static enum CodecType {
		REQUEST, RESPONSE
	}

}
