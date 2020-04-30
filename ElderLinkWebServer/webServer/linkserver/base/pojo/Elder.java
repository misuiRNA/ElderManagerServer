package linkserver.base.pojo;

public class Elder {
	/**
	 * 老人——ID
	 */
	private String elderID;
	/**
	 * 老人——姓名
	 */
	private String elderName;
	/**
	 * 老人——出生年月
	 */
	private String elderBirthday;
	/**
	 * 老人——性别
	 */
	private String elderSex;
	/**
	 * 老人——监护人
	 */
	private String elderChild;
	/**
	 * 老人——监护人联系方式
	 */
	private String elderChildNumber;
	/**
	 * 老人——安全后动区域
	 */
	private String elderArea;
	
	public void setElderID(String id) {this.elderID=id;}
	public String getElderID() {return elderID;}
	
	public void setElderName(String name) {this.elderName=name;}
	public String getElderName() {return elderName;}
	
	public void setElderBirthday(String birthday) {this.elderBirthday=birthday;}
	public String getElderBirthday() {return elderBirthday;}
	
	public void setElderSex(String sex) {this.elderSex=sex;}
	public String getElderSex() {return elderSex;}
	
	public void setElderChild(String child) {this.elderChild=child;}
	public String getElderChild() {return elderChild;} 
	
	public void setElderChildNumber(String number) {this.elderChildNumber=number;}
	public String getElderChildNumber() {return elderChildNumber;}
	
	public void setElderArea(String area) {this.elderArea=area;}
	public String getElderArea() {return elderArea;}
	
	public Elder() {}
	
	public Elder(String id,String name,String sex,String birthday,
			     String area,String child,String number) {
		this.elderID=id;
		this.elderName=name;
		this.elderBirthday=birthday;
		this.elderSex=sex;
		this.elderChild=child;
		this.elderChildNumber=number;
		this.elderArea=area;
	}
	
	public void setPara(String id,String name,String sex,String birthday,
		                String area,String child,String number) {
		this.setElderID(id);
		this.setElderName(name);
		this.setElderBirthday(birthday);
		this.setElderSex(sex);
		this.setElderArea(area);
		this.setElderChild(child);
		this.setElderChildNumber(number);
	}
	/**
	 * 比较除child、childNumber、area以外的其他属性是否相等
	 * @param other
	 * @return
	 */
	public boolean equals(Elder other) {
		if(!this.elderID.equals(other.getElderID())) return false;
		if(!this.elderName.equals(other.getElderName())) return false;
		if(!this.elderSex.equals(other.getElderSex())) return false;
		if(!this.elderBirthday.equals(other.getElderBirthday())) return false;  //将时间格式统一后进行比较
		return true;
	}
	
}
