package cn.zg.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web 配置类
 * @author Jiang, Hang
 * @date  2019/12/30
 */
@Configuration
@PropertySource(value="classpath:configProperties/fileDownUpload.properties", encoding="UTF-8")
public class WebConfig implements WebMvcConfigurer {
	
	/**
     * 跨域支持
     * @param registry
     */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
        .allowedOrigins("*")
        .allowCredentials(true)
        .allowedMethods("GET", "POST", "DELETE", "PUT")
        .maxAge(3600 * 24);
	}
	
	/**
	 * 静态资源映射
	 */
	@Value("${task.file.path}")
	private String filePath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("filePath="+filePath);
		registry.addResourceHandler("/img/**").addResourceLocations(filePath);
	}
}
