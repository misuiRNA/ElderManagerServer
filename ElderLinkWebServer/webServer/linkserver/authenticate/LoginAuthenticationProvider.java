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
		//����֤filter�����������¼�û��ķ�װ
		LoginUser userDetails=(LoginUser)userDetailsService.loadUserByUsername(authentication.getName());
		if(!authentication.getCredentials().equals(userDetails.getPassword())) {
			throw new BadCredentialsException(LogUserAttribute.LOGIN_WRONGPASSWORD);
		}
		//��װ��֤�ɹ�����û���Ϣ�����ظ�filter��������ʵ���ϣ����ȷ��ص�manager����manager���ظ�filter�����������ص���Ϣ��Ӧ������������Ϣ
		UsernamePasswordAuthenticationToken result=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
