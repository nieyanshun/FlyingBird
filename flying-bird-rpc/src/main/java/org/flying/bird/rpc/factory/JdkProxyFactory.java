package org.flying.bird.rpc.factory;

import org.flying.bird.rpc.Proxy;
import org.flying.bird.rpc.ProxyFactory;
import org.flying.bird.rpc.proxy.JdkProxy;

/**
 * @author nieyanshun
 *
 */
public class JdkProxyFactory implements ProxyFactory {
    private static final Proxy PROXY = new JdkProxy();

    @Override
    public Proxy getProxy() {
        return PROXY;
    }

}
