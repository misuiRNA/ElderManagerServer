package linkserver.data.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import linkserver.base.pojo.RingData;

public class RingDataMapper implements RowMapper<RingData>{

	@Override
	public RingData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		String curID=rs.getString("curID");
		String time=rs.getDate("time").toString()+" "+rs.getTime("time").toString();
		String lng=rs.getString("lng");
		String lat=rs.getString("lat");
		Integer heartRate=rs.getInt("heartRate");
		RingData ring=new RingData(curID,time,lng,lat,heartRate);
		return ring;
	}

}
