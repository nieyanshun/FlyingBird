package org.flying.bird.remoting;

public class Response implements Encodealbe {

    private int responseId;

    private Object value;

    private Throwable e;

    public Object getValue() {
        return value;
    }

    public boolean hasException() {
        return (null == e);
    }

    public Throwable getException() {
        return e;
    }

    public int getResponseId() {
        return this.responseId;
    }

    public void setResponseId(int responseId) {
        this.responseId = responseId;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setE(Throwable e) {
        this.e = e;
    }
}
