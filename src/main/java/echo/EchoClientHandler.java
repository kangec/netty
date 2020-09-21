package echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.logging.Logger;

/**
 * @Author Ardien
 * @Date 9/21/2020 7:25 PM
 * @Email ardien@126.com
 * @Version 1.0
 **/

@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private final Logger logger = Logger.getLogger(Thread.currentThread().getName());
    private final String[] msg = {
            "杨花扑帐春云热,","龟甲屏风醉眼缬。",
            "东家胡蝶西家飞,","白骑少年今日归。"
    };

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (String s : msg) {
            ctx.write(Unpooled.copiedBuffer(s.getBytes()));
        }
        ctx.flush();
    }

    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        logger.info(msg.toString(CharsetUtil.UTF_8));
    }
}
