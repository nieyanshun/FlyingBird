package org.flying.bird.remoting.future;

import java.util.concurrent.TimeUnit;

public interface Future {
    
    Object get() throws InterruptedException;

    Object get(long timeOut, TimeUnit unit) throws InterruptedException;

    void addCallBack(CallBack call);

    public interface CallBack {
        void callBack();
    }
}
