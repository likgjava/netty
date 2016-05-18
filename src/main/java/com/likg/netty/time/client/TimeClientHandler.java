package com.likg.netty.time.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * Created by likg on 2016-05-17.
 */
public class TimeClientHandler extends ChannelHandlerAdapter {

    private byte[] req;
    private int count;

    public TimeClientHandler() {
        req = ("query time!" + System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("EchoClientHandler channelActive");

        //String msg = MsgQueue.getQueue().take();

        ByteBuf byteBuf;
        for(int i=0; i<100; i++){
            byteBuf = Unpooled.buffer(req.length);
            byteBuf.writeBytes(req);
            ctx.writeAndFlush(byteBuf);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//
//        buf.readBytes(req);
        String body = (String) msg;
        System.out.println("result===" + body + "  count=" + ++count);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
