package cn.edu.scau.cmi.chenmengfu.redpacket.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
//定义Spring MVC扫描的包
@ComponentScan(value="cn.edu.scau.cmi.chenmengfu.redpacket.*",
	includeFilters= {@Filter(type=FilterType.ANNOTATION,value=Controller.class)})
//启动spring mvc配置
@EnableWebMvc
public class WebConfig {
	/**
	 * 通过注解@Bean初始化视图解析器
	 * @return resolver 视图解析器
	 */
	@Bean(name="internalResourceViewResolver")
	public ViewResolver initViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean(name="requestMappingHandlerAdapter")
	public HandlerAdapter initRequestMappingHandlerAdapter() {
		//创建RequestMappingHandlerAdapter适配器
		RequestMappingHandlerAdapter rmha = new RequestMappingHandlerAdapter();
		//HTTP JSON转换器
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		MediaType mediaType=MediaType.APPLICATION_JSON_UTF8;
		List<MediaType> mediaTypes=new ArrayList<>();
		mediaTypes.add(mediaType);
		jsonConverter.setSupportedMediaTypes(mediaTypes);
		rmha.getMessageConverters().add(jsonConverter);
		return rmha;
	}
}
