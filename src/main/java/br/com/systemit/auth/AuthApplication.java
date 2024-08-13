package br.com.systemit.auth;


import java.time.LocalDateTime;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.systemit.auth.domain.entity.Module;
import br.com.systemit.auth.domain.entity.Profile;
import br.com.systemit.auth.domain.entity.User;
import br.com.systemit.auth.domain.entity.UserProfile;
import br.com.systemit.auth.domain.repository.ModuleRepository;
import br.com.systemit.auth.domain.repository.ProfileRepository;
import br.com.systemit.auth.domain.repository.UserProfileRepository;
import br.com.systemit.auth.domain.repository.UserRepository;
import br.com.systemit.auth.util.DateUtil;

@SpringBootApplication
public class AuthApplication {

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			// module config
			var authModule = moduleRepository.save(Module.builder().name("AUTHENTICATION").description("MODULE FOR AUTHENTICATION DATA").status(true).build());
			var financeModule = moduleRepository.save(Module.builder().name("FINANCE").description("FINANCE MODULE").status(true).build());
			
			// profile config
			var authAdmin = profileRepository.save(Profile.builder().name("Admin").role("auth-admin").description("profile to high access to auth module").status(true).module(authModule).build());
			profileRepository.save(Profile.builder().name("User").role("finance-user").description("profile to lower access to finance module").status(true).module(financeModule).build());
			profileRepository.save(Profile.builder().name("Admin").role("finance-admin").description("profile to high access to finance module").status(true).module(financeModule).build());

			// user config
			var user = userRepository.save(User.builder().name("Marcelo Bento")
											  .nickname("Bento")
											  .registrationDate(DateUtil.getDataFormatada("2024-08-11T18:28:00", DateUtil.PATTERN_DATE_TIME_US))
											  .lastAccess(DateUtil.getDataFormatada("2024-08-11T18:28:00", DateUtil.PATTERN_DATE_TIME_US))
											  .login("marcelobento")
											  .password(passwordEncoder().encode("12345678"))
											  .lastPassword(passwordEncoder().encode("12345678"))
											  .firstAccess(true)
											  .status(true)
											  .attempt(0)
											  .email("celobento26@gmail.com").build());
			// user profile config
			userProfileRepository.save(UserProfile.builder()
												  .user(user)
												  .profile(authAdmin)
												  .responsibleAdd("setup system")
												  .addedIn(LocalDateTime.now())
												  .status(true)
												  .build());
		};
	}

	@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
