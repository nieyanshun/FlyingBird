package org.flying.bird.spring.invoker;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractProxyInvoker<T> implements Invoker<T> {

    private final T proxy;

    private final Class<T> type;

    public AbstractProxyInvoker(T proxy, Class<T> type) {
        if (proxy == null) {
            throw new IllegalArgumentException("proxy == null");
        }
        if (type == null) {
            throw new IllegalArgumentException("interface == null");
        }
        if (!type.isInstance(proxy)) {
            throw new IllegalArgumentException(
                    proxy.getClass().getName() + " not implement interface " + type);
        }
        this.proxy = proxy;
        this.type = type;
    }

    public Class<T> getInterface() {
        return type;
    }

    public boolean isAvailable() {
        return true;
    }

    public void destroy() {}

    public Object invoke(Invocation invocation) {
        try {
            doInvoke(proxy, invocation.getMethodName(), invocation.getParameterTypes(),
                    invocation.getArguments());
            return new Object();
        } catch (InvocationTargetException e) {
            return null;
        } catch (Throwable e) {
            throw new RuntimeException("Failed to invoke remote proxy method "
                    + invocation.getMethodName() + " to " + ", cause: " + e.getMessage(), e);
        }
    }

    protected abstract Object doInvoke(T proxy, String methodName, Class<?>[] parameterTypes,
            Object[] arguments) throws Throwable;

    @Override
    public String toString() {
        return getInterface() + " -> ";
    }


}
