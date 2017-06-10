package org.flying.bird.spring.invoker;

public abstract class AbstractProxyFactory implements ProxyFactory {

    public <T> T getProxy(Invoker<T> invoker) {
        return getProxy(invoker, null);
    }

    public abstract <T> T getProxy(Invoker<T> invoker, Class<?>[] types);

}
