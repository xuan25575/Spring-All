package com.springboot.netty.tcp.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 *
 *    说明
 *    1. 我们自定义一个 Handler 需要继续 netty 规定好的某个 HandlerAdapter(规范)
 *    2. 这时我们自定义一个 Handler , 才能称为一个 handler
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] buffer = new byte[msg.readableBytes()];
        msg.readBytes(buffer);
        String content = new String(buffer, Charset.forName("utf-8"));
        System.out.println("服务端收到数据:" +content);
        System.out.println("服务端收到数据的次数:" + ++this.count);


        //服务器回送数据给客户端, 回送一个随机 id ,
       ByteBuf byteBuf =  Unpooled.copiedBuffer(UUID.randomUUID().toString()+"  ",Charset.forName("utf-8"));
       ctx.writeAndFlush(byteBuf);
    }


    /**
     * 异常处理 一般是需要关闭通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
