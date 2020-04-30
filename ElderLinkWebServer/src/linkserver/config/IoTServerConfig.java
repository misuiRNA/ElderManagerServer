package linkserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import nettyServer.boot.LinkBoot;

@Configuration
@ComponentScan(basePackages="nettyServer")
public class IoTServerConfig {
	
	@Bean
	public LinkBoot linkBoot() {
		//
		LinkBoot boot=new LinkBoot(1514);
		Thread iotThread=new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					System.out.println("正在启动IoT服务");
					boot.start();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("启动IoT服务失败");
				}
			}}) ;
		iotThread.start();
		//
		return boot;
	}
	
	
}
