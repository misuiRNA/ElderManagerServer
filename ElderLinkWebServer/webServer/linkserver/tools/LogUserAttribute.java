package linkserver.tools;
/**
 * LogUser相关操作的一些固定属性设置，如字符串等
 * @author Misui_user
 *
 */
public final class LogUserAttribute {
	/**
	 * 登录请求返回结果——没有登录权限
	 */
	public static final String LOGIN_NOPERMISSION="NOPERMISSION";
	/**
	 * 登录请求返回结果——密码错误
	 */
	public static final String LOGIN_WRONGPASSWORD="WRONGPASSWORD";
	/**
	 * 登录请求返回结果——管理员身份登录
	 */
	public static final String LOGIN_ADMINSTRATOR="ADMINISTRATOR";
	/**
	 * 登录请求返回结果——普通用户身份登录
	 */
	public static final String LOGIN_USERPERMIT="USERPERMIT";
	/**
	 * 按姓名查找用户——没有改用户
	 */
	public static final String FINDUSER_NULL="null";
	/**
	 * 新建用户——成功
	 */
	public static final String CREATE_SUCCESS="ADD_SUCCESS";
	/**
	 * 新建用户——失败
	 */
	public static final String CREATE_FAILD="ADD_FAILD";
	/**
	 * 新建用户——用户名重复
	 */
	public static final String CREATE__REPEATNAME="REPEATNAME";
	/**
	 * 更新用户信息——成功
	 */
	public static final String UPDATE_SUCCESS = "UPDATE_SUCCESS";
	/**
	 * 更新用户信息——失败
	 */
	public static final String UPDATE_FAILD="UPDATE_FAILD";
	/**
	 * 更新用户信息——没有对应用户
	 */
	public static final String UPDATE_NULL="null";
	/**
	 * 更改用户密码——没有对应用户
	 */
	public static final String PASSWORD_NULL="UPDATE_FAILD";
	/**
	 * 更改用户密码——修改成功
	 */
	public static final String PASSWORD_SUCCESS="UPDATE_SUCCESS";
	/**
	 * 更改用户密码——修改失败
	 */
	public static final String PASSWORD_FAILD="UPDATE_FAILD";
	/**
	 * 删除用户——操作失败
	 */
	public static final String DELETE_FAILD="DEL_FAILD";
	/**
	 * 删除用户——操作成功
	 */
	public static final String DELETE_SUCCESS="DEL_SUCCESS";
	
}
