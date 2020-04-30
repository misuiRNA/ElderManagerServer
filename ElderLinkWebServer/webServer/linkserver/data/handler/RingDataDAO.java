package linkserver.data.handler;

import java.util.List;
import javax.sql.DataSource;

import linkserver.base.pojo.RingData;

public interface RingDataDAO {
	/**
	 * 注入DataSource
	 * @param ds
	 */
	public void setDataSource(DataSource ds);
	/**
	 * 插入手环数据
	 * @param ringData
	 * @return
	 */
	public boolean createData(RingData ringData);
	/**
	 * 根据老人id获取最后一条手环数据
	 * @param id
	 * @return
	 */
	public RingData getData(String id);
	/**
	 * 列举指定老人指定时间断内的手环数据
	 * @param id
	 * @param startD
	 * @param endD
	 * @return
	 */
	public List<RingData> listData(String id,String startD,String endD);
}
