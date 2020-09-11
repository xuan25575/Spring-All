package com.springboot.netty.rpc;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServer {

    public static void startServer(String hostName, int port) {
        startServer0(hostName,port);
    }

    private static void startServer0(String hostName, int port) {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {

                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            // 业务处理
                            pipeline.addLast(new NettyServerHandler());
                        }
                    });

            ChannelFuture cf = bootstrap.bind(hostName, port).sync();
            cf.channel().closeFuture().sync();
            System.out.println("服务提供方开始提供服务~~");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
