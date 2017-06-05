package org.flying.bird.remoting;

import org.flying.bird.remoting.server.NettyServer;

public class TestServer {
    public static void main(String[] args) {
        NettyServer server = new NettyServer();
        server.bind();
    }
}
