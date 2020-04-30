package emergencise;
import java.util.HashMap;
import java.util.Map;

/**
 * 紧急事件类，包含位置安全、身体健康参数等安全参数
 * @author misui
 *
 */
public class Emergency {
	private String homeID;				//养老院编号
	private String elderID;				//老人编号
	private boolean pointStatus = true;	//位置状态——true为安全，false为不安全
	private boolean fitStatues = true;	//身体健康参数状态——true为健康，false为不健康
	private String[] pointValues;		//位置数据，经纬度
	//身体健康参数数据
	private Map<String, Integer> fitValues = new HashMap<String, Integer>();
	
	public Emergency(String homeID, String elderID) {		//应当使用拷贝
		this.homeID = homeID;
		this.elderID = elderID;
		this.pointStatus = true;
		this.fitStatues = true;
	}
	public void setPointStatus(boolean pointStatus) {
		this.pointStatus = pointStatus;
	}
	public void setFitStatus(boolean fitStatus) {
		this.fitStatues = fitStatus;
	}
	public void setPointValues(String[] point) {		//应当使用拷贝
		this.pointValues = point.clone();
	}
	public void setFitValues(Map<String, Integer> fitValues) {
		this.fitValues = fitValues;
	}
	public String getHomeID() {
		return this.homeID;
	}
	public String getElderID() {
		return this.elderID;
	}
	public boolean getPointStatus() {
		return this.pointStatus;
	}
	public boolean getFitStatus() {
		return this.fitStatues;
	}
	public String[] getPointValues() {
		return this.pointValues;
	}
	public Map<String, Integer> getFitValues(){
		return this.fitValues;
	}
}
