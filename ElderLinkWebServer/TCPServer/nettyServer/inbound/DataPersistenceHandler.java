package nettyServer.inbound;

import java.util.Map;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import linkserver.base.pojo.RingData;
import linkserver.data.handler.RingDataJDBC;
import nettyServer.tools.ConstantMember;

/**
 * 持久化手环数据的Handler,在pipline入站的第五项
 * @author root
 *
 */
public class DataPersistenceHandler extends ChannelInboundHandlerAdapter implements ConstantMember{
	
	private final RingDataJDBC template;
	private RingData ring;
	public DataPersistenceHandler(RingDataJDBC ringDataTemplate) {
		this.template=ringDataTemplate;
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.out.println("<----------数据持久化操作---------->");
		@SuppressWarnings("unchecked")
		Map<String,Object> datas=(Map<String,Object>)msg;			//获取解码器解码后的数据映射
		String id=(String)datas.get(KEY_ELDERID);
		String lng=((String[])datas.get(KEY_POINT))[0];
		String lat=((String[])datas.get(KEY_POINT))[1];
		int heartRate=(Integer)datas.get(KEY_HEARTRATE);
		ring=new RingData(id,lng,lat,heartRate);
		template.createData(ring);
		System.out.println("数据插入成功！");
	}
	
}
