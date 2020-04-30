package linkserver.data.handler;

import java.util.List;
import javax.sql.DataSource;

import linkserver.base.pojo.LogUser;

public interface LogUserDAO {
	/*
	 *
	 * �������ݿ�����
	 * @param ds
	 */
	public void setDataSource(DataSource ds);
	
	/**
	 * ��������û���Ϣ
	 * @param user
	 * @return
	 */
	public boolean createUser(LogUser user);
	
	/**
	 * ͨ���û�����ȡ�����û���Ϣ
	 * @param username
	 * @return
	 */
	public LogUser getUser(String username);
	
	/**
	 * ��ȡ�����û���Ϣ
	 * @return
	 */
	public List<LogUser> listUser();
	
	/**
	 * �����û���Ϣ
	 * @param user
	 * @return
	 */
	public boolean updateUser(LogUser user);
	
	/**
	 * ɾ��ָ���û���Ϣ��
	 * @param username  �����û�
	 * @param password  �����û����
	 * @return
	 */
	public boolean DeleteUser(String username);
	
}
