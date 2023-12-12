package br.com.example.jeferson.sena.todolist.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {
    
    /*
     * Id
     * Usuário (ID_USUARIO)
     * Descrição
     * Titulo
     * Data de inicio
     * Data de término
     */

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;
    
    @Column(length = 50)
    private String title;
    //Data e horário
    private LocalDateTime startAt; //quando começa a tarefa.
    private LocalDateTime endAt; // quando termina a tarefa.
    private String priority; // prioridade da tarefa

    private UUID idUser; // Associar o usuário com a tarefa.

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTitle(String title) throws Exception {
        if (title.length() > 50) {
            throw new Exception("O campo title deve conter no máximo 50 caracteres!");
        }
        this.title = title;
    }

    
}
