package learn.memcached.kafka;

import learn.memcached.kafka.seniver.Sender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext app  = SpringApplication.run(KafkaApplication.class, args);

		while(true){
			Sender sender = app.getBean(Sender.class);
			sender.sendMessage();
			Thread.sleep(500);
		}

	}
}
