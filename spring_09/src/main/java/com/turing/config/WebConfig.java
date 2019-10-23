package com.turing.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * springMVC����
 * @author 11954
 *
 */
@Configuration
@ComponentScan(basePackages = "com.turing.controller")
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
	
//	//������ͼ������
//	@Bean
//	public ViewResolver viewResolver() {
//		//������ͼ��������������ǰ׺��׺
//		InternalResourceViewResolver resolver=new InternalResourceViewResolver("/WEB-INF/JSP/", ".jsp");
//		//����bean������������Ҳ���Է���
//		resolver.setExposeContextBeansAsAttributes(true);
//		return resolver;
//	}
	
	//����ģ�������
	@Bean
	public ITemplateResolver templateResolver(ApplicationContext applicationContext) {
		//ģ�������
		SpringResourceTemplateResolver templateResolver=new SpringResourceTemplateResolver();
		//����������(IOC����)
		templateResolver.setApplicationContext(applicationContext);
		//����ǰ׺
		templateResolver.setPrefix("/");
		//���ú�׺
		templateResolver.setSuffix(".html");
		//����ģ������
		templateResolver.setTemplateMode(TemplateMode.HTML);
		//����ʱΪ�˵��Է��㣬����ҳ�滺��
		templateResolver.setCacheable(false);
		//���ñ���
		templateResolver.setCharacterEncoding("utf-8");
		return templateResolver;
	}
	
	//Springģ������
	@Bean
	public ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
		//ģ������
		SpringTemplateEngine templateEngine=new SpringTemplateEngine();
		//���ý�����
		templateEngine.setTemplateResolver(templateResolver);
		//֧��SpEL��Spring��EL����ʽ��
		templateEngine.setEnableSpringELCompiler(true);
		return templateEngine;
	}
	
	//��ͼ������
	@Bean
	public ViewResolver viewResolver(ISpringTemplateEngine templateEngine) {
		//����Thymeleaf��ͼ������
		ThymeleafViewResolver viewResolver=new ThymeleafViewResolver();
		//����ģ������
		viewResolver.setTemplateEngine(templateEngine);
		//���ñ���
		viewResolver.setCharacterEncoding("utf-8");
		return viewResolver;
	}
	
	//������̬��Դ����
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
			//����
			configurer.enable();	
	}
}