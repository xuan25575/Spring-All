package com.springboot.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 实例要求:使用IDEA创建Netty项目
 * 2) Netty 服务器在 6668 端口监听，浏览器发出请求 "http://localhost:6888/ "
 * 3) 服务器可以回复消息给客户端 "Hello! 我是服务器 5 " , 并对特定请求资源进行过滤.
 */
public class TestServer {


    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            //配置
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());

            // netty 绑定端口并启动过
            ChannelFuture channelFuture = serverBootstrap.bind(6888).sync();
            channelFuture.channel().closeFuture().sync();

        }finally {

            //优雅的关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }



    }

}
