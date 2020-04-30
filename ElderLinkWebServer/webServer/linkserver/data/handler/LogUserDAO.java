package linkserver.data.handler;

import java.util.List;
import javax.sql.DataSource;

import linkserver.base.pojo.LogUser;

public interface LogUserDAO {
	/*
	 *
	 * 设置数据库连接
	 * @param ds
	 */
	public void setDataSource(DataSource ds);
	
	/**
	 * 添加桌面用户信息
	 * @param user
	 * @return
	 */
	public boolean createUser(LogUser user);
	
	/**
	 * 通过用户名获取桌面用户信息
	 * @param username
	 * @return
	 */
	public LogUser getUser(String username);
	
	/**
	 * 获取所有用户信息
	 * @return
	 */
	public List<LogUser> listUser();
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public boolean updateUser(LogUser user);
	
	/**
	 * 删除指定用户信息，
	 * @param username  检索用户
	 * @param password  核验用户身份
	 * @return
	 */
	public boolean DeleteUser(String username);
	
}
