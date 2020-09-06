package com.springboot.netty.tcp.solution;

import com.springboot.netty.heartbeat.protocol.CustomProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

//服务端的解码器 --> Inbound
public class MyMessageDecoder extends ByteToMessageDecoder {

    /**
     * 入站进行编码
     * @param ctx 上下文
     * @param in 入站的数据
     * @param out list 的数据将流入下一个handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("解码被调用");
        //需要将得到二进制字节码-> MessageProtocol 数据包(对象)
        int  len = in.readInt();
        byte[] bytes = new byte[len];
        in.readBytes(bytes);
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(len);
        messageProtocol.setContent(bytes);
        out.add(messageProtocol);
    }
}
