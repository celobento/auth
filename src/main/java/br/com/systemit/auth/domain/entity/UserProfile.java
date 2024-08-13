package br.com.systemit.auth.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_user_profile")
public class UserProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "isn_user_profile")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "isn_user", referencedColumnName = "isn_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "isn_profile", referencedColumnName = "isn_profile")
    private Profile profile;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dth_added_in")
    private LocalDateTime addedIn;

    @Column(name = "dsc_responsible_add", length = 250)
    @NotEmpty(message = "{field.name.required}")
    private String responsibleAdd;

    @Column(name = "dsc_responsible_removedadd", length = 250)
    private String responsibleRemove;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dth_removed_in")
    private LocalDateTime removedIn;

    @Column(name = "flg_status")
    private Boolean status;

}
