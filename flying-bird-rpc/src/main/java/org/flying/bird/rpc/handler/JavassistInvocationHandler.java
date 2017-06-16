package org.flying.bird.rpc.handler;

import org.flying.bird.remoting.client.BirdClient;

/**
 * @author nieyanshun
 *
 */
public class JavassistInvocationHandler extends JdkInvocationHandler {

    public JavassistInvocationHandler(BirdClient client, String className) {
        super(client, className);
    }

}
