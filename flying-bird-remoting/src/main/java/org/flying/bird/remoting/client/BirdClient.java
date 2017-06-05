package org.flying.bird.remoting.client;


import org.apache.log4j.Logger;
import org.flying.bird.remoting.Client;
import org.flying.bird.remoting.Request;
import org.flying.bird.remoting.codec.BinaryMessageDecoder;
import org.flying.bird.remoting.codec.BinaryMessageEncoder;
import org.flying.bird.remoting.exception.RemotingException;
import org.flying.bird.remoting.future.ResponseFuture;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class BirdClient implements Client {
    private static Logger LOG = Logger.getLogger(BirdClient.class);

    private static final String DEFAULT_HOST = "127.0.0.1";

    private static int PORT = 9000;

    private String host;

    private int port;

    private Channel channel;

    private EventLoopGroup group = new NioEventLoopGroup();

    private long timeOut;

    public BirdClient() {
        this.host = DEFAULT_HOST;
        this.port = PORT;
        this.timeOut = 1000L;
    }

    public BirdClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.timeOut = 1000L;
    }

    public BirdClient(String host, int port, long timeOut) {
        this.host = host;
        this.port = port;
        this.timeOut = timeOut;
    }


    @Override
    public void connect() {
        Bootstrap bootStrap = new Bootstrap();
        try {
            bootStrap.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeLine = ch.pipeline();
                            pipeLine.addLast(new BinaryMessageDecoder())
                                    .addLast(new BinaryMessageEncoder());
                        }
                    });
            ChannelFuture f = bootStrap.connect(getHost(), getPort()).sync();
            channel = f.channel();
            // channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // group.shutdownGracefully();
        }
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public Channel getChannel() {
        return this.channel;
    }

    @Override
    public void send(Request req) throws RemotingException {
        System.out.println(req);
        if (!req.isOneWay()) {
            LOG.warn("Req : " + req
                    + "will not recive a response msg .Make sure you really want that.");
        }
        // if (!getChannel().isWritable()) {
        // throw new RemotingException("Channel unwritable.");
        // }
        getChannel().writeAndFlush(req);
    }

    @Override
    public ResponseFuture request(Request request) throws RemotingException {
        ResponseFuture f = new ResponseFuture();
        ResponseFuture.addFuture(f, request.getRequestId());
        getChannel().writeAndFlush(request);
        return f;
    }

    @Override
    public void disConnect() {
        try {
            channel.close();
            group.shutdownGracefully();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
