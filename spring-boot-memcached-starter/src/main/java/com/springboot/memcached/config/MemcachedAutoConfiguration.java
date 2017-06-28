package com.springboot.memcached.config;

import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/16
 * @Time:11:35
 */
@Configuration
public class MemcachedAutoConfiguration{
    private static final String LOCALHOST = "localhost";
    private static final int DEFAULT_PORT = 11211;
    @Autowired
    private Environment environment;

    public MemcachedAutoConfiguration() {
    }

    @Bean
    public MemcachedClient memcachedClient() throws IOException {
        ArrayList addresses = new ArrayList();
        String servers = this.environment.getProperty("memcached.servers");
        if(StringUtils.isEmpty(servers)) {
            addresses.add(new InetSocketAddress("localhost", 11211));
        } else {
            String[] var3 = servers.split(",");
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String server = var3[var5];
                int colon = server.indexOf(":");
                if(colon == -1) {
                    addresses.add(new InetSocketAddress(server, 11211));
                } else {
                    int port = Integer.parseInt(server.substring(colon + 1));
                    addresses.add(new InetSocketAddress(server.substring(0, colon), port));
                }
            }
        }

        return new MemcachedClient(addresses);
    }
}
