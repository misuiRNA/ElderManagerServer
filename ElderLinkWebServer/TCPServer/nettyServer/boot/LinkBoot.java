package nettyServer.boot;

import org.springframework.beans.factory.annotation.Autowired;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import linkserver.data.handler.ElderJDBC;
import linkserver.data.handler.RingDataJDBC;
import nettyServer.inbound.DataPersistenceHandler;
import nettyServer.inbound.EmergencyHandler;
import nettyServer.inbound.FitnessHandler;
import nettyServer.inbound.OverstepHandler;
import nettyServer.inbound.RingDataDecoder;

/**
 * 手环数据服务器的引导程序，入口
 * @author root
 *
 */
public class LinkBoot {
	private int PORT;
	private ElderJDBC elderTemplate;
	private RingDataJDBC ringDataTemplate;
	public LinkBoot() {
		this(1514);
	}
	public LinkBoot(int port) {
		this.PORT=port;
	}
	
	@Autowired
	public void setElderTemplate(ElderJDBC elderJDBCTemplate) {
		this.elderTemplate=elderJDBCTemplate;
		System.out.println("elderTemplate has been load");
	}
	@Autowired
	public void setRingDataTemplate(RingDataJDBC ringDataJDBCTemplate) {
		this.ringDataTemplate=ringDataJDBCTemplate;
		System.out.println("ringDataTemplate has been load");
	}
	
	/**
	 * 以给定端口号开启服务
	 * @param port 服务端口号
	 */
	public void start() throws Exception{
		EventLoopGroup bossGroup=new NioEventLoopGroup(1);
		EventLoopGroup workGroup=new NioEventLoopGroup();
		try {
			ServerBootstrap boot=new ServerBootstrap();
			boot.group(bossGroup, workGroup)
				   .channel(NioServerSocketChannel.class)
				   .option(ChannelOption.SO_BACKLOG, 100)		//该部分内容，后续需要定制
				   .handler(new LoggingHandler(LogLevel.INFO))		//后续需要定制
				   .childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						// TODO Auto-generated method stub
						ChannelPipeline pipeline=ch.pipeline();
						pipeline.addLast(new RingDataDecoder());				//消息解码器，入站第一层解码
						pipeline.addLast(new OverstepHandler(elderTemplate));
						pipeline.addLast(new FitnessHandler());
						pipeline.addLast(new EmergencyHandler());
						pipeline.addLast(new DataPersistenceHandler(ringDataTemplate));
					}
				   });
			ChannelFuture future=boot.bind(PORT).sync();
			System.out.println("<----------IoTServer started---------->");
			future.channel().closeFuture().sync();
		}finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
	
	
	
	
	
	
}
