package com.springboot.memcached.annotation;

import com.springboot.memcached.config.MemcachedAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/16
 * @Time:11:32
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({MemcachedAutoConfiguration.class})
@Documented
public @interface EnableMemcached {
}
