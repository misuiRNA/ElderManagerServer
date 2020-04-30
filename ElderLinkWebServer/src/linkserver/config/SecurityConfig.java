package linkserver.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import linkserver.authenticate.LoginAuthenticationProvider;
import linkserver.authenticate.LoginFailureHandler;
import linkserver.authenticate.LoginFilter;
import linkserver.authenticate.LoginSuccessHandler;
import linkserver.authenticate.LoginUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Bean		//UserDetailsService��bean����LoginUserServiceʵ����
	public UserDetailsService userDetailsService() {
		LoginUserService userDetailsService=new LoginUserService();
		return userDetailsService;
	}
	@Bean
	public AuthenticationProvider loginProvider() {
		LoginAuthenticationProvider provider=new LoginAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		return provider;
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		AuthenticationManager manager=new AuthenticationManager(){
			@Override   
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
			return loginProvider().authenticate(authentication);    
			}
			}; 
		return manager;
		}
	@Bean
	public LoginFilter loginFilter() throws Exception {
		LoginFilter filter=new LoginFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		filter.setAuthenticationSuccessHandler(new LoginSuccessHandler());
		filter.setAuthenticationFailureHandler(new LoginFailureHandler());
		filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
		return filter;
	}
	protected void configure(HttpSecurity http) throws Exception{
		//����csrf����������������Ӧ�����Ǽ���
		http.csrf().disable();
		http
			.formLogin()
				//��ʾ403��ת���ҳ�棬��ѡ��ҳ������·��
				.loginPage("/WEBSRC/noAuthenticated.txt")
			.and()
			.authorizeRequests()
				//���ÿɷ���·��
				.antMatchers("/WEBSRC/*").permitAll()
				.anyRequest().authenticated();
		http
			//�ö���filter�滻ԭ����֤filter
			.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
		http
			.logout()											//����ע������
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))	//����ע��url
				.logoutSuccessUrl("/WEBSRC/logoutSuccess.txt")						//����ע����ķ����ַ���ΪLOGOUTSECCESS
				.clearAuthentication(true)											//�����֤����
				.invalidateHttpSession(true);										//���session��Ϣ
	}
}
