package udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;
import java.net.InetSocketAddress;

public class ChineseProverClient {
    public void run(int port) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap server = new Bootstrap();
            server.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new ChineseProverClientHandler());

            Channel channel = server.bind(0).sync().channel();
            for (int i = 0; i < 20; i++) {
                channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("古诗", CharsetUtil.UTF_8)
                        , new InetSocketAddress("255.255.255.255",port))).sync();
                Thread.sleep(2000);
            }

            if (!channel.closeFuture().await(15000)) {
                System.out.println("Time Out");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        ChineseProverClient client = new ChineseProverClient();
        client.run(9021);
    }
}
