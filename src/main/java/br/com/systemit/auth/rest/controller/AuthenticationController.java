package br.com.systemit.auth.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth/authentication")
public class AuthenticationController {

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody String entity) {
        
        return ResponseEntity.ok().body("ok");
    }

    @GetMapping("/tokenValidate")
    public ResponseEntity<String> tokenValidate(@RequestBody String entity) {
        
        return ResponseEntity.ok().body("ok");
    }
    
    
}
