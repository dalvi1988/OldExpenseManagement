package com.chaitanya.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.chaitanya.*" })
@EnableTransactionManagement
@Import({ SecurityConfig.class })
public class AppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/jqwidgets/**").addResourceLocations("classpath:/jqwidgets/");
		registry.addResourceHandler("/demos/**").addResourceLocations("classpath:/demos/");
		registry.addResourceHandler("/mobiledemos/**").addResourceLocations("classpath:/mobiledemos/");
		registry.addResourceHandler("/scripts/**").addResourceLocations("classpath:/scripts/");
	}	
	
	@Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder
        	.scanPackages("com.chaitanya.login.model")
        	.scanPackages("com.chaitanya.department.model")
        	.scanPackages("com.chaitanya.employee.model")
        	.scanPackages("com.chaitanya.masters.company")
            .addProperties(getHibernateProperties());

        return builder.buildSessionFactory();
    }

	private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return prop;
    }
	
	@Bean
	public BasicDataSource dataSource() {
		
		BasicDataSource ds = new BasicDataSource();
	    ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/businessreimbursment");
		ds.setUsername("root");
		ds.setPassword("Pramila21");
		return ds;
	}
	
	@Bean
    public HibernateTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory());
    }
		
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
    public RequestMappingHandlerAdapter  annotationMethodHandlerAdapter()
    {
        final RequestMappingHandlerAdapter annotationMethodHandlerAdapter = new RequestMappingHandlerAdapter();
        final MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();

        List<HttpMessageConverter<?>> httpMessageConverter = new ArrayList<HttpMessageConverter<?>>();
        httpMessageConverter.add(mappingJacksonHttpMessageConverter);

        String[] supportedHttpMethods = { "POST", "GET", "HEAD" };

        annotationMethodHandlerAdapter.setMessageConverters(httpMessageConverter);
        annotationMethodHandlerAdapter.setSupportedMethods(supportedHttpMethods);

        return annotationMethodHandlerAdapter;
    }
	
}