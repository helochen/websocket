package org.demo.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

public class WebSocketInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new HttpServerCodec())
                .addLast(new HttpObjectAggregator(65535))
                .addLast(new WebSocketServerProtocolHandler("", "", false))
                .addLast(new DemoSimpleChannelInBoundHandler())
                .addLast(new LastSimpleChannelInBoundHandler())
        ;
    }
}
