package com.springboot.netty.heartbeat.codec;

import com.springboot.netty.heartbeat.protocol.CustomProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

//服务端的解码器 --> Inbound
public class HeartbeatDecoder extends ByteToMessageDecoder {

    /**
     * 入站进行编码
     * @param ctx 上下文
     * @param in 入站的数据
     * @param out list 的数据将流入下一个handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        long id = in.readLong();
        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);
        String content  = new String(bytes);

        CustomProtocol customProtocol = new CustomProtocol();
        customProtocol.setId(id);
        customProtocol.setContent(content);

        out.add(customProtocol);
    }
}
