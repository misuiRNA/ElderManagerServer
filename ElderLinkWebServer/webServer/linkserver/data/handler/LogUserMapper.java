package linkserver.data.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import linkserver.base.pojo.LogUser;

public class LogUserMapper  implements RowMapper<LogUser> {

	@Override
	public LogUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		LogUser user=new LogUser();
		user.setIdCard(rs.getString("ID"));
		user.setUserName(rs.getString("name"));
		user.setIsAdmin(rs.getInt("isAdmin"));
		user.setSex(rs.getString("sex"));
		user.setNumber(rs.getString("number"));
		user.setBirthday(rs.getDate("birthday").toString());
		user.setUserPassword(rs.getString("password"));
		user.setRealName(rs.getString("realName"));
		user.setSuperior(rs.getString("superior"));
		return user;
	}

}
