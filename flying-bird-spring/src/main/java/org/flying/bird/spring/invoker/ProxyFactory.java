package org.flying.bird.spring.invoker;

public interface ProxyFactory {
    /**
     * create proxy.
     * 
     * @param invoker
     * @return proxy
     */
    <T> T getProxy(Invoker<T> invoker);

    /**
     * create invoker.
     * 
     * @param <T>
     * @param proxy
     * @param type
     * @param url
     * @return invoker
     */
    <T> Invoker<T> getInvoker(T proxy, Class<T> type);

}
