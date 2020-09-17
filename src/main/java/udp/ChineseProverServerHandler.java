package udp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.ThreadLocalRandom;

public class ChineseProverServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final String[] DICTIONARY = {
            "只要功夫深，铁许磨成针。",
            "异乡何处最牵愁？独上边城城上楼。",
            "日暮北来惟有雁，地寒西去更无州。",
            "数声塞角高还咽，一派泾河冻不流。",
            "君作贫官我为客，此中离恨共难收。"
    };

    private String nextQuote() {
        int index = ThreadLocalRandom.current().nextInt(DICTIONARY.length);
        return DICTIONARY[index];
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        String body = msg.content().toString(CharsetUtil.UTF_8);
        System.out.println(body);
        if ("古诗".equals(body)) {
            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(nextQuote(),CharsetUtil.UTF_8),msg.sender()));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
