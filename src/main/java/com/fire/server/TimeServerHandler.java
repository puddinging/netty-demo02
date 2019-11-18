package com.fire.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

//示例可以在channel里边共享
@Sharable
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
//    每个信息入站都会调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req,"UTF-8");
//        String currentTime = "query time order".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"bad order";
//        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
//        ctx.write(resp);
        System.out.println("server received: "+ buf.toString(CharsetUtil.UTF_8));
//        将接受到的数据返回给发送者，但是此时并没有刷新数据
        ctx.write(buf);
    }

//    通知处理器最后的 channelread() 是当前批处理中的最后一条消息时调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.flush();
//        将数据冲刷到远程节点，关闭通道后，操作完成
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }


//    读操作时捕获到异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        打印异常堆栈跟踪
        cause.printStackTrace();
//        关闭通道
        ctx.close();
    }
}
