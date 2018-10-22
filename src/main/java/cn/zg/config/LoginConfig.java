package cn.zg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import cn.zg.intercepter.LoginInterceptor;

@Configuration 
public class LoginConfig extends WebMvcConfigurationSupport  {
	//不需要登录验证的urls
	private final String[] noValidateUrls = 
		{ "/html/process/login.html", "/css/**", "/package/**", "/process/**",
				"/javaScript/**", "/picture/**"};
	
	@Autowired  
	LoginInterceptor loginInterceptor;  
	
	@Override    
	protected void addInterceptors( InterceptorRegistry registry) {    
		 registry.addInterceptor( loginInterceptor ).addPathPatterns("/**")        
			.excludePathPatterns( noValidateUrls );     
		super.addInterceptors(registry);   		
	}

	
	/**   
	 * <p>Title: addResourceHandlers</p>   
	 * Description: 定义请求资源的路径
	 * @param registry   
	 */ 
	@Override    
	protected void addResourceHandlers( ResourceHandlerRegistry registry) {  
		registry.addResourceHandler( "/**" )
				.addResourceLocations( "classpath:/static/");  
		super.addResourceHandlers( registry );    
	}     
	
	
	/**   
	 * <p>Title: addViewControllers</p>   
	 * <p>Description: 一个请求映射成视图，无需书写控制器，
	 * 				addViewCOntroller("请求路径").setViewName("请求页面文件路径")	 
	 * @param registry   
	 
	@Override	
	public void addViewControllers( ViewControllerRegistry registry) {		
		registry.addViewController("/show/getLogin").setViewName("login");	
	}
	*/ 
	@Bean    
	public LoginInterceptor userInterceptor() {     
		return new LoginInterceptor();   
	}

}
