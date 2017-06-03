package org.flying.bird.protocol.packet;

import org.flying.bird.protocol.Header;
import org.flying.bird.protocol.Message;

public class BirdMessage implements Message {

    private Header header;

    private Object body;

    public BirdMessage(Header header, Object body) {
        this.header = header;
        this.body = body;
    }

    @Override
    public Header getHeader() {
        return header;
    }

    @Override
    public Object body() {
        return body;
    }

    @Override
    public String toString() {
        return "BirdMessage [header=" + header + ", body=" + body + "]";
    }

}
