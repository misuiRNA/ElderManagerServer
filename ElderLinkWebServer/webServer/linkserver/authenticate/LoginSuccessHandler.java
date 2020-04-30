package linkserver.authenticate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		System.out.println("---------------SUCCESS-----name:"+authentication.getName()+"----password:"+authentication.getCredentials());
		LoginUser user=(LoginUser)authentication.getPrincipal();
		response.getWriter().print(user.getLoginStatus());
	}
}
