package com.springboot.netty.heartbeat.codec;

import com.springboot.netty.heartbeat.protocol.CustomProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

// 客户端解码器--outBound
public class HeartbeatEncode  extends MessageToByteEncoder<CustomProtocol> {
    /**
     * MessageToByteEncoder 源码中
     *
     *  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
     *         ByteBuf buf = null;
     *         try {
     *             if (acceptOutboundMessage(msg)) { // 判断传入的数据类型是否和需要编码的类型的一致，才会进行编码
     *                 @SuppressWarnings("unchecked")
     *                 I cast = (I) msg;
     *                 buf = allocateBuffer(ctx, cast, preferDirect);
     *                 try {
     *                     encode(ctx, cast, buf);
     *                 } finally {
     *                     ReferenceCountUtil.release(cast);
     *                 }
     *
     *                 if (buf.isReadable()) {
     *                     ctx.write(buf, promise);
     *                 } else { // 否则执行下一个 handler
     *                     buf.release();
     *                     ctx.write(Unpooled.EMPTY_BUFFER, promise);
     *                 }
     *                 buf = null;
     *             } else {
     *                 ctx.write(msg, promise);
     *             }
     *     }
     *
     *
     *
     * @param ctx 上下文
     * @param msg 消息 （和类上范型绑定的类型一致）
     * @param out 出站的数据
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, CustomProtocol msg, ByteBuf out) throws Exception {
        out.writeLong(msg.getId());
        out.writeBytes(msg.getContent().getBytes());
    }
}
