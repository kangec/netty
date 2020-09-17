package udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class ChineseProverClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        String body = msg.content().toString(CharsetUtil.UTF_8);
        System.out.println(body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
