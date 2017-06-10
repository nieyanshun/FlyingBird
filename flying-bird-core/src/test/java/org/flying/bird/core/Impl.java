package org.flying.bird.core;

public class Impl implements ProxyAble {

    @Override
    public void print() {
        System.out.println("Impl invoking...");
    }

    @Override
    public String print(String str) {
        System.out.println("Impl invoking..." + str);
        return "Successed";
    }

}
