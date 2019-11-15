package com.fire.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class TimeClient {
    public void connect(int port, String host) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
        } finally {
            group.shutdownGracefully();
        }
    }
}
