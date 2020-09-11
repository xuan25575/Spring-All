package com.springboot.netty.tcp.solution;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 *
 *    说明
 *    1. 我们自定义一个 Handler 需要继续 netty 规定好的某个 HandlerAdapter(规范)
 *    2. 这时我们自定义一个 Handler , 才能称为一个 handler
 */
public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {


    private int count;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
    //接收到数据，并处理
        int len = msg.getLen();
        byte[] content  = msg.getContent();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("服务器接收到信息如下");
        System.out.println("长度=" + len);
        System.out.println("内容=" + new String(content, Charset.forName("utf-8")));
        System.out.println("服务器接收到消息包数量=" + (++this.count));


        //服务器回送数据给客户端, 构建一个协议包 回送一个随机 id ,
        byte[] response = UUID.randomUUID().toString().getBytes("utf-8");
        int responseLen = UUID.randomUUID().toString().length();
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(responseLen);
        messageProtocol.setContent(response);
        ctx.writeAndFlush(messageProtocol);
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
