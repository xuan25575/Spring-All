package com.springboot.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 心跳机制检测案例.
 * 需要客户端连接进来。
 */
public class MyServer {

    public static void main(String[] args) throws Exception {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    //输出日志
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            /**
                             * 加入一个 netty 提供 IdleStateHandler
                             * 1. IdleStateHandler 是 netty 提供的处理空闲状态的处理器
                             * 2. long readerIdleTime : 表示多长时间没有读, 就会发送一个心跳检测包检测是否连接
                             * 3. long writerIdleTime : 表示多长时间没有写, 就会发送一个心跳检测包检测是否连接
                             * 4. long allIdleTime : 表示多长时间没有读写, 就会发送一个心跳检测包检测是否连接
                             * 5. 文档说明
                             *  1.triggers an {@link IdleStateEvent} when a {@link Channel}
                             *  has not performed read, write, or both operation for a while.
                             * 6. 当 IdleStateEvent触发后 , 就会传递给管道的下一个 handler 去处理
                             *  通过调用(触发)下一个 handler 的 userEventTriggered , 在该方法中去处理 IdleStateEvent(读 空闲，写空闲，读写空闲)
                             */
                            pipeline.addLast(new IdleStateHandler(13, 5, 2, TimeUnit.SECONDS));
                            pipeline.addLast(new MyServerHandler());
                        }
                    });


            //启动服务器
            ChannelFuture cf = bootstrap.bind(6668).sync();
            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
