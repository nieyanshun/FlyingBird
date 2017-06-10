package org.flying.bird.spring.invoker;

import java.lang.reflect.Method;
import java.util.Map;

public class RpcInvocation implements Invocation {
    private Invocation invocation;
    private Invoker<?> invoker;
    private String methodName;

    private Object[] args;

    public RpcInvocation(Invocation invocation, Invoker<?> invoker) {
        this.invocation = invocation;
        this.invoker = invoker;
    }

    public RpcInvocation(Method method, Object[] arguments) {
        this.methodName = method.getName();
        this.args = arguments;
    }

    @Override
    public String getMethodName() {
        return (null == invocation) ? methodName : invocation.getMethodName();
    }

    @Override
    public Class<?>[] getParameterTypes() {
        return invocation.getParameterTypes();
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    @Override
    public Map<String, String> getAttachments() {
        return null;
    }

    @Override
    public String getAttachment(String key) {
        return null;
    }

    @Override
    public String getAttachment(String key, String defaultValue) {
        return null;
    }

    @Override
    public Invoker<?> getInvoker() {
        return this.invoker;
    }

}
