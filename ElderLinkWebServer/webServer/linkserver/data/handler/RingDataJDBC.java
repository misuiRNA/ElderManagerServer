package linkserver.data.handler;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import linkserver.base.pojo.RingData;

@Repository("ringDataJDBCTemplate")
public class RingDataJDBC implements RingDataDAO {
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
	public boolean createData(RingData ring) {
		// TODO Auto-generated method stub
		String SQL="INSERT INTO "+ring.getCurID()+" (curID,time,lng,lat,heartRate) VALUES (?,?,?,?,?)";
		String ID,time,lng,lat;
		Integer heartRate;
		ID=ring.getCurID();
		time=ring.getDatetime();
		lng=ring.getlng();
		lat=ring.getlat();
		heartRate=ring.getHeartRate();
		jdbcTemplateObject.update(SQL,ID,time,lng,lat,heartRate);
		return true;
	}

	@Override
	public RingData getData(String id) {
		// TODO Auto-generated method stub
		String SQL="SELECT TOP 1 * FROM "+id+" ORDER BY time DESC";
		List<RingData> rings=jdbcTemplateObject.query(SQL, new RingDataMapper());
		if(rings.size()==0) return null;
		return rings.get(0);
	}

	@Override
	public List<RingData> listData(String id, String startD, String endD) {
		// TODO Auto-generated method stub
		String SQL="SELECT * FROM "+id+" WHERE time BETWEEN ? AND ?";
		List<RingData> rings=jdbcTemplateObject.query(SQL, new RingDataMapper(),startD,endD);
		return rings;
	}

}
