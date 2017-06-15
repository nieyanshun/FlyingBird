package org.flying.bird.rpc.proxy;


import java.lang.reflect.InvocationHandler;
import org.flying.bird.rpc.Proxy;
import org.flying.bird.rpc.javassist.ProxyGenerator;

/**
 * @author nieyanshun
 *
 */
public class JavassistProxy implements Proxy {


    @SuppressWarnings("unchecked")
    public <T> T newProxyInstance(final ClassLoader classLoader, final Class<T> clazz,
            final InvocationHandler handler) {

        try {
            return (T) ProxyGenerator.newProxyInstance(classLoader, clazz, handler);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
