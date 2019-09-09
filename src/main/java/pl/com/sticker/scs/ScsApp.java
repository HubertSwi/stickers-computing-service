package pl.com.sticker.scs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ScsApp {

	public static void main(String[] args) {
		SpringApplication.run(ScsApp.class, args);
	}

}

