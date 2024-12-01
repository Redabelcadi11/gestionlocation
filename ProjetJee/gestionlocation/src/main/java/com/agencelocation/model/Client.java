package com.agencelocation.model;

import jakarta.persistence.*;
import java.util.List;


@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private String numeroTelephone;
    private String permisConduire;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "client")
    private List<ContratLocation> contrats;

    // Getters and setters
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() { return email; }



    public void setNumeroTelephone(String num) {
        this.numeroTelephone = num;
    }
    public void setPermisConduire(String num) {
        this.numeroTelephone = num;
    }

    public String getNumeroTelephone() { return numeroTelephone; }
    public String getPermisConduire() { return permisConduire; }
    public void setRole(Role role) {
        this.role = role;
    }


    public enum Role {
        ROLE_ADMIN, ROLE_CLIENT  // Assurez-vous que les rôles sont bien formatés avec "ROLE_"
    }


}
