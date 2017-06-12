package org.flying.bird.spring.extension;

import java.lang.reflect.Method;
import java.util.Map;
import org.flying.bird.common.MethodArg;
import org.flying.bird.common.Ret;
import org.flying.bird.common.URL;
import org.flying.bird.common.exception.RpcInvokeException;
import org.flying.bird.protocol.packet.BirdMessage;
import org.flying.bird.remoting.Response;
import io.netty.channel.ChannelHandlerContext;

/**
 * 默认服务端Request处理器
 * 
 * @author nieyanshun
 *
 */
public class DefaultRequestHandler extends AbstractProcessHandler {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, BirdMessage msg) throws Exception {
        if (msg.body() instanceof URL) {
            final URL url = (URL) msg.body();
            final int requestId = msg.getHeader().serialId();
            String serviceName = url.getClassName();
            String method = url.getMethod();
            Map<String, MethodArg> param = url.getParam();
            Object result = null;
            final Response response = new Response();
            response.setResponseId(requestId);
            response.setRet(Ret.SUCCESS);
            try {
                result = invokeService(serviceName, method, param);
            } catch (Exception e) {
                e.printStackTrace();
                response.setException(e);
                response.setRet(Ret.ERROR);
            }
            if (null != result) {
                response.setValue(result);
            }
            ctx.writeAndFlush(response);
        }
    }

    @Override
    Object doInvoke(final String className, final String method, Map<String, MethodArg> param) {
        Object service = null;
        try {
            service = getApplicationContext().getService(className);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            final Class<?> clazz = classLoader.loadClass(className);
            final int paramSzie = param.keySet().size();
            final Class<?>[] parameterTypes = new Class[paramSzie];
            final Object[] args = new Object[paramSzie];
            for (int i = 0; i < paramSzie; i++) {
                final MethodArg methodArg = param.get(String.valueOf(i + 1));
                parameterTypes[i] = classLoader.loadClass(methodArg.getType());
                args[i] = methodArg.getValue();
            }
            Method m = clazz.getMethod(method, parameterTypes);
            if (m.getReturnType() != null) {
                return m.invoke(service, args);
            }
            m.invoke(service, args);

        } catch (Exception e) {
            throw new RpcInvokeException(e);
        }
        return null;
    }

}
