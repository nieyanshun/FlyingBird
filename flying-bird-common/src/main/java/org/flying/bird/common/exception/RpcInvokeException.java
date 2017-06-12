package org.flying.bird.common.exception;

public class RpcInvokeException extends RuntimeException {

    private static final long serialVersionUID = -1430323691071524896L;

    public RpcInvokeException() {
        super();
    }

    public RpcInvokeException(String msg) {
        super(msg);
    }

    public RpcInvokeException(Throwable e) {
        super(e);
    }

    public RpcInvokeException(String msg, Throwable e) {
        super(msg, e);
    }

}
