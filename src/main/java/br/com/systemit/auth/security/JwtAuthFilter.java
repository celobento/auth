package br.com.systemit.auth.security;


import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.systemit.auth.service.impl.UserServiceimpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserServiceimpl userService;

    public JwtAuthFilter( JwtService jwtService, UserServiceimpl userService ) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        String authorization = httpServletRequest.getHeader("Authorization");

        if( authorization != null && authorization.startsWith("Bearer")){
            String token = authorization.split(" ")[1];
            System.out.println("Token: " + token);
            boolean isValid = jwtService.tokenValido(token);
            System.out.println("Token Valido: " + isValid);
            if(isValid){
                String loginUsuario = jwtService.obterLoginUsuario(token);
                UserDetails user = userService.loadUserByUsername(loginUsuario);
                UsernamePasswordAuthenticationToken userAuth = new
                        UsernamePasswordAuthenticationToken(user,null,
                        user.getAuthorities());
                userAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(userAuth);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
