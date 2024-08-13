package br.com.systemit.auth.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "isn_user")
    private Integer id;

    @Column(name = "dsc_name", length = 250)
    @Length(min = 5, max = 250)
    private String name;

    @Column(name = "dsc_nickname", length = 50)
    @Length(min = 5, max = 50)
    private String nickname;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dth_registration_date")
    private LocalDateTime registrationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dth_last_access")
    private LocalDateTime lastAccess;

    @Column(name = "dsc_login", length = 20)
    @Length(min = 5, max = 20)
    private String login;

    @Column(name = "dsc_password", length = 100)
    @Length(min = 8, max = 100)
    private String password;

    @Column(name = "dsc_last_password", length = 100)
    @Length(min = 8, max = 100)
    private String lastPassword;

    @Column(name = "flg_first_access")
    private Boolean firstAccess;

    @Column(name = "flg_status")
    private Boolean status;

    @Column(name = "num_attenpt")
    private Integer attempt;

    @Column(name = "dsc_email", length = 100)
    @Length(min = 7, max = 100)
    private String email;
}
