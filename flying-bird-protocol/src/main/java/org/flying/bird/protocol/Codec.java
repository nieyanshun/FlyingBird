package org.flying.bird.protocol;

public interface Codec {

    Object decode(byte[] data);

    byte[] encode(Object obj, int requestId, boolean isReq);

    public static enum CodecType {
        REQUEST, RESPONSE
    }

}
