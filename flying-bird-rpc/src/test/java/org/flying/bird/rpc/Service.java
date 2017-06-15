package org.flying.bird.rpc;

public interface Service {
    void testProxy();
    
    String test(String arg);
    
    String test(String[] arg);

}
