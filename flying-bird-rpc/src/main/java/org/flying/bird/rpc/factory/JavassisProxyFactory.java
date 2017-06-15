package org.flying.bird.rpc.factory;

import org.flying.bird.rpc.Proxy;
import org.flying.bird.rpc.ProxyFactory;
import org.flying.bird.rpc.proxy.JavassistProxy;

/**
 * @author nieyanshun
 *
 */
public class JavassisProxyFactory implements ProxyFactory {

    private static final Proxy PROXY = new JavassistProxy();

    @Override
    public Proxy getProxy() {
        return PROXY;
    }

}
