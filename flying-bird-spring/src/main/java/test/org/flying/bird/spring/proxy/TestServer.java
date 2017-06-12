package test.org.flying.bird.spring.proxy;

import org.flying.bird.remoting.server.NettyServer;
import org.flying.bird.spring.extension.DefaultRequestHandler;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import io.netty.channel.ChannelHandler;

public class TestServer {

    public static void main(String[] args) {


        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-server-test.xml");

        context.start();
        
        ChannelHandler[] handlers = new ChannelHandler[1];
        handlers[0] = new DefaultRequestHandler();
        NettyServer server = new NettyServer("127.0.0.1", 9000, handlers);
        server.bind();
        context.close();

    }

}
