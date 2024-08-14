package br.com.systemit.auth.service;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserIdentification {
    private String id;
    private String name;
    private String login;
    private List<String> roles;

    public UserIdentification(String id, String name, String login, List<String> roles) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.roles = roles;
    }

    public List<String> getRoles() {
        if(roles == null){
            roles = new ArrayList<>();
        }
        return roles;
    }
}
