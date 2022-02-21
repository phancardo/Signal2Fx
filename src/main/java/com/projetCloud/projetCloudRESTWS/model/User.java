package com.projetCloud.projetCloudRESTWS.model;

import com.projetCloud.projetCloudRESTWS.util.ValidEmail;
import com.projetCloud.projetCloudRESTWS.util.ValidPassword;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@Table(name="auth_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @ValidPassword
    @Column(name = "motdepasse")
    private String motDePasse;


    @OneToOne
    @JoinTable(name = "chief_region",
            joinColumns = @JoinColumn(name = "iduser",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "idregion",referencedColumnName = "id"))
    private Region region;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "iduser",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "idrole",referencedColumnName = "id"))
    private Set<Role> roles;
}
