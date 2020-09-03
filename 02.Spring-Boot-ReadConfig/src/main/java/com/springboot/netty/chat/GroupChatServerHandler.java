package com.springboot.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GroupChatServerHandler extends SimpleChannelInboundHandler<String>{

    //public static List<Channel> channels = new ArrayList<Channel>();

    //使用一个 hashMap 管理
    //public static Map<String, Channel> channels = new HashMap<String,Channel>();

    //定义一个 channel 组，管理所有的 channel
    //GlobalEventExecutor.INSTANCE) 是全局的事件执行器，是一个单例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    // 日期格式器
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    //handlerAdded 表示连接建立，一旦连接，第一个被执行
    //将当前 channel 加入到 channelGroup
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();

        /**
         * 将该客户加入聊天的信息推送给其它在线的客户端
         * 该方法会将 channelGroup 中所有的 channel 遍历，并发送消息， 我们不需要自己遍历
         */
        channelGroup.writeAndFlush("【客户端】:"+channel.remoteAddress() + "加入聊天, 时间:"+ format.format(new Date()));
        channelGroup.add(channel);
    }

    //断开连接, 将 xx 客户离开信息推送给当前在线的客户
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("【客户端】" + channel.remoteAddress() + "离开了。");
        System.out.println("数量:"+channelGroup.size());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //表示 channel 处于活动状态, 提示 xx 上线
        Channel channel = ctx.channel();
        System.out.println("客户端: " + channel.remoteAddress() + "上线了");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //下线了
        System.out.println("【客户端:】" + channel.remoteAddress() + " 下线了");
        // 遍历所有channel 告诉这个客户端下线了

    }


    //读取数据.
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.stream().forEach(ch -> {
            if(ch != channel){//不是当前的 channel,转发消息
                ch.writeAndFlush("【客户端】"+ channel.remoteAddress() + ", 消息 :"+msg+ "\n");

            }else{
                //回显自己的信息
                ch.writeAndFlush("【自己】发送的消息:" + msg + "\n");
            }
        });
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 关闭通道
        ctx.close();
    }
}
