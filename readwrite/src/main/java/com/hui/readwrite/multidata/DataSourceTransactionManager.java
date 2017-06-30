package com.hui.readwrite.multidata;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/30
 * @Time:10:38
 */
@Configuration
@EnableTransactionManagement
public class DataSourceTransactionManager {
    /**
     * 自定义事务
     * MyBatis自动参与到spring事务管理中，无需额外配置，
     * 只要org.mybatis.spring.SqlSessionFactoryBean引用的数据源与DataSourceTransactionManager引用的数据源一致即可
     * 否则事务管理会不起作用。
     * @return
     */
    @Resource(name = "writeDataSource")
    private DataSource dataSource;

    @Bean(name = "transactionManager")
    public org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManagers() {
        return new org.springframework.jdbc.datasource.DataSourceTransactionManager(dataSource);
    }
}

