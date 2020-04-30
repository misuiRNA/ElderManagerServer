package test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import linkserver.base.pojo.LogUser;
import linkserver.data.handler.LogUserJDBC;

public class UserJdbcTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		LogUserJDBC userJDBCTemplate=(LogUserJDBC)context.getBean("logUserJDBCTemplate");
		
//		createTest(userJDBCTemplate);
		
		updateTest(userJDBCTemplate);
		
//		listTest(userJDBCTemplate);
		
//		queryTest(userJDBCTemplate);
		
//		deleteTest(userJDBCTemplate);
		
	}
	
	public static void createTest(LogUserJDBC jdbc) {
		System.out.println("���ڿ�ʼ��������......");
		LogUser user=new LogUser("jerry1","215039",0,"612726198702281965","��ķ","��","10002","1985/09/16","tom");
		if(jdbc.createUser(user)) {
			System.out.println("����ɹ���");
		}else {
			System.out.println("����ʧ�ܣ����������ԡ�����������");
		}
		listTest(jdbc);
	}
	
	public static void listTest(LogUserJDBC jdbc) {
		System.out.println("LogUser�����������£�");
		List<LogUser> users=jdbc.listUser();
		for(LogUser user:users) {
			System.out.println("name:"+user.getUserName()+"--ID:"+user.getIdCard()+"--isAdmin:"+
		                        user.getIsAdmin()+"--superior:"+user.getSuperior()+
		                        "--password:"+user.getUserPassword());
		}
	}

	public static void updateTest(LogUserJDBC jdbc) {
		LogUser user=new LogUser("jerry","123456",0,"612726198702281965","��ķ","��","10002","1985/09/16","tom");
		jdbc.updateUser(user);
		listTest(jdbc);
	}
	
	public static void queryTest(LogUserJDBC jdbc) {
		LogUser user=jdbc.getUser("tom");
		if(user==null) user=new LogUser();
		System.out.println("name:"+user.getUserName()+"--ID:"+user.getIdCard()+"--isAdmin:"+
                user.getIsAdmin()+"--superior:"+user.getSuperior()+
                "--password:"+user.getUserPassword());
	}
	
	public static void deleteTest(LogUserJDBC jdbc) {
		if(jdbc.DeleteUser("jerry1"))
		{
			System.out.println("ɾ���ɹ�������");
		}else {
			System.out.println("ɾ��ʧ�ܣ������ԣ�����");
		}
		listTest(jdbc);
	}
}
