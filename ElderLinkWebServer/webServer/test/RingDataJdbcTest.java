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
		System.out.println("��Ӧ��Ա��"+id+"�������һ������Ϊ��");
		RingData ring=jdbc.getData(id);
		System.out.println("��ţ�"+ring.getCurID()+"    ʱ�䣺"+ring.getDatetime()+"    ���ȣ�"+ring.getlng()+
				            "    γ�ȣ�"+ring.getlat()+"    ���ʣ�"+ring.getHeartRate());
	}
	
	public static void listTest(RingDataJDBC jdbc) {
		System.out.println("ָ��ʱ����ڵ��������£�");
		String startD,endD,id;
		id="KW170004";
		startD="2018/04/20 00:00:00";
		endD="2018/05/21 16:30:00";
		List<RingData> rings=jdbc.listData(id, startD, endD);
		for(RingData ring:rings) {
			System.out.println("��ţ�"+ring.getCurID()+"    ʱ�䣺"+ring.getDatetime()+"    ���ȣ�"+ring.getlng()+
		            "    γ�ȣ�"+ring.getlat()+"    ���ʣ�"+ring.getHeartRate());
		}
	}
	
	public static void createTest(RingDataJDBC jdbc) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("���ڿ�ʼ�������ݡ�����������.");
		RingData ring=new RingData("KW170004",df.format(new Date()),"108.77414","34.0402",88);
		jdbc.createData(ring);
//		listTest(jdbc);
	}
}
