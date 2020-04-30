package linkserver.data.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import linkserver.base.pojo.Elder;

public class ElderMapper implements RowMapper<Elder> {

	@Override
	public Elder mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Elder elder=new Elder();
		elder.setElderID(rs.getString("ID"));
		elder.setElderName(rs.getString("name"));
		elder.setElderSex(rs.getString("sex"));
		elder.setElderBirthday(rs.getDate("birthday").toString());       //�����ԣ�ȷ���������ʽ
		elder.setElderChild(rs.getString("child"));
		elder.setElderChildNumber(rs.getString("childNumber"));
		elder.setElderArea(rs.getString("area"));
		return elder;
	}
	
}
