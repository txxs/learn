package learn.memcached;

import com.btmatthews.springboot.memcached.EnableMemcached;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMemcached
public class MemcachedApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemcachedApplication.class, args);
	}
}
