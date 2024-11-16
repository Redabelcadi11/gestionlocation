package com.agencelocation.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private int numeroTelephone;
    private String permisConduire;
    private Role role;


    @OneToMany(mappedBy = "client")
    private List<ContratLocation> contrats;

    // Getters and setters
    public String getPassword(){
        return password;
    }

    public Role getRole(){
        return role;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setNumeroTelephone(int num) { this.numeroTelephone = num;}


    public void setRole(Role role){
        this.role = role;
    }

    public enum Role {
        ADMIN, CLIENT
    }
}
