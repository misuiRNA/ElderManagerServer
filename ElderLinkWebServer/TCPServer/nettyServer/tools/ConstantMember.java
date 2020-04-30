package nettyServer.tools;
/**
 * 存放一些公共常量
 * @author root
 *
 */
public interface ConstantMember {
	//以下字段作为解码器解码消息后，存放数据Map的key
	public final String KEY_APPLAYFOROUT="ApplayforOUT_2";		//
	public final String KEY_POWER="POWER_3";					//
	public final String KEY_HOMEID="HOMEID_4";					//
	public final String KEY_ELDERID="ELDERID_5";				//
	public final String KEY_HEARTRATE="HEARTRATE_6";			//
	public final String KEY_BLOODPRESSURE="BLOODPRESSURE_7";	//
	public final String KEY_POINT="POINT_8";					//
	
	//以下字段为业务处理过程中需要持久化的数据在Map中的key
	public final String KEY_INAREA="INAREA";
	public final String KEY_ELDER="ELDEROBJ";
	
	//以下字段表示老人安全状态，Map中的Key值
	public final String KEY_EMERGENCY_POINTSTATUS = "POINTSTATUS_ELDER";
	public final String KEY_EMERGENCY_FITSTATUS = "FITSTATUS_ELDER";
	
}
