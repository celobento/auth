package br.com.systemit.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.systemit.auth.security.JwtAuthFilter;
import br.com.systemit.auth.security.JwtService;
import br.com.systemit.auth.service.impl.UserServiceimpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private UserServiceimpl usuarioService;

    @Autowired
    private JwtService jwtService;

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAuthenticationProvider customAuthenticationProvider) throws Exception {
        http.authorizeHttpRequests((authorize) -> 
                        authorize
                                .requestMatchers("/h2-console/**").permitAll()
                                .requestMatchers("/auth/**").permitAll()
                                //.requestMatchers("/admin/**").hasAnyRole("ADMIN")
                                //.requestMatchers("/api/users/**").hasAnyRole("user", "admin", "auth-admin")
                                //.requestMatchers(HttpMethod.POST, "/api/usuarios/**").permitAll()
                                .anyRequest().authenticated())
                .csrf((csrf) -> csrf.disable())
                .headers((headers) -> headers.frameOptions((frame) -> frame.sameOrigin()))
                //.httpBasic(Customizer.withDefaults())
                //.formLogin(Customizer.withDefaults())
                //.authenticationProvider(customAuthenticationProvider)
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

     /*@Bean
      public GrantedAuthorityDefaults grantedAuthorityDefaults(){
          return new GrantedAuthorityDefaults("");
      }*/

}
