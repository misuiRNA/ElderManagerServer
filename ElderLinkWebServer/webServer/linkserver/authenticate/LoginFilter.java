package linkserver.authenticate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import linkserver.tools.LogUserAttribute;

public class LoginFilter extends AbstractAuthenticationProcessingFilter{
	
	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
	public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
	private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
	private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
	
	public LoginFilter() {
		super(new AntPathRequestMatcher("/login", "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		if(!request.getMethod().equals("POST")) {
			throw new  AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		String username=request.getParameter(usernameParameter);
		String password=request.getParameter(passwordParameter);
		if (username == null||password == null)  throw new BadCredentialsException(LogUserAttribute.LOGIN_NOPERMISSION);
		username = username.trim();
		UsernamePasswordAuthenticationToken authRequest=new UsernamePasswordAuthenticationToken(username,password); 
		Authentication authenResult=this.getAuthenticationManager().authenticate(authRequest);
		return authenResult;
	}
}
