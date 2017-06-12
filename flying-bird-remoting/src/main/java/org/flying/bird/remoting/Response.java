package org.flying.bird.remoting;

public class Response implements Encodealbe {

    private transient int responseId;

    private Object value;

    private Throwable exception;

    private int ret;

    private String msg;

    public int getResponseId() {
        return responseId;
    }

    public Response setResponseId(int responseId) {
        this.responseId = responseId;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public Response setValue(Object value) {
        this.value = value;
        return this;
    }

    public Throwable getException() {
        return exception;
    }

    public Response setException(Throwable exception) {
        this.exception = exception;
        return this;
    }

    public int getRet() {
        return ret;
    }

    public Response setRet(int ret) {
        this.ret = ret;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Response setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
