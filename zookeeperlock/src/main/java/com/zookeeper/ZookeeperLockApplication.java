package com.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.zookeeper.distributeLock")
public class ZookeeperLockApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZookeeperLockApplication.class, args);
	}
}
