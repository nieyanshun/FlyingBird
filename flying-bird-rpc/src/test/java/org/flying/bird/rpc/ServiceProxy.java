package org.flying.bird.rpc;

public class ServiceProxy implements Service {

    @Override
    public void testProxy() {
        System.out.println("Service invoke.");
    }

}
