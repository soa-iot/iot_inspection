package cn.zg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//
//@MapperScan("cn.zg.dao.inter") 

//spring注解扫描
@SpringBootApplication(scanBasePackages = "cn.zg")

//开启spring-data-JPA
@EnableJpaRepositories( "cn.zg.dao.inter" )

//JPA实体类扫描
@EntityScan("cn.zg.entity")
public class ElectricInspection extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ElectricInspection.class, args);
	}			

	 @Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        // 注意这里要指向原先用main方法执行的Application启动类
	        return builder.sources(ElectricInspection.class); 
	 }
}
