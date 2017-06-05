package org.flying.bird.remoting.future;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.flying.bird.remoting.Response;
import io.netty.channel.Channel;

public class ResponseFuture implements Future {

    private static Map<Integer, ResponseFuture> FUTURE_GROUP = new ConcurrentHashMap<>();

    // private Channel channel;

    private Response response;

    private Object lock = new Object();

    private static long TIME_OUT = 1000L;

    @Override
    public Object get() throws InterruptedException {
        return get(TIME_OUT, TimeUnit.MILLISECONDS);
    }

    @Override
    public Object get(long timeOut, TimeUnit unit) throws InterruptedException {
        if ((unit != TimeUnit.SECONDS) && (unit != TimeUnit.MILLISECONDS))
            throw new RuntimeException("Only TimeUnit.SECONDS or TimeUnit.MILLISECONDS allowed.");
        if (timeOut < 0)
            timeOut = 1000L;
        if (unit == TimeUnit.SECONDS)
            timeOut = timeOut * 1000;
        long start = System.currentTimeMillis();
        if (!isDone()) {
            synchronized (lock) {
                if (!isDone()) {
                    wait(timeOut);

                    long end = System.currentTimeMillis();

                    if ((end - start) >= timeOut) {
                        throw new RuntimeException("get Timeout.");
                    }

                }
            }
        }
        return response;
    }

    @Override
    public void addCallBack(CallBack call) {
        throw new UnsupportedOperationException();
    }

    public static void addFuture(ResponseFuture f, int id) {
        FUTURE_GROUP.putIfAbsent(id, f);
    }

    public static void responseRecived(Response response) {
        if (null == FUTURE_GROUP.get(response.getResponseId())) {
            throw new RuntimeException("RequestId " + response.getResponseId() + " not found!");
        }
        ResponseFuture f = FUTURE_GROUP.remove(response.getResponseId());
        if (null != f) {
            f.doRecived(response);
        }
    }

    public void setChannel(Channel channel) {
        // this.channel = channel;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    private boolean isDone() {
        return (null != response);
    }

    void doRecived(Response response) {
        this.response = response;
        this.lock.notifyAll();
    }
}
