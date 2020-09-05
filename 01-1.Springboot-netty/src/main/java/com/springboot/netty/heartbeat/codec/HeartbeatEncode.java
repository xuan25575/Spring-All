package com.springboot.netty.heartbeat.codec;

import com.springboot.netty.heartbeat.protocol.CustomProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

// 客户端解码器
public class HeartbeatEncode  extends MessageToByteEncoder<CustomProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, CustomProtocol msg, ByteBuf out) throws Exception {
        out.writeLong(msg.getId());
        out.writeBytes(msg.getContent().getBytes());
    }
}
