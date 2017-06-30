package com.hui.readwrite.multidata;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/30
 * @Time:10:35
 */
@Configuration
@ConditionalOnClass({EnableTransactionManagement.class})
@Import({ DataBaseConfiguration.class})
@MapperScan(basePackages={"com.hui.readwrite.mapper.master1"})
public class TxxsbatisConfiguration {

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Value("${datasource.readSize}")
    private String dataSourceSize;

    @Resource(name = "writeDataSource")
    private DataSource dataSource;

    @Resource(name = "readDataSources")
    private List<DataSource> readDataSources;

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(roundRobinDataSouceProxy());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.hui.readwrite.po");
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/master1*//*.xml"));
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }
    /**
     * 有多少个数据源就要配置多少个bean
     * @return
     */
    @Bean
    public AbstractRoutingDataSource roundRobinDataSouceProxy() {
        int size = Integer.parseInt(dataSourceSize);
        TxxsAbstractRoutingDataSource proxy = new TxxsAbstractRoutingDataSource(size);
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        // DataSource writeDataSource = SpringContextHolder.getBean("writeDataSource");
        // 写
        targetDataSources.put(DataSourceType.write.getType(),dataSource);
        // targetDataSources.put(DataSourceType.read.getType(),readDataSource);
        //多个读数据库时
        for (int i = 0; i < size; i++) {
            targetDataSources.put(i, readDataSources.get(i));
        }
        proxy.setDefaultTargetDataSource(dataSource);
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }
}
