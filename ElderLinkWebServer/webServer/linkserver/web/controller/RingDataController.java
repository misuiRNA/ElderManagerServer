package linkserver.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import linkserver.base.pojo.RingData;
import linkserver.data.handler.RingDataJDBC;
import linkserver.tools.RingAttribute;

@RestController
@RequestMapping(value="/ring",produces="application/json; charset=utf-8")
public class RingDataController {
	private RingDataJDBC template;
	
	@Autowired
	public void setTemplate(RingDataJDBC ringDataJDBCTemplate) {
		this.template=ringDataJDBCTemplate;
	}
	
	@RequestMapping(value="/listByTime/{elderID}/{startTime}/{endTime}",method=RequestMethod.GET)  //查询指定用户的指定时间段内的消息
	public Object queryRingDataByTime(@PathVariable("elderID") String id,
											  @PathVariable("startTime") String startD,
											  @PathVariable("endTime") String endD){
		List<RingData> datas=template.listData(id, startD, endD);
		if(datas.size()==0) return RingAttribute.GETDATABYTIME_NULL;
		return datas;
	}
	//查询指定老人的最新位置信息
	@RequestMapping(value="/lastRecord/{elderID}",method=RequestMethod.GET)
	public Object getLastRecord(@PathVariable("elderID") String id) {
		RingData data=template.getData(id);
		if(data==null) return RingAttribute.GETLASTDATA_NULL;
		return data;
	}
	
}
