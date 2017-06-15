package org.flying.bird.rpc.handler;

import org.flying.bird.remoting.client.BirdClient;

/**
 * @author nieyanshun
 *
 */
public class JavassistinvocationHandler extends JdkInvocationHandler {

    public JavassistinvocationHandler(BirdClient client, String className) {
        super(client, className);
    }

}
