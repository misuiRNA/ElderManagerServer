package emergencise;

import java.util.HashMap;
import java.util.Map;

import nettyServer.tools.ConstantMember;

/**
 * 紧急事件生成工厂
 * @author misui
 *
 */
public class EmergencyFactory implements ConstantMember{
	/**
	 * 新建健康参数异常的Emergency事件，并返回
	 * @param homeId
	 * @param elderId
	 * @return
	 */
	public static Emergency newEmergency(Map<String, Object> datas) {
		if(datas == null || datas.size() < 2) return null;
		Emergency e = new Emergency((String)datas.get(KEY_HOMEID),
								(String)datas.get(KEY_ELDERID));
		e.setFitStatus((Boolean)datas.get(KEY_EMERGENCY_FITSTATUS));
		e.setPointStatus((Boolean)datas.get(KEY_EMERGENCY_POINTSTATUS));
		Map<String, Integer> fitValues = new HashMap<String, Integer>();
		fitValues.put(EmergencyAttribute.FIT_HAERTRATE, (Integer)datas.get(KEY_HEARTRATE));
		fitValues.put(EmergencyAttribute.FIT_BLOODPRESSURE, (Integer)datas.get(KEY_BLOODPRESSURE));
		e.setFitValues(fitValues);
		e.setPointValues((String[])datas.get(KEY_POINT));
		return e;
	}
	
}
