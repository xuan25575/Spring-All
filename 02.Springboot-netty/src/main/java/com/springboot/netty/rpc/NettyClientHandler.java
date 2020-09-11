package com.springboot.netty.rpc;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;//上下文
    private String result; //返回的结果
    private String param; //客户端调用方法时，传入的参数

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接已经激活");
        //因为我们在其它方法会使用到 ctx
        context = ctx;
    }

    // 不加锁的话 不能到唤醒当前持有锁 wait的线程。
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(" channelRead被调用 ");
        result = msg.toString();
        notify(); //唤醒等待的线程
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       ctx.close();
    }

    //被代理对象调用, 发送数据给服务器，-> wait -> 等待被唤醒(channelRead) -> 返回结果 (3)->5
    //synchronized 等当前对象拿到锁。
    @Override
    public synchronized Object call() throws Exception {

        System.out.println(" call1 被调用 ");
        context.writeAndFlush(param);
        //进行 wait 不拿到锁的话就会报错
        wait(); //等待 channelRead 方法获取到服务器的结果后，唤醒
        System.out.println(" call2 被调用 ");
        return result; //服务方返回的结果
    }

    //(2)
    void setParam(String param) {
        System.out.println(" Set Param ");
        this.param = param;
    }
}
