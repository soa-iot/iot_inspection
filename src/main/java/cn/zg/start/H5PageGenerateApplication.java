package cn.zg.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.zg.dao.inter")  
@SpringBootApplication(scanBasePackages = "cn.zg")
public class H5PageGenerateApplication {

	public static void main(String[] args) {
		SpringApplication.run(H5PageGenerateApplication.class, args);
	}		
	
}
