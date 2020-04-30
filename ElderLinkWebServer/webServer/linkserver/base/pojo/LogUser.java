package linkserver.base.pojo;

public class LogUser {
	/**
	 * 桌面用户——姓名
	 */
	private String userName;
	/**
	 * 桌面用户——密码
	 */
	private String userPassword;
	/**
	 * 桌面用户——是否具有管理员权限
	 * 
	 */
	private int isAdmin=-1;
	/**
	 * 桌面用户——员工编号
	 */
	private String number;
	/**
	 * 桌面用户——真实姓名
	 */
	private String realName;
	/**
	 * 桌面用户——性别
	 */
	private String sex;
	/**
	 * 桌面用户——身份证号
	 */
	private String idCard;
	/**
	 * 桌面用户——出生年月
	 */
	private String birthday;
	/**
	 * 桌面用户——添加该用户的管理员用户名
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
	 * 判断两个对象是否相等，比较除realName、password、isAdmin的属性
	 * @param other 所需比较的对象
	 * @return 比较结果
	 */
	public boolean equale(LogUser other) {
		if(!this.userName.equals(other.getUserName())) return false;
		if(!this.superior.equals(other.getSuperior())) return false;
		if(!this.idCard.equals(other.getIdCard())) return false;
		if(!this.number.equals(other.getNumber())) return false;
		if(!this.sex.equals(other.getSex())) return false;
		if(!this.birthday.replaceAll("/", "-").equals(other.getBirthday())) return false;   //将时间格式统一后进行比较
		return true;
	}
}
