package com.turing.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.turing",excludeFilters = {
		@Filter(type=FilterType.ANNOTATION,value= {
				EnableWebMvc.class,Controller.class
		})
})
@MapperScan(basePackages = "com.turing.mapper")
@ImportResource("classpath:spring-transaction.xml")
public class RootConfig {

	// ��������Դ
	// ��������Դ
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mysql:///car");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");

		// ������Ҫ��
		dataSource.setUrl("jdbc:mysql:///car");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		// ���ӳص�һЩ����
		dataSource.setInitialSize(20);// ��ʼ������
		dataSource.setMaxIdle(10);// ���������
		dataSource.setMinIdle(2);// ��С������
		dataSource.setMaxTotal(50);// ���������
		dataSource.setMaxWaitMillis(5000);// ������ӵȴ�ʱ��

		return dataSource;
	}
	
	/**
	 * ����SQLsessionFactory,������֮ǰ��Mybatis-config.xml
	 * @throws Exception 
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
		//��������Դ
		bean.setDataSource(dataSource);
		return bean.getObject();		
	}
	
	//�������������
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
		
	}
	
	
}
