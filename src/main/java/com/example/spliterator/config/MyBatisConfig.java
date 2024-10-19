package com.example.spliterator.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan({"com.example.spliterator.users.dao"})
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        // Configuration to map underscore to camelcase variables
        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
        config.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(config);

        // Configuration for setting Alias packages
        factoryBean.setTypeAliasesPackage("com.example.spliterator.*.models");

        factoryBean.setMapperLocations(
            new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml"));

        return factoryBean.getObject();
    }

}
