package linkserver.data.handler;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import linkserver.base.pojo.LogUser;

@Repository("userJDBCTemplate")
public class LogUserJDBC implements LogUserDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	@Autowired
	@Override
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		this.dataSource=ds;
		this.jdbcTemplateObject=new JdbcTemplate(dataSource);
	}

	@Override
	public boolean createUser(LogUser user) {
		// TODO Auto-generated method stub
		if(this.getUser(user.getUserName())!=null) return false;    //如果已经存在该用户名，则不允许创建
		String id,password,realName,name,number,sex,birthday,superior;
		Integer isAdmin;
		id=user.getIdCard();
		password=user.getUserPassword();
		realName=user.getRealName();
		name=user.getUserName();
		number=user.getNumber();
		isAdmin=user.getIsAdmin();
		sex=user.getSex();
		birthday=user.getBirthday();
		superior=user.getSuperior();;
		String SQL="INSERT INTO LogUser (ID,password,realName,name,number,isAdmin,sex,birthday,superior)"
				   + " VALUES (?,?,?,?,?,?,?,?,?)";
		jdbcTemplateObject.update(SQL,id,password,realName,name,number,isAdmin,sex,birthday,superior);
		
		return true;
	}

	@Override
	public LogUser getUser(String username) {
		// TODO Auto-generated method stub
		String SQL="SELECT * FROM LogUser WHERE name=?";
		List<LogUser> users=jdbcTemplateObject.query(SQL, new LogUserMapper(),username);
		if(users.size()==0) return null;
		return users.get(0);
		
	}

	@Override
	public List<LogUser> listUser() {
		// TODO Auto-generated method stub
		String SQL="SELECT * FROM LogUser ORDER BY ID ASC";
		List<LogUser> users=jdbcTemplateObject.query(SQL,new LogUserMapper());
		return users;
	}

	@Override
	public boolean updateUser(LogUser user) {
		// TODO Auto-generated method stub
		String password,name,realName;
		password=user.getUserPassword();
		name=user.getUserName();
		realName=user.getRealName();
		if(!user.equale(this.getUser(user.getUserName()))) return false;
		String SQL="UPDATE LogUser SET password=?,realName=? WHERE name=?";
		jdbcTemplateObject.update(SQL,password,realName,name);
		return true;
	}

	@Override
	public boolean DeleteUser(String username) {
		// TODO Auto-generated method stub
		if(this.getUser(username)==null) return false;
		String SQL="DELETE FROM LogUser WHERE name=?";
		jdbcTemplateObject.update(SQL,username);
		return true;
	}

}
