package learn.memcached.config;

import net.spy.memcached.spring.MemcachedClientFactoryBean;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/15
 * @Time:10:38
 */
//@Configuration
public class MemcacheConfig extends CachingConfigurerSupport {

/*    @Bean
    public MemcachedClientFactoryBean getMemcachedClientFactoryBean(){
        MemcachedClientFactoryBean memcachedClientFactoryBean = new MemcachedClientFactoryBean();
        memcachedClientFactoryBean.setServers("127.0.0.1:11211");
        return memcachedClientFactoryBean;
    }*/
}
