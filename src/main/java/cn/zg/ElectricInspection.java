package cn.zg;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//
//@MapperScan("cn.zg.dao.inter") 

//spring注解扫描
@SpringBootApplication(scanBasePackages = "cn.zg")

// 开启spring-data-JPA
@EnableJpaRepositories("cn.zg.dao.inter")

// JPA实体类扫描
@EntityScan("cn.zg.entity")
@EnableTransactionManagement
@Configuration
public class ElectricInspection extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ElectricInspection.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 注意这里要指向原先用main方法执行的Application启动类
		return builder.sources(ElectricInspection.class);
	}
	
	/**
	 * 对上传文件的配置
	 * @return MultipartConfigElement配置实例
	 * 
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 设置单个附件大小上限值(默认为1M)
		//选择字符串作为参数的话，单位可以用MB、KB;
		factory.setMaxFileSize("10MB");
		// 设置所有附件的总大小上限值
		factory.setMaxRequestSize("50MB");
		return factory.createMultipartConfig();
	}
}
