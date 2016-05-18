package com.likg.netty.time.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by likg on 2016-05-17.
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    private int count;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //System.out.println("EchoServerHandler channelRead");

//        ByteBuf byteBuf = (ByteBuf) msg;
//        byte[] req = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(req);

        String body = (String) msg;
        System.out.println("receive client msg=" + body + "  count==" + ++count);

        String time = System.currentTimeMillis() + System.getProperty("line.separator");
        ByteBuf reps = Unpooled.copiedBuffer(time.getBytes());
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
