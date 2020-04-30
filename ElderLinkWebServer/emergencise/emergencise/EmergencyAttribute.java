package emergencise;
/**
 * 存储属性与常量
 * @author misui
 *
 */
public abstract class EmergencyAttribute {
	/**
	 * 心率字段
	 */
	public final static String FIT_HAERTRATE = "heartRate";
	/**
	 * 血压字段
	 */
	public final static String FIT_BLOODPRESSURE = "bloodPressure";
	/**
	 * 各养老机构ID数组，存储所有养老机构ID，用于建立紧急事件Map集合
	 */
	public final static String[] HOMEIDS = {"6130180001","6130180002"};
	
}
