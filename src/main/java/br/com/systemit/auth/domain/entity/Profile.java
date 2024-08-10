package br.com.systemit.auth.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "isn_profile")
    private Integer id;

    @Column(name = "dsc_name", length = 50)
    @NotEmpty(message = "{field.name.required}")
    private String name;

    @Column(name = "dsc_description", length = 250)
    @NotEmpty(message = "{field.description.required}")
    private String description;

    @Column(name = "dsc_role", length = 50)
    @NotEmpty(message = "{field.role.required}")
    private String role;

    @Column(name = "flg_status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "isn_module", referencedColumnName = "isn_module")
    private Module module;

}
