package test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import linkserver.base.pojo.Elder;
import linkserver.data.handler.ElderJDBC;

public class ElderJdbcTest {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		ElderJDBC elderJDBCTemplate=(ElderJDBC)context.getBean("elderJDBCTemplate");
		
//		creatTest(elderJDBCTemplate);
		
//		updateTest(elderJDBCTemplate);
		
//		queryTest(elderJDBCTemplate);
		
//		deleteTest(elderJDBCTemplate);
		
		listTest(elderJDBCTemplate);
		
//		getNewID(elderJDBCTemplate);
	}
	
	public static void creatTest(ElderJDBC jdbc) {
		System.out.println("现在开始插入数据。。。。。。");
		Elder elder=new Elder("KW170003","王五","男","1953/02/08","1245","张筱雨","1255");
		if(jdbc.createElder(elder)) {
			System.out.println("插入成功。。。。。。\n表中数据详情如下：");
		}else {
			System.out.println("插入失败，请重试。。。。。。");
		}

		listTest(jdbc);
	}
	
	public static void queryTest(ElderJDBC jdbc) {
		Elder elder=jdbc.getElder("KW170003");
		if(elder==null) elder=new Elder();
		System.out.println("编号："+elder.getElderID()+"--姓名："+elder.getElderName()+"--生日："+elder.getElderBirthday()+
		                   "--性别："+elder.getElderSex()+"--活动范围："+elder.getElderArea()+
		                   "--监护人："+elder.getElderChild()+"--联系方式："+elder.getElderChildNumber());
	}
	
	public static void listTest(ElderJDBC jdbc) {
		List<Elder> elders=jdbc.listElder();
		for(Elder elder:elders)
		{
			System.out.println("编号："+elder.getElderID()+"--姓名："+elder.getElderName()+"--生日："+elder.getElderBirthday()+
					           "--性别："+elder.getElderSex()+"--活动范围："+elder.getElderArea()+
					           "--监护人："+elder.getElderChild()+"--联系方式："+elder.getElderChildNumber());	
		}
	}
	
	public static void updateTest(ElderJDBC jdbc) {
		System.out.println("正在修改数据。。。。。。");
		Elder elder=new Elder("KW170003","李四","男","1953/02/08","5528","liasi","6666");
		jdbc.updateElder(elder);
		System.out.println("修改成功。。。。。。\n表中数据详情如下：");
		listTest(jdbc);
	}
	
	public static void deleteTest(ElderJDBC jdbc) {
		System.out.println("正在删除数据。。。。。。");
		if(jdbc.deleteElder("KW170001")) {
			System.out.println("删除成功。。。。。。\n表中数据详情如下：");
		}else {
			System.out.println("操作失败！\n请重试！");
		}
		listTest(jdbc);
	}
	
	public static void getNewID(ElderJDBC jdbc) {
		String newID=jdbc.getNewID();
		System.out.println("获得的ID为："+newID);
	}

}
