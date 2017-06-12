package org.flying.bird.spring.extension;

import java.lang.reflect.Method;
import java.util.Map;

import org.flying.bird.common.MethodArg;
import org.flying.bird.protocol.packet.BirdMessage;
import org.flying.bird.spring.Context;
import org.flying.bird.spring.ContextFactory;

import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 扩展请求处理父类
 * 
 * @author nieyanshun
 *
 */
public abstract class AbstractProcessHandler extends SimpleChannelInboundHandler<BirdMessage> {

    private Context applicationContext;

    Object invokeService(final String className, final String method,
            Map<String, MethodArg> param) {
        preInvoke();
        return doInvoke(className, method, param);
    }

    abstract Object doInvoke(final String className, final String method,
            Map<String, MethodArg> param);

    protected void preInvoke() {
        applicationContext = ContextFactory.getContext();
    }

    public Context getApplicationContext() {
        return applicationContext;
    }
}
