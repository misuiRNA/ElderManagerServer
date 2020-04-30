package linkserver.authenticate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import linkserver.tools.LogUserAttribute;

public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		if("Bad credentials".equals(exception.getMessage())) {
			response.getWriter().print(LogUserAttribute.LOGIN_NOPERMISSION);    //no userDetails in DB
			return;
		}  
		else if("User account is locked".equals(exception.getMessage())) {
			response.getWriter().print(LogUserAttribute.LOGIN_WRONGPASSWORD);    //wrong password
			return;
		}
		response.getWriter().print(exception.getMessage());   //"LOGIN_FAILD\n"+
	}
}
