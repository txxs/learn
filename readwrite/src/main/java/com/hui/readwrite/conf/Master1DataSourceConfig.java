package com.hui.readwrite.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/28
 * @Time:10:59
 */
/*@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = Master1DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "master1SqlSessionFactory")*/
public class Master1DataSourceConfig {
/*
    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.hui.readwrite.mapper.master1";
    static final String MAPPER_LOCATION = "classpath:mapper/master1*//*.xml";

    @Value("${master1.datasource.url}")
    private String url;

    @Value("${master1.datasource.username}")
    private String user;

    @Value("${master1.datasource.password}")
    private String password;

    @Value("${master1.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "master1DataSource")
    @Primary
    public DataSource masterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "master1TransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "master1SqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("master1DataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(Master1DataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }*/
}
