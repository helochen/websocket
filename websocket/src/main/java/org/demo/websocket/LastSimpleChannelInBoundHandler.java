package org.demo.websocket;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

@ChannelHandler.Sharable
public class LastSimpleChannelInBoundHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("last...........");
        if (msg instanceof TextWebSocketFrame) {
            String info = ((TextWebSocketFrame) msg).text();
            System.out.println(info);

            ctx.writeAndFlush(new TextWebSocketFrame("last:" + info));
        }
    }
}
