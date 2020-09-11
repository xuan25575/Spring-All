package com.springboot.netty.tcp.solution;

import com.springboot.netty.heartbeat.protocol.CustomProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

// 客户端解码器--outBound
public class MyMessageEncode extends MessageToByteEncoder<MessageProtocol> {
    /**
     * @param ctx 上下文
     * @param msg 消息 （和类上范型绑定的类型一致）
     * @param out 出站的数据
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) throws Exception {
        System.out.println("编码器被调用");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}
