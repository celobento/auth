package br.com.systemit.auth.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CredentialDTO {
    private String login;
    private String password;
}
