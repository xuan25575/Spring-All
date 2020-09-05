package com.springboot.netty.webSocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;


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
                            //因为基于 http 协议，使用 http 的编码和解码器
                            pipeline.addLast(new HttpServerCodec());
                            //是以块方式写，添加 ChunkedWriteHandler 处理器
                            pipeline.addLast(new ChunkedWriteHandler());

                            /**
                             * 说明
                             * 1. http 数据在传输过程中是分段, HttpObjectAggregator ，就是可以将多个段聚合
                             * 2. 这就就是为什么，当浏览器发送大量数据时，就会发出多次 http 请求
                             */
                            pipeline.addLast(new HttpObjectAggregator(8192));

                            /**
                             * 说明
                             * 1. 对应 websocket ，它的数据是以 帧(frame) 形式传递
                             * 2. 可以看到 WebSocketFrame 下面有六个子类
                             * 3. 浏览器请求时 ws://localhost:7000/hello 表示请求的 uri
                             * 4. WebSocketServerProtocolHandler 核心功能是将 http 协议升级为 ws 协议 , 保持长连接
                             * 5. 是通过一个 状态码 101
                             * 6。WebSocket是HTML5中的协议，支持持久连接；而Http协议不支持持久连接。
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));

                            //自定义的 handler ，处理业务逻辑
                            pipeline.addLast(new MyTextWebSocketFrameHandler());
                        }
                    });


            //启动服务器
            ChannelFuture cf = bootstrap.bind(7000).sync();
            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
