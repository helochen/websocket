package org.demo.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class WebSocketDemo {


    public static void main(String[] args) throws InterruptedException {


        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, work).channel(NioServerSocketChannel.class).localAddress(1024)
                    .childHandler(new WebSocketInitializer())
            ;

            ChannelFuture future = serverBootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully().sync();
            work.shutdownGracefully().sync();
        }

    }

}
