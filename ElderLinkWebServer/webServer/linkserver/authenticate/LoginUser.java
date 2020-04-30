package linkserver.authenticate;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUser implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1602381854954988652L;
	
	private String username;
	
	private String password;
	
	private String loginStatus;
	
	private boolean isEnabled;
	
	private Collection<? extends GrantedAuthority> authorities;				//存放用户角色，为管理员或者普通用户，实际上只存放了一种角色
	
	public LoginUser(String username,String password,boolean isEnabled) {
		this.username=username;
		this.password=password;
		this.isEnabled=isEnabled;
	}
	/**
	 * 构造方法，构造实例并初始化
	 * @param username 用户名
	 * @param password 密码
	 * @param isEnabled 用户是否可见属性——目前尚未启用，默认true
	 * @param authorities 用户身份，管理员或者普通用户
	 */
	public LoginUser(String username,String password,boolean isEnabled,Collection<? extends GrantedAuthority> authorities) {
		this.username=username;
		this.password=password;
		this.isEnabled=isEnabled;
		this.authorities=authorities;
		for(GrantedAuthority authority:authorities) {
			loginStatus=authority.getAuthority();
		}
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	public String getLoginStatus() {
		return this.loginStatus;
		
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.isEnabled;
	}

}
