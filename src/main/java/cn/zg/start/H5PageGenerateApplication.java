package cn.zg.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//
//@MapperScan("cn.zg.dao.inter") 

//spring注解扫描
@SpringBootApplication(scanBasePackages = "cn.zg")

//开启spring-data-JPA
@EnableJpaRepositories( "cn.zg.dao.inter" )

//JPA实体类扫描
@EntityScan("cn.zg.entity")
public class H5PageGenerateApplication {

	public static void main(String[] args) {
		SpringApplication.run(H5PageGenerateApplication.class, args);
	}		
	
}
