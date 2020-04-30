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
		System.out.println("���ڿ�ʼ�������ݡ�����������");
		Elder elder=new Elder("KW170003","����","��","1953/02/08","1245","������","1255");
		if(jdbc.createElder(elder)) {
			System.out.println("����ɹ�������������\n���������������£�");
		}else {
			System.out.println("����ʧ�ܣ������ԡ�����������");
		}

		listTest(jdbc);
	}
	
	public static void queryTest(ElderJDBC jdbc) {
		Elder elder=jdbc.getElder("KW170003");
		if(elder==null) elder=new Elder();
		System.out.println("��ţ�"+elder.getElderID()+"--������"+elder.getElderName()+"--���գ�"+elder.getElderBirthday()+
		                   "--�Ա�"+elder.getElderSex()+"--���Χ��"+elder.getElderArea()+
		                   "--�໤�ˣ�"+elder.getElderChild()+"--��ϵ��ʽ��"+elder.getElderChildNumber());
	}
	
	public static void listTest(ElderJDBC jdbc) {
		List<Elder> elders=jdbc.listElder();
		for(Elder elder:elders)
		{
			System.out.println("��ţ�"+elder.getElderID()+"--������"+elder.getElderName()+"--���գ�"+elder.getElderBirthday()+
					           "--�Ա�"+elder.getElderSex()+"--���Χ��"+elder.getElderArea()+
					           "--�໤�ˣ�"+elder.getElderChild()+"--��ϵ��ʽ��"+elder.getElderChildNumber());	
		}
	}
	
	public static void updateTest(ElderJDBC jdbc) {
		System.out.println("�����޸����ݡ�����������");
		Elder elder=new Elder("KW170003","����","��","1953/02/08","5528","liasi","6666");
		jdbc.updateElder(elder);
		System.out.println("�޸ĳɹ�������������\n���������������£�");
		listTest(jdbc);
	}
	
	public static void deleteTest(ElderJDBC jdbc) {
		System.out.println("����ɾ�����ݡ�����������");
		if(jdbc.deleteElder("KW170001")) {
			System.out.println("ɾ���ɹ�������������\n���������������£�");
		}else {
			System.out.println("����ʧ�ܣ�\n�����ԣ�");
		}
		listTest(jdbc);
	}
	
	public static void getNewID(ElderJDBC jdbc) {
		String newID=jdbc.getNewID();
		System.out.println("��õ�IDΪ��"+newID);
	}

}
