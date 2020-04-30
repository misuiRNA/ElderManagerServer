package linkserver.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages="linkserver",
				excludeFilters= {@Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)})
public class RootConfig {
	
	@Bean  //数据库连接实例
	public DataSource dataSource() {
		DriverManagerDataSource source=new DriverManagerDataSource();
		source.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		source.setUrl("jdbc:sqlserver://localhost:1433; DatabaseName=ElderLinkServer");
		source.setUsername("rna");
		source.setPassword("rna1564");
		return source;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager manager=new DataSourceTransactionManager();
		manager.setDataSource(dataSource());
		return manager;
	}	
}
