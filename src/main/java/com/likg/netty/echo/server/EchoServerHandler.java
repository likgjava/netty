package com.likg.netty.echo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by likg on 2016-05-17.
 */
public class EchoServerHandler extends ChannelHandlerAdapter {

    private int count;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("receive client msg=" + body + "  count==" + ++count);

        body += "$_";
        ByteBuf reps = Unpooled.copiedBuffer(body.getBytes());
        ctx.write(reps);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
