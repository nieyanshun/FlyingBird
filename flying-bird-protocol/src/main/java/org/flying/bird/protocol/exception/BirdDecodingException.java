package org.flying.bird.protocol.exception;

public class BirdDecodingException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BirdDecodingException() {

    }

    public BirdDecodingException(String msg) {
        super(msg);
    }

    public BirdDecodingException(Throwable t) {
        super(t);
    }

}
