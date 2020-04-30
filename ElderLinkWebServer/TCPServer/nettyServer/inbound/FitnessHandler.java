package nettyServer.inbound;

import java.util.Map;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import nettyServer.tools.ConstantMember;

/**
 * 身体健康判定处理器，在pipline入站的第三项，判断身体健康参数是否正常
 * @author root
 *
 */
public class FitnessHandler extends ChannelInboundHandlerAdapter implements ConstantMember{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
        @SuppressWarnings("unchecked")
		Map<String,Object> datas=(Map<String,Object>)msg;			//获取解码器解码后的数据映射
        int power=(Integer) datas.get(KEY_POWER);
        int heartRate=(Integer) datas.get(KEY_HEARTRATE);
        int bloodPressure=(Integer) datas.get(KEY_BLOODPRESSURE);
        if((!enoughPower(power)) || (!safeHeartRate(heartRate)) || (!safeBloodPressure(bloodPressure))) {
        	//如果身体状态异常，向紧急事件缓存区插入紧急事件
        	datas.put(KEY_EMERGENCY_FITSTATUS, new Boolean(false));
        }
        ctx.fireChannelRead(msg);
	}
	/**
	 * 判断电量是否足够
	 * @param power
	 * @return
	 */
	private boolean enoughPower(int power) {
		if(power < 20) {
			return false;
		}
		return true;
	}
	/**
	 * 判断心律是否正常，***********************这里后续需要借助数据分析方法，分析每个人的不同情况
	 * @param heartRate
	 * @return
	 */
	private boolean safeHeartRate(int heartRate) {
		if(heartRate > 55 && heartRate < 90) {
			return true;
		}
		return false;
	}
	/**
	 * 判断血压是否正常，***********************这里后续需要借助数据分析方法，分析每个人的不同情况
	 * @param heartRate
	 * @return
	 */
	private boolean safeBloodPressure(int bloodPressure) {
		if(bloodPressure > 65 && bloodPressure < 160) {
			return true;
		}
		return false;
	}
	
}
