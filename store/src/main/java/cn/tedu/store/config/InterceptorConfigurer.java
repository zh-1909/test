package cn.tedu.store.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.tedu.store.interceptor.LoginInterceptor;
/**
 * 拦截器配置文件
 * @author tarena
 *
 */
@Configuration//加了才会设置为配置
public class InterceptorConfigurer implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		HandlerInterceptor interceptor = new LoginInterceptor();
		 
		List<String> patterns = new ArrayList<>();
		patterns.add("/users/reg");
		patterns.add("/users/login");
		patterns.add("/web/register.html");
		patterns.add("/web/login.html");
		patterns.add("/web/product.html");
		patterns.add("/web/index.html");
		patterns.add("/bootstrap3/**");
		patterns.add("/css/**");
		patterns.add("/images/**");
		patterns.add("/js/**");
		patterns.add("/districts/**"); 
		patterns.add("/products/**");
		//add Interceptor
		//添加拦截器
		registry.addInterceptor(interceptor)
		//add Path Patterns
		//添加路径模式
		.addPathPatterns("/**")
		//exclude Path Patterns
		//排除路径模式
		.excludePathPatterns(patterns);
	}

}
