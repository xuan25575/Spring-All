package com.springboot.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

public class TestServerInitializer extends ChannelInitializer<NioSocketChannel> {


    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        //加入一个 netty 提供的 httpServerCodec codec =>[coder - decoder]
        // HttpServerCodec 说明
        //1. HttpServerCodec 是 netty 提供的处理 http 的 编-解码器
        pipeline.addLast("httpCodec",new HttpServerCodec());
        //2. 增加一个自定义的 handler
        pipeline.addLast(new TestHttpServerHandler());
    }
}
