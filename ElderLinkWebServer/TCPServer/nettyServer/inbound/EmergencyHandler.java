package nettyServer.inbound;

import java.util.Map;

import emergencise.Emergency;
import emergencise.EmergencyCache;
import emergencise.EmergencyFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import nettyServer.tools.ConstantMember;
/**
 * 在pipline入站的第四项，将紧急事件存入缓存区域
 * @author misui
 *
 */
public class EmergencyHandler extends ChannelInboundHandlerAdapter implements ConstantMember{
	
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String,Object> datas=(Map<String,Object>)msg;			//获取解码器解码后的数据映射
		this.addEmergency(datas);									//插入紧急事件
		ctx.fireChannelRead(msg);
	}
	/**
	 * 添加紧急事件到缓存区
	 * @param datas
	 */
	private void addEmergency(Map<String, Object> datas) {
		Emergency emergency = getEmergency(datas);
		if(emergency == null) return;
		EmergencyCache cache = EmergencyCache.cache();
		cache.addEmergency((String)datas.get(KEY_HOMEID), emergency);
	}
	/**
	 * 生成紧急事件实例
	 * @param datas
	 * @return
	 */
	private Emergency getEmergency(Map<String, Object> datas) {
		Boolean fitStatus = (Boolean)datas.get(KEY_EMERGENCY_FITSTATUS);
		Boolean pointStatus = (Boolean)datas.get(KEY_EMERGENCY_POINTSTATUS);
		if(fitStatus == null) {
			fitStatus = true;
			datas.put(KEY_EMERGENCY_FITSTATUS, fitStatus);
		}
		if(pointStatus == null) {
			pointStatus = true;
			datas.put(KEY_EMERGENCY_POINTSTATUS, pointStatus);
		}
		if(fitStatus && pointStatus) return null;
		Emergency emergency = EmergencyFactory.newEmergency(datas);
		return emergency;
	}
}
