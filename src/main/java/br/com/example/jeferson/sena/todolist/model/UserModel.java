package br.com.example.jeferson.sena.todolist.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data // getter and setter (Lombok) para todos os atributos de uma vez
@Entity(name = "tb_users")
public class UserModel {
    
    @Id // Transformando em chave primaria.
    @GeneratedValue(generator = "UUID") //geração de id de forma automática pelo spring
    private UUID id; 

    @Column(unique = true) // no banco de dados - um atributo unico
    private String username;
    private String name;
    private String password;

    // getters and setters (Antes)
    /*
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

     */
    
    @CreationTimestamp
    private LocalDateTime creatadAt; // tempo em que o atributo/dado no banco de dados foi criado

}
