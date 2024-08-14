package br.com.systemit.auth.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.systemit.auth.domain.entity.User;
import br.com.systemit.auth.service.CustomAuthentication;
import br.com.systemit.auth.service.UserIdentification;
import br.com.systemit.auth.service.impl.UserServiceimpl;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserServiceimpl usuarioService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();
        String senha = (String) authentication.getCredentials();

        User user = usuarioService.getUserWithPermission(login);
        if(user != null){
            boolean senhasBatem = passwordEncoder.matches(senha, user.getPassword());
            if(senhasBatem){
                UserIdentification identificacaoUsuario = new UserIdentification(
                        user.getId().toString(),
                        user.getName(),
                        user.getLogin(),
                        user.getRoles()
                );

                return new CustomAuthentication(identificacaoUsuario);
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
