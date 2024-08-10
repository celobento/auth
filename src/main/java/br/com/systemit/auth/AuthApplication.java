package br.com.systemit.auth;


import br.com.systemit.auth.domain.entity.Module;
import br.com.systemit.auth.domain.reposit.ModuleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

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
