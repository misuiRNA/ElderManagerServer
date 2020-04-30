package linkserver.data.handler;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.transaction.PlatformTransactionManager;

import linkserver.base.pojo.Elder;

public interface ElderDAO {
	/**
	 * 设置数据库连接
	 * @param ds
	 */
	public void setDataSource(DataSource ds);
	/**
	 * 设置事物管理属性
	 * @param tm
	 */
	void setTransactionManager(PlatformTransactionManager tm);
	/**
	 * 新建老人信息
	 * @param elder elder对象，要求各项属性完整
	 * @return
	 */
	public boolean createElder(Elder elder);
	/**
	 * 通过ID查询指定老人信息
	 * @param id 老人id
	 * @return 返回值为一个Elder对象
	 */
	public Elder getElder(String id);
	/**
	 * 列举所有老人信息
	 * @return 返回值为一个Elder列表
	 */
	public List<Elder> listElder();
	/**
	 * 更新指定老人信息
	 * @param elder elder对象
	 * @return
	 */
	public boolean updateElder(Elder elder);
	/**
	 * 删除指定老人信息
	 * @param id 老人id
	 * @return
	 */
	public boolean deleteElder(String id);
	/**
	 * 获取elder表中的最后一条id并返回一个新id，用于创建新elder数据
	 * @return  返回一个可用的id，用于创建新elder对象
	 */
	public String getNewID();
	
}
