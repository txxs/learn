package com.hui.readwrite.multidata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/30
 * @Time:10:21
 */
@Configuration
public class DataBaseConfiguration{

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name="writeDataSource", destroyMethod = "close", initMethod="init")
    @Primary
    @ConfigurationProperties(prefix = "spring.write.datasource")
    public DataSource writeDataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
    /**
     * 有多少个从库就要配置多少个
     * @return
     */
    @Bean(name = "readDataSourceOne")
    @ConfigurationProperties(prefix = "spring.read.one")
    public DataSource readDataSourceOne(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "readDataSourceTwo")
    @ConfigurationProperties(prefix = "spring.read.two")
    public DataSource readDataSourceTwo() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean("readDataSources")
    public List<DataSource> readDataSources(){
        List<DataSource> dataSources=new ArrayList<DataSource>();
        dataSources.add(readDataSourceOne());
        dataSources.add(readDataSourceTwo());
        return dataSources;
    }
}
