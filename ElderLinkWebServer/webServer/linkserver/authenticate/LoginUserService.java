package linkserver.authenticate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import linkserver.tools.LogUserAttribute;

public class LoginUserService implements UserDetailsService{
	
	private JdbcTemplate template;
	
	private final String sqlUser;
	
	private final RowMapper<LoginUser> userRowMapper;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template=new JdbcTemplate(dataSource);
	}
	
	public LoginUserService() {
		super();
		sqlUser="SELECT name,password,isAdmin FROM LogUser WHERE name=?";
		userRowMapper=new RowMapper<LoginUser>() {
			@Override
			public LoginUser mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				int isAdmin=rs.getInt("isAdmin");
				ArrayList<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
				if(isAdmin==1) {
					authorities.add(new SimpleGrantedAuthority(LogUserAttribute.LOGIN_ADMINSTRATOR));			//管理员权限
				}else {
					authorities.add(new SimpleGrantedAuthority(LogUserAttribute.LOGIN_USERPERMIT));				//普通用户权限
				}
				LoginUser user=new LoginUser(rs.getString("name"), rs.getString("password"), true, authorities);
				return user;
			}
		};
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		try {
		LoginUser userfromDB=template.queryForObject(sqlUser, userRowMapper,username);
		return userfromDB;
		}catch(EmptyResultDataAccessException e) {
			throw new UsernameNotFoundException(LogUserAttribute.LOGIN_NOPERMISSION);
		}
	}

}
