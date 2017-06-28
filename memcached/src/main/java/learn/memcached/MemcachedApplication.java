package learn.memcached;


import com.springboot.memcached.annotation.EnableMemcached;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMemcached
public class MemcachedApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemcachedApplication.class, args);
	}
}
