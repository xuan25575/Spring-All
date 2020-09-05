package com.springboot.netty.heartbeat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * Springboot 整合心跳机制
 * 实现逻辑说明:
 * 1.spring boot 配置netty server相关的配置
 *  -1  类 IdleStateHandler 处理读写空闲的事件
 *  -2  解码类 HeartbeatDecoder
 *  -3  HeartBeatSimpleHandle 业务处理，服务端 5秒没有读请求，会发送一个心跳格式包（暂且这么说）
 *  -4  NettySocketHolder 管理客户端连接
 * 2. 在另一个机器或者代码上 配置netty client 配置
 *  -1 类 IdleStateHandler 处理读写空闲的事件
 *  -2 编码类 HeartbeatEncode
 *  -3 EchoClientHandle 客户端10秒没有写请求，发送一个心跳包
 *
 * 整合自定义端点，或者 actuator
 *
 */
@Component
public class HeartBeatServer {

    /** logger. */
    private Logger logger = LoggerFactory.getLogger(HeartBeatServer.class);
    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup work = new NioEventLoopGroup();


    @Value("${netty.server.port}")
    private int nettyPort;

    /**
     * 启动 Netty，@PostConstruct注解的方法将会在依赖注入完成后被自动调用。
     * @return
     * @throws InterruptedException
     */
    @PostConstruct
    public void start() throws InterruptedException {

        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(nettyPort))
                //保持长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new HeartbeatInitializer());

        ChannelFuture future = bootstrap.bind().sync();
        if (future.isSuccess()) {
            logger.info("启动 Netty 成功");
        }
    }


    /**
     * 销毁
     */
    @PreDestroy
    public void destroy() {
        boss.shutdownGracefully().syncUninterruptibly();
        work.shutdownGracefully().syncUninterruptibly();
        logger.info("关闭 Netty 成功");
    }
}
