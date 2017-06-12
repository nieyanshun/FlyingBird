package org.flying.bird.remoting.handler;

import org.apache.log4j.Logger;
import org.flying.bird.protocol.packet.BirdMessage;
import org.flying.bird.remoting.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Echo processor
 * 
 * @author nieyanshun
 *
 */
public class RequestEchoProcessHandler extends SimpleChannelInboundHandler<BirdMessage> {
    private static final Logger LOG = Logger.getLogger(RequestEchoProcessHandler.class);

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, BirdMessage msg) throws Exception {
        System.out.println(msg);
        LOG.info(msg);
        Response rep = new Response();
        rep.setResponseId(msg.getHeader().serialId());
        rep.setValue("Rpc invoke success.");
        ctx.writeAndFlush(rep);
    }
}
