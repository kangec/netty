package serial;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;


public class SubReqClient {
    public void run(String host, int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            Bootstrap client = new Bootstrap();
            client.group(bossGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new ObjectDecoder( 1024
                                    , ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
                            pipeline.addLast(new ObjectEncoder());
                            pipeline.addLast(new SubscribeReqClientHandler());
                        }
                    });

            ChannelFuture future = client.connect(host, port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        SubReqClient client = new SubReqClient();
        client.run("127.0.0.1", 8091);
    }
}
