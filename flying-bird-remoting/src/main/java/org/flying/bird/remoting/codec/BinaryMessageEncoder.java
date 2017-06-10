package org.flying.bird.remoting.codec;

import org.flying.bird.protocol.codec.BirdCodec;
import org.flying.bird.remoting.Encodealbe;
import org.flying.bird.remoting.Request;
import org.flying.bird.remoting.Response;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class BinaryMessageEncoder extends MessageToByteEncoder<Encodealbe> {

    private BirdCodec codec;

    public BinaryMessageEncoder() {
        super();
        codec = new BirdCodec();
    }


    @Override
    protected void encode(ChannelHandlerContext ctx, Encodealbe msg, ByteBuf out) throws Exception {
        byte[] data = null;
        try {
            if (msg instanceof Request) {
                int requestId = ((Request) msg).getRequestId();
                data = codec.encode(((Request) msg).getMsg(), requestId, true);
            } else if (msg instanceof Response) {
                int requestId = ((Response) msg).getResponseId();
                data = codec.encode(((Response) msg).getValue(), requestId, false);
            }
            out.writeBytes(data);
        } catch (Throwable t) {
            t.printStackTrace();
            ctx.close();
        }
    }

}
