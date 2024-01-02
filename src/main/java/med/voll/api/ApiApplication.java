package med.voll.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {//Esta classe carrega e inicializa todas as configurações e tomcat.
		SpringApplication.run(ApiApplication.class, args);
	}

}
