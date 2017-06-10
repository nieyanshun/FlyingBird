package org.flying.bird.remoting.handler;


import org.flying.bird.protocol.packet.BirdMessage;
import org.flying.bird.remoting.Response;
import org.flying.bird.remoting.future.ResponseFuture;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ResponseProcessorHandler extends SimpleChannelInboundHandler<BirdMessage> {


    @Override
    protected void messageReceived(ChannelHandlerContext ctx, BirdMessage msg) throws Exception {
        Object body = msg.body();
        Response rep = new Response();
        rep.setValue(body);
        rep.setResponseId(msg.getHeader().serialId());
        ResponseFuture.responseRecived(rep);
    }
}
