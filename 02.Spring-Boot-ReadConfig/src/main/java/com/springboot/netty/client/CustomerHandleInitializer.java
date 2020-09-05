package com.springboot.netty.client;

import com.springboot.netty.codec.HeartbeatEncode;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;

// 初始化 handler
public class CustomerHandleInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                //10 秒没发送消息 将IdleStateHandler 添加到 ChannelPipeline 中
                .addLast(new IdleStateHandler(0, 10, 0))
                .addLast(new HeartbeatEncode())
                .addLast(new EchoClientHandle());
    }

}
