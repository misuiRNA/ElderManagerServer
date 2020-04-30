package linkserver.data.handler;

import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import linkserver.base.pojo.Elder;

@Repository("elderJDBCTemplate")
public class ElderJDBC implements ElderDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private PlatformTransactionManager transactionManager;
	
	@Autowired
	@Override
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		this.dataSource=ds;
		this.jdbcTemplateObject=new JdbcTemplate(dataSource);
	}
	
	@Autowired
	@Override
	public void setTransactionManager(PlatformTransactionManager tm) {
		this.transactionManager=tm;
	}

	@SuppressWarnings("finally")
	@Override
	public boolean createElder(Elder elder) {
		// TODO Auto-generated method stub
		boolean resultStatus=false;   //����״̬�����ظ�������
		if(this.getElder(elder.getElderID())!=null) return resultStatus;    //�����id��elder�����Ѿ����ڣ��������½�
		String id,name,sex,birthday,child,childNumber,area;
		id=elder.getElderID();
		name=elder.getElderName();
		sex=elder.getElderSex();
		birthday=elder.getElderBirthday();
		child=elder.getElderChild();
		childNumber=elder.getElderChildNumber();
		area=elder.getElderArea();
		TransactionDefinition def=new DefaultTransactionDefinition();   //�������������
		TransactionStatus status=transactionManager.getTransaction(def);
		String SQL="INSERT INTO Elder (ID,name,sex,birthday,child,childNumber,area) VALUES (?,?,?,?,?,?,?)";
		String creatRingData="CREATE TABLE "+id+" ("
				             + "[curID] nvarchar(50),"
				             + "[time] datetime,"
				             + "[lng] nvarchar(50),"
				             + "[lat] nvarchar(50),"
				             + "[heartRate] int"
				             + ");";
		try {
			jdbcTemplateObject.update(SQL,id,name,sex,birthday,child,childNumber,area);
			jdbcTemplateObject.update(creatRingData);
			transactionManager.commit(status);
			resultStatus=true;
		}catch(DataAccessException e){
			transactionManager.rollback(status);
			resultStatus=false;
			System.out.println(e);//**************************��ɾ��
			throw e;
		}finally {
			return resultStatus;
		}
	}

	@Override
	public Elder getElder(String id) {
		// TODO Auto-generated method stub
		String SQL="SELECT * FROM Elder WHERE ID=?";
		List<Elder> elders=jdbcTemplateObject.query(SQL, new ElderMapper(),id);
		//���û�д��˵���Ϣ���򷵻�һ���ն���
		if(elders.size()==0) return null;
		return elders.get(0);  //�����б��е�Ψһһ����¼
	}

	@Override
	public List<Elder> listElder() {
		// TODO Auto-generated method stub
		String SQL="SELECT * FROM Elder";
		List<Elder> elders=jdbcTemplateObject.query(SQL, new ElderMapper());
		return elders;
	}

	@Override
	public boolean updateElder(Elder elder) {
		// TODO Auto-generated method stub
		String SQL="UPDATE Elder SET child=?,childNumber=?,area=? WHERE ID=?";
		String id,child,childNumber,area;
		id=elder.getElderID();
		child=elder.getElderChild();
		childNumber=elder.getElderChildNumber();
		area=elder.getElderArea();
		if(!elder.equals(this.getElder(id))) return false;   //���������Ϣ��ƥ�䣬���������
		jdbcTemplateObject.update(SQL,child,childNumber,area,id);
		return true;
	}

	@Override
	public boolean deleteElder(String id) {
		// TODO Auto-generated method stub
		if(this.getElder(id)==null) return false;   //��������ڸ�id��elder�������޷�����ɾ������
		TransactionDefinition def=new DefaultTransactionDefinition();
		TransactionStatus status=transactionManager.getTransaction(def);
		String SQL="DELETE FROM Elder WHERE ID=?";
		String deleteRingData="DROP TABLE "+id+"";
		try {
			jdbcTemplateObject.update(SQL,id);
			jdbcTemplateObject.update(deleteRingData);
			transactionManager.commit(status);
			return true;
		}catch(DataAccessException e) {
			transactionManager.rollback(status);
			System.out.println(e); //***********************************��ɾ��
			return false;
		}

	}

	/**
	 * �÷�����δ�����ŷ�ʽ���
	 */
	@Override
	public String getNewID() {
		// TODO Auto-generated method stub
		String SQL="SELECT * FROM Elder";
		List<Elder> elders=jdbcTemplateObject.query(SQL, new ElderMapper());
		if(elders.isEmpty()) return "KW170001";
		String lastID=elders.get(elders.size()-1).getElderID();
		int IDNum=Integer.valueOf(lastID.substring(2, lastID.length())).intValue()+1;    //������
		String newID="KW"+IDNum;
		return newID;
	}

}
