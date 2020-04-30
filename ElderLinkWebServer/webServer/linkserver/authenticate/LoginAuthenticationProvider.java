package linkserver.authenticate;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

import linkserver.tools.LogUserAttribute;

public class LoginAuthenticationProvider implements AuthenticationProvider{
	
	private UserDetailsService userDetailsService;
	
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService=userDetailsService;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//由认证filter传来的请求登录用户的封装
		LoginUser userDetails=(LoginUser)userDetailsService.loadUserByUsername(authentication.getName());
		if(!authentication.getCredentials().equals(userDetails.getPassword())) {
			throw new BadCredentialsException(LogUserAttribute.LOGIN_WRONGPASSWORD);
		}
		//封装认证成功后的用户信息，返回给filter————实际上，会先返回到manager后有manager返回给filter————返回的信息中应当清除密码等信息
		UsernamePasswordAuthenticationToken result=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
