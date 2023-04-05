package cl.corona.bbookbrand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BbookBrandApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbookBrandApplication.class, args);
	}

}
