package linkserver.tools;
/**
 * Elder类操作的相关属性，如常用字符等
 * @author Misui_user
 *
 */
public final class ElderAttribute {
	/**
	 * 查询老人信息为空时，返回的字符串
	 */
	public static final String FINDELDER_NULL="null";
	/**
	 * 如果数据库中没有任何老人信息时，返回字符串
	 */
	public static final String LISTELDER_NULL="null";
	/**
	 * 按姓名查找老人——没有改用户
	 */
	public static final String FINDUSER_NULL="null";
	/**
	 * 新建老人——成功
	 */
	public static final String CREATE_SUCCESS="ADD_SUCCESS";
	/**
	 * 新建老人——失败
	 */
	public static final String CREATE_FAILD="ADD_FAILD";
	/**
	 * 更新老人信息——成功
	 */
	public static final String UPDATE_SUCCESS = "UPDATE_SUCCESS";
	/**
	 * 更新老人信息——失败
	 */
	public static final String UPDATE_FAILD="UPDATE_FAILD";
	/**
	 * 更新老人信息——没有对应用户
	 */
	public static final String UPDATE_NULL="null";
	/**
	 * 删除老人信息——操作失败
	 */
	public static final String DELETE_FAILD="DEL_FAILD";
	/**
	 * 删除老人信息——操作成功
	 */
	public static final String DELETE_SUCCESS="DEL_SUCCESS";
	/**
	 * 删除老人信息——没有对应老人
	 */
	public static final String DELETE_NULL="null";
}
