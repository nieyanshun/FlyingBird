package org.flying.bird.rpc;

import java.lang.reflect.InvocationHandler;


/**
 * @author nieyanshun
 *
 */
public interface Proxy {

    public <T> T newProxyInstance(ClassLoader classLoader, Class<T> clazzs,
            InvocationHandler handler);

}
