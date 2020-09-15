package serial;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.slf4j.Slf4j;

@ChannelHandler.Sharable
public class SubscribeReqServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq req = (SubscribeReq) msg;
        if ("LiSi".equalsIgnoreCase(req.getUsername())) {
            System.out.println("Service accept client subscribe req:[ " + req.toString() + " ]");
            ctx.writeAndFlush(resp(req.getSubReqId()));
        }
    }

    private Object resp(int subReqId) {
        return SubscribeResp.builder()
                .subResp(subReqId)
                .respCode(0)
                .desc("Netty book order succeed.")
                .build();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
