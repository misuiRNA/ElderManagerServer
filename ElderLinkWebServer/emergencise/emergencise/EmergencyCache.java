package emergencise;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 紧急时间缓存类，构造紧急事件队列
 * @author misui
 *
 */
public class EmergencyCache {
	private static EmergencyCache emergencyCache = new EmergencyCache();
	private Map<String, Queue<Emergency>> emergencies = new HashMap<String, Queue<Emergency>>();
	
	private EmergencyCache() {
		for(String id:EmergencyAttribute.HOMEIDS) {
			emergencies.put(id, new LinkedBlockingQueue<Emergency>());
		}
	}
	
	public static EmergencyCache cache () {
		if(emergencyCache == null) {
			emergencyCache = new EmergencyCache();
		}
		return emergencyCache;
	}
	/**
	 * 将紧急事件置入对应养老机构的紧急事件缓冲区
	 * @param nursingId
	 * @param e
	 */
	public void addEmergency(String homeId, Emergency e) {
		System.out.println("<----------缓存紧急事件---------->");
		Queue<Emergency> queue = emergencies.get(homeId);
		if(queue == null) {
			System.out.println("机构ID不合法！");
			return;
		}
		if(queue.add(e)) System.out.println("事件插入成功！");
			else System.out.println("事件插入失败！");
	}
	
	public Emergency pollEmergency(String homeId) {
		System.out.println("<----------紧急事件轮询---------->");
		Queue<Emergency> queue = emergencies.get(homeId);
		if(queue == null) {
			System.out.println("养老机构ID不合法！");
			return null;
		}
		if(queue.isEmpty()) {
			System.out.println("养老机构“" + homeId + "”无紧急事件!");
			return null;
		}
		System.out.println("已向养老机构“" + homeId + "”推送紧急事件");
		return queue.poll();
	}
	
}
