package br.com.systemit.auth;


import br.com.systemit.auth.domain.entity.Module;
import br.com.systemit.auth.domain.repository.ModuleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AuthApplication {

	@Autowired
	private ModuleRepository moduleRepository;

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			moduleRepository.save(Module.builder().name("ADMINISTRATIVE").description("MODULE FOR ADMINISTRATIVE DATA").build());
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
