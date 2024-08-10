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
@Table(name = "tb_module")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "isn_module")
    private Integer id;

    @Column(name = "dsc_name")
    @NotEmpty(message = "{field.name.required}")
    private String name;

    @Column(name = "dsc_description")
    @NotEmpty(message = "{field.description.required}")
    private String description;

}
