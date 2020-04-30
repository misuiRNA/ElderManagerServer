package linkserver.base.pojo;

public class LogUser {
	/**
	 * �����û���������
	 */
	private String userName;
	/**
	 * �����û���������
	 */
	private String userPassword;
	/**
	 * �����û������Ƿ���й���ԱȨ��
	 * 
	 */
	private int isAdmin=-1;
	/**
	 * �����û�����Ա�����
	 */
	private String number;
	/**
	 * �����û�������ʵ����
	 */
	private String realName;
	/**
	 * �����û������Ա�
	 */
	private String sex;
	/**
	 * �����û��������֤��
	 */
	private String idCard;
	/**
	 * �����û�������������
	 */
	private String birthday;
	/**
	 * �����û�������Ӹ��û��Ĺ���Ա�û���
	 */
	private String superior;
	
	public void setUserName(String name) {this.userName=name;}
	public String getUserName() {return userName;}
	
	public void setUserPassword(String password) {this.userPassword=password;}
	public String getUserPassword() {return userPassword;}
	
	public void setIsAdmin(Integer isAdmin) {this.isAdmin=isAdmin;}
	public Integer getIsAdmin() {return isAdmin;}
	
	public void setNumber(String number) {this.number=number;}
	public String getNumber() {return number;} 
	
	public void setRealName(String rName) {this.realName=rName;}
	public String getRealName() {return realName;}
	
	public void setSex(String sex) {this.sex=sex;}
	public String getSex() {return sex;}
	
	public void setIdCard(String id) {this.idCard=id;}
	public String getIdCard() {return idCard;}
	
	public void setBirthday(String birthday) {this.birthday=birthday;}
	public String getBirthday() {return birthday;}
	
	public void setSuperior(String superior) {this.superior=superior;}
	public String getSuperior() {return superior;}
	
	public LogUser() {}
	/**
	 * 
	 * @param name
	 * @param password
	 * @param isAdmin
	 * @param number
	 * @param realName
	 * @param sex
	 * @param id
	 * @param birthday
	 * @param superior
	 */
	public LogUser(String name,String password,Integer isAdmin,String number,
			       String realName,String sex,String id,String birthday,String superior) {
		this.userName=name;
		this.userPassword=password;
		this.isAdmin=isAdmin;
		this.number=number;
		this.realName=realName;
		this.sex=sex;
		this.idCard=id;
		this.birthday=birthday;
		this.superior=superior;
	}
	/**
	 * 
	 * @param name  
	 * @param password
	 * @param isAdmin
	 * @param number
	 * @param realName
	 * @param sex
	 * @param id
	 * @param birthday
	 * @param superior
	 */
	public void setPara(String name,String password,Integer isAdmin,String number,
			       String realName,String sex,String id,String birthday,String superior) {
		this.setUserName(name);
		this.setUserPassword(password);
		this.setIsAdmin(isAdmin);
		this.setIdCard(id);
		this.setNumber(number);
		this.setRealName(realName);
		this.setSex(sex);
		this.setBirthday(birthday);
		this.setSuperior(superior);
	}
	/**
	 * �ж����������Ƿ���ȣ��Ƚϳ�realName��password��isAdmin������
	 * @param other ����ȽϵĶ���
	 * @return �ȽϽ��
	 */
	public boolean equale(LogUser other) {
		if(!this.userName.equals(other.getUserName())) return false;
		if(!this.superior.equals(other.getSuperior())) return false;
		if(!this.idCard.equals(other.getIdCard())) return false;
		if(!this.number.equals(other.getNumber())) return false;
		if(!this.sex.equals(other.getSex())) return false;
		if(!this.birthday.replaceAll("/", "-").equals(other.getBirthday())) return false;   //��ʱ���ʽͳһ����бȽ�
		return true;
	}
}
