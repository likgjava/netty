package com.likg.netty.echo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by likg on 2016-05-17.
 */
public class EchoClientHandler extends ChannelHandlerAdapter {

    private static final String ECHO_REQ = "hello, welcome to Netty.$_";
    private int count;

    public EchoClientHandler() {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("EchoClientHandler channelActive");

        for(int i=0; i<100; i++){
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("result===" + body + "  count=" + ++count);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
