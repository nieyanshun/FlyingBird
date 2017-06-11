package org.flying.bird.remoting.handler;

import java.util.Map;

import org.flying.bird.common.MethodArg;
import org.flying.bird.common.URL;
import org.flying.bird.protocol.packet.BirdMessage;
import org.flying.bird.remoting.Response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RequestProcessHandler extends SimpleChannelInboundHandler<BirdMessage> {


    @Override
    protected void messageReceived(ChannelHandlerContext ctx, BirdMessage msg) throws Exception {
        System.out.println(msg);

        Object body = msg.body();
        if (body instanceof URL) {
            URL url = (URL) body;
            final String serviceInterface = url.getClassName();
            final String methodName = url.getMethod();
            final Map<String, MethodArg> arg = url.getParam();
            Response rep = new Response();
            rep.setResponseId(msg.getHeader().serialId());
            rep.setValue("Rpc invoke success.");
            ctx.writeAndFlush(rep);

        }

        // ctx.channel().writeAndFlush("Recived!");
    }
}
