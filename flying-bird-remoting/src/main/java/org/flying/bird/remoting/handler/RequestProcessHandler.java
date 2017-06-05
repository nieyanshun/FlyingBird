package org.flying.bird.remoting.handler;

import org.flying.bird.protocol.packet.BirdMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RequestProcessHandler extends SimpleChannelInboundHandler<BirdMessage> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, BirdMessage msg) throws Exception {
        System.out.println(msg);
        // ctx.channel().writeAndFlush("Recived!");
    }
}
