package cn.edu.scau.cmi.chenmengfu.redpacket.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
//定义Spring扫描的包
@ComponentScan(value="cn.edu.scau.cmi.chenmengfu.redpacket.*",includeFilters={
		@Filter(type=FilterType.ANNOTATION,value= {Service.class})
})
@EnableTransactionManagement
public class RootConfig implements TransactionManagementConfigurer{

	private DataSource dataSource = null;
	/**
	 * 配置数据库
	 * @return
	 */
	@Bean(name="dataSource")
	public DataSource initDataSource() {
		if(dataSource != null) {
			return dataSource;
		}
		Properties properties = new Properties();
		properties.setProperty("driverClassName", "com.mysql.jdbc.Driver");
		properties.setProperty("url", "jdbc:mysql://localhost:3306/chapter22");
		properties.setProperty("username", "root");
		properties.setProperty("password", "root");
		properties.setProperty("maxActive", "200");
		properties.setProperty("maxIdle", "20");
		properties.setProperty("maxWait", "30000");
		try {
			
			dataSource=BasicDataSourceFactory.createDataSource(properties);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dataSource;
	}
	/**
	 * 配置xxx
	 * @return
	 */
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactoryBean initSqlSessionFactory() {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(initDataSource());
		//配置mybatis配置文件
		Resource resource = new ClassPathResource("mybatis/mybatis-config.xml");
		sqlSessionFactoryBean.setConfigLocation(resource);
//		URLClassLoader
		return sqlSessionFactoryBean;
	}
	/**
	 * 通过自动扫描，发现MyBatis Mapper接口
	 * @return Mapper扫描器
	 */
	@Bean
	public MapperScannerConfigurer initMapperScannerConfigurer() {
		MapperScannerConfigurer msc = new MapperScannerConfigurer();
		msc.setBasePackage("cn.edu.scau.cmi.chenmengfu.redpacket.*");
		msc.setSqlSessionFactoryBeanName("sqlSessionFactory");
		msc.setAnnotationClass(Repository.class);
		return msc;
	}
	
	/**
	 * 实现接口方法，注册注解食物，当@Transactional使用时产生数据库事务
	 */
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		DataSourceTransactionManager manager = new DataSourceTransactionManager();
		manager.setDataSource(initDataSource());
		return manager;
	}
	
}
