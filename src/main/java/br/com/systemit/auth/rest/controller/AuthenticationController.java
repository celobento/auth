package br.com.systemit.auth.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.systemit.auth.domain.dto.CredentialDTO;
import br.com.systemit.auth.domain.dto.TokenDTO;
import br.com.systemit.auth.domain.entity.User;
import br.com.systemit.auth.security.JwtService;
import br.com.systemit.auth.service.impl.UserServiceimpl;



@RestController
@RequestMapping("/auth/authentication")
public class AuthenticationController {

    @Autowired
    private UserServiceimpl usuarioService;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public TokenDTO authenticate(@RequestBody CredentialDTO credenciais) {
        System.out.println(">>>>>>>>>>>>>>>");
        
        try{
            
            User usuario = User.builder()
                    .login(credenciais.getLogin())
                    .password(credenciais.getPassword()).build();

            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            
            String token = jwtService.gerarToken(usuario);
            
            return TokenDTO.builder()
                           .login(usuario.getLogin())
                           .accessToken(token)
                           .build();
            
        } catch (Exception e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping("/tokenValidate")
    public ResponseEntity<String> tokenValidate(@RequestBody String entity) {
        
        return ResponseEntity.ok().body("ok");
    }
    
    
}
