package org.flying.bird.remoting.server;

import org.flying.bird.remoting.Server;
import org.flying.bird.remoting.codec.BinaryMessageDecoder;
import org.flying.bird.remoting.codec.BinaryMessageEncoder;
import org.flying.bird.remoting.handler.InboundExceptionHandler;
import org.flying.bird.remoting.handler.RequestProcessHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer implements Server {

    private static String ADDRESS = "127.0.0.1";

    private String host;
    private int port;

    public NettyServer() {
        host = ADDRESS;
        port = 9000;
    }


    public NettyServer(String host, int port) {
        this.host = host;
        this.port = port;
    }


    @Override
    public void bind() {
        try {
            doBind();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void doBind() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootStrap = new ServerBootstrap();
        try {
            bootStrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeLine = ch.pipeline();
                            pipeLine.addLast(new BinaryMessageDecoder())
                                    .addLast(new BinaryMessageEncoder())
                                    .addLast(new InboundExceptionHandler())
                                    .addLast(new RequestProcessHandler());
                        }
                    });
            ChannelFuture f = bootStrap.bind(getHost(), getPort()).sync();
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    public String getHost() {
        return host;
    }


    public int getPort() {
        return port;
    }



}
