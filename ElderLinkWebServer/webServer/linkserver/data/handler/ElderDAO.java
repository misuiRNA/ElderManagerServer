package linkserver.data.handler;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.transaction.PlatformTransactionManager;

import linkserver.base.pojo.Elder;

public interface ElderDAO {
	/**
	 * �������ݿ�����
	 * @param ds
	 */
	public void setDataSource(DataSource ds);
	/**
	 * ���������������
	 * @param tm
	 */
	void setTransactionManager(PlatformTransactionManager tm);
	/**
	 * �½�������Ϣ
	 * @param elder elder����Ҫ�������������
	 * @return
	 */
	public boolean createElder(Elder elder);
	/**
	 * ͨ��ID��ѯָ��������Ϣ
	 * @param id ����id
	 * @return ����ֵΪһ��Elder����
	 */
	public Elder getElder(String id);
	/**
	 * �о�����������Ϣ
	 * @return ����ֵΪһ��Elder�б�
	 */
	public List<Elder> listElder();
	/**
	 * ����ָ��������Ϣ
	 * @param elder elder����
	 * @return
	 */
	public boolean updateElder(Elder elder);
	/**
	 * ɾ��ָ��������Ϣ
	 * @param id ����id
	 * @return
	 */
	public boolean deleteElder(String id);
	/**
	 * ��ȡelder���е����һ��id������һ����id�����ڴ�����elder����
	 * @return  ����һ�����õ�id�����ڴ�����elder����
	 */
	public String getNewID();
	
}
