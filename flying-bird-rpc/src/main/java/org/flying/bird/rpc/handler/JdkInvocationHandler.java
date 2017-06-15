package org.flying.bird.rpc.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.flying.bird.common.MethodArg;
import org.flying.bird.common.URL;
import org.flying.bird.remoting.Request;
import org.flying.bird.remoting.client.BirdClient;

public class JdkInvocationHandler implements InvocationHandler {


    private BirdClient client;

    private String className;

    public JdkInvocationHandler(BirdClient client, String className) {
        this.client = client;
        this.className = className;
    }

    @Override

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().equals("toString"))
            return "Proxy bean toString invoked.";

        if (method.getName().equals("hashCode")) {
            throw new RuntimeException("hashCode invoke not permitted on proxy bean.");
        }

        if (method.getName().equals("equals")) {
            throw new RuntimeException("equals invoke not permitted on proxy bean.");
        }

        final Request request = new Request();
        final Map<String, MethodArg> methodParams = new HashMap<>();

        Class<?>[] paramTypes = method.getParameterTypes();
        int index = 0;
        for (Class<?> param : paramTypes) {
            if (!(param.isAssignableFrom(args[index].getClass()))) {
                throw new RuntimeException("Method param class type exception.");
            }
            final MethodArg arg = new MethodArg(param.getName(), args[index]);
            methodParams.put(String.valueOf(index + 1), arg);
            index++;
        }

        final URL reqUrl = new URL.Builder().className(className).method(method.getName())
                .param(methodParams).build();
        request.setMsg(reqUrl);
        return client.request(request).get().getValue();

    }

    public BirdClient getClient() {
        return client;
    }
}
