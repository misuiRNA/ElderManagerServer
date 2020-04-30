package linkserver.base.pojo;

public class Elder {
	/**
	 * ���ˡ���ID
	 */
	private String elderID;
	/**
	 * ���ˡ�������
	 */
	private String elderName;
	/**
	 * ���ˡ�����������
	 */
	private String elderBirthday;
	/**
	 * ���ˡ����Ա�
	 */
	private String elderSex;
	/**
	 * ���ˡ����໤��
	 */
	private String elderChild;
	/**
	 * ���ˡ����໤����ϵ��ʽ
	 */
	private String elderChildNumber;
	/**
	 * ���ˡ�����ȫ������
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
	 * �Ƚϳ�child��childNumber��area��������������Ƿ����
	 * @param other
	 * @return
	 */
	public boolean equals(Elder other) {
		if(!this.elderID.equals(other.getElderID())) return false;
		if(!this.elderName.equals(other.getElderName())) return false;
		if(!this.elderSex.equals(other.getElderSex())) return false;
		if(!this.elderBirthday.equals(other.getElderBirthday())) return false;  //��ʱ���ʽͳһ����бȽ�
		return true;
	}
	
}
