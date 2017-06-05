package org.flying.bird.remoting;

import java.util.concurrent.atomic.AtomicInteger;
import org.flying.bird.protocol.Header;
import org.flying.bird.protocol.packet.MessageHeader;

public class Request implements Encodealbe {

    private static AtomicInteger REQUEST_ID = new AtomicInteger(0);

    private int requestId;

    private boolean isOneWay;

    private Object msg;

    public Request() {
        // getAndIncrement()增长到MAX_VALUE时，再增长会变为MIN_VALUE，负数也可以做为ID
        this.requestId = REQUEST_ID.getAndIncrement();
    }

    public int getRequestId() {
        return requestId;
    }

    public Request setRequestId(int requestId) {
        this.requestId = requestId;
        return this;
    }

    public boolean isOneWay() {
        return isOneWay;
    }

    public Request setOneWay(boolean isOneWay) {
        this.isOneWay = isOneWay;
        return this;
    }

    public Object getMsg() {
        return msg;
    }

    public Request setMsg(Object msg) {
        this.msg = msg;
        return this;
    }

//    public Header createRequestHeader() {
//        MessageHeader.Builder builder = new MessageHeader.Builder().serialId(getRequestId())
//                .timeStamp((int) (System.currentTimeMillis() / 1000));
//        return builder.buildReq();
//    }
}
