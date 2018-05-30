package cn.edu.scau.cmi.chenmengfu.redpacket.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"*.do"};
	}

	/**
	 * @param registration Servlet上传文件配置 
	 */
	@Override
	protected void customizeRegistration(Dynamic registration) {
		//配置文件上传路径
		String filePath ="d:/mvc/uploads";
		//5MB
		Long singleMax=(long)(5*Math.pow(2, 20));
		//10MB
		Long totalMax=(long)(10*Math.pow(2, 20));
		//设置上传文件配置
		registration.setMultipartConfig(new MultipartConfigElement(filePath, singleMax, totalMax, 0));
	}
}
