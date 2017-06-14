package org.flying.bird.rpc;

public class Invoker {
    public Object invoke() {
        System.out.println("Invoker successed.");
        return "Invoked.";
    }
}
