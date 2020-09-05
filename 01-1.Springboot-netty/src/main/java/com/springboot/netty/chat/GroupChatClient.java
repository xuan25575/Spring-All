package com.springboot.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class GroupChatClient {

    private int port;
    private String ip;


    public GroupChatClient(int port, String ip) {
        this.port = port;
        this.ip = ip;
    }


    public void run() throws Exception{

        NioEventLoopGroup group  = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            // 添加自己的handler
                            pipeline.addLast(new GroupChatClientHandler());
                        }
                    });

            ChannelFuture cf = bootstrap.connect(ip, port).sync();

            Channel channel = cf.channel();
            System.out.println("-------" + channel.localAddress()+ "--------");

            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNext()){
                String str = scanner.nextLine();
                //通过 channel 发送到服务器端
                channel.writeAndFlush(str);
            }

            //cf.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully();
        }


    }

    public static void main(String[] args) throws  Exception {
        new GroupChatClient(8888,"127.0.0.1").run();
    }
}
