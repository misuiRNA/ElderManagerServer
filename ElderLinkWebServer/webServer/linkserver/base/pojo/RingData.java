package linkserver.base.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RingData {
	/**
	 * �ֻ����ݱ�������Ӧ����ID
	 */
	private String curID;
	/**
	 * ����ʱ��
	 */
	private String datetime;
	/**
	 * ����
	 */
	private String lng;
	/**
	 * γ��
	 */
	private String lat;
	/**
	 * ����
	 */
	private Integer heartRate;
	
	public String getCurID() {return curID;}
	
	public String getDatetime() {return datetime;}
	/**
	 * ����
	 * @return
	 */
	public String getlng() {return lng;}
	/**
	 * γ��
	 * @return
	 */
	public String getlat() {return lat;}
	
	public Integer getHeartRate() {return heartRate;}

	/**
	 *  新建对象时需要手动加入时间字符，一般由mapper构建，在查询数据时使用
	 * @param id
	 * @param time
	 * @param lng   经度
	 * @param lat   纬度
	 * @param heart  心率值
	 */
	public RingData(String id,String time,String lng,String lat,Integer heart) {
		this.curID=id;
		this.datetime=time;
		this.lng=lng;
		this.lat=lat;
		this.heartRate=heart;
	}
	/**
	 * 新建对象时自动获取当前系统时间
	 * @param id
	 * @param lng
	 * @param lat
	 * @param heart
	 */
	public RingData(String id,String lng,String lat,Integer heart) {
		this(id,null,lng,lat,heart);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.datetime=sdf.format(new Date());
		
	}
}
