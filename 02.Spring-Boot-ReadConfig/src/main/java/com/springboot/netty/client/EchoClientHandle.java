package com.springboot.netty.client;

import com.springboot.context.SpringBeanFactory;
import com.springboot.netty.protocol.CustomProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 当客户端空闲了 N 秒没有给服务端发送消息时会自动发送一个心跳来维持连接。
 */
public class EchoClientHandle extends SimpleChannelInboundHandler<ByteBuf> {

    /** logger. */
    private Logger logger = LoggerFactory.getLogger(EchoClientHandle.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt ;

            if (idleStateEvent.state() == IdleState.WRITER_IDLE){
                logger.info("已经 10 秒没有发送信息！");
                //向服务端发送消息
                CustomProtocol heartBeat = SpringBeanFactory.getBean("heartBeat", CustomProtocol.class);
                ctx.writeAndFlush(heartBeat).addListener(ChannelFutureListener.CLOSE_ON_FAILURE) ;
            }

        }
        super.userEventTriggered(ctx, evt);
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

        // 客户端收到服务器端传递的消息;
        logger.info("客户端收到消息={}",msg.toString(CharsetUtil.UTF_8)) ;

    }
}
