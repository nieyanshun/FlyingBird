package org.flying.bird.rpc.proxy;

import java.lang.reflect.InvocationHandler;

import org.flying.bird.rpc.Proxy;

/**
 * @author nieyanshun
 *
 */
public class JdkProxy implements Proxy {

    @SuppressWarnings("unchecked")
    @Override
    public <T> T newProxyInstance(ClassLoader classLoader, final Class<T> clazz,
            final InvocationHandler handler) {
        final Class<?>[] classes = new Class[1];
        classes[0] = clazz;
        return (T) java.lang.reflect.Proxy.newProxyInstance(classLoader, classes, handler);

    }
}
