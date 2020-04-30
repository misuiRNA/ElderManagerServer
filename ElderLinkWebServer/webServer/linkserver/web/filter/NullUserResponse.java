package linkserver.web.filter;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import linkserver.base.pojo.LogUser;

/**
 * 
 * @author Misui_user
 ****@Pointcut
 ****@After
 ****@Before
 ****@AfterRunning
 ****@AfterThrowing
 ****@Around
 */
@Aspect
@Component
public class NullUserResponse {
	
	@Pointcut("execution(* linkserver.web.controller.LogUserController.queryLogUser(..))")
	public void declearAop() {}
	
	@Before("declearAop()")
	public void beforeFindLogUser() {
		
	}
	
	@AfterReturning(pointcut="declearAop()",returning="user")
	public void afterReturnLogUser(Object user) {
		if(user.equals("null")) return;
//		System.out.println("user have login!");
		LogUser luser=(LogUser)user;
		System.out.println("user:"+luser.getUserName()+"--"+luser.getRealName()+" have login!");
	}
	
}
