package org.demo.websocket;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

@ChannelHandler.Sharable
public class DemoSimpleChannelInBoundHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("read.............");
        if (msg instanceof TextWebSocketFrame) {
            String info = ((TextWebSocketFrame) msg).text();
            System.out.println(info);
            StringBuilder sb = new StringBuilder();
            sb.append("echo:").append(info);
            ctx.write(new TextWebSocketFrame(sb.toString()));
            ((TextWebSocketFrame) msg).retain();
        }
        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler active........");
        super.channelActive(ctx);
    }
}
