package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import linkserver.base.pojo.RingData;
import linkserver.data.handler.RingDataJDBC;

public class RingDataJdbcTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		RingDataJDBC ringJDBC=(RingDataJDBC)context.getBean("RingDataJDBCTemplate");
		
//		createTest(ringJDBC);
		
//		listTest(ringJDBC);
		
//		queryTest(ringJDBC);
		
		for(int i=0;i<200;i++) {
			try {
				createTest(ringJDBC);
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		listTest(ringJDBC);
	}
	
	public static void queryTest(RingDataJDBC jdbc) {
		String id="KW170001";
		System.out.println("对应人员“"+id+"”的最后一条数据为：");
		RingData ring=jdbc.getData(id);
		System.out.println("编号："+ring.getCurID()+"    时间："+ring.getDatetime()+"    经度："+ring.getlng()+
				            "    纬度："+ring.getlat()+"    心率："+ring.getHeartRate());
	}
	
	public static void listTest(RingDataJDBC jdbc) {
		System.out.println("指定时间段内的数据如下：");
		String startD,endD,id;
		id="KW170004";
		startD="2018/04/20 00:00:00";
		endD="2018/05/21 16:30:00";
		List<RingData> rings=jdbc.listData(id, startD, endD);
		for(RingData ring:rings) {
			System.out.println("编号："+ring.getCurID()+"    时间："+ring.getDatetime()+"    经度："+ring.getlng()+
		            "    纬度："+ring.getlat()+"    心率："+ring.getHeartRate());
		}
	}
	
	public static void createTest(RingDataJDBC jdbc) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("现在开始插入数据。。。。。。.");
		RingData ring=new RingData("KW170004",df.format(new Date()),"108.77414","34.0402",88);
		jdbc.createData(ring);
//		listTest(jdbc);
	}
}
