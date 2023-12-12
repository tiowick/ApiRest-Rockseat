package br.com.example.jeferson.sena.todolist.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.jeferson.sena.todolist.model.TaskModel;
import java.util.List;


public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    
    List<TaskModel> findByIdUser(UUID idUser);
    TaskModel findByIdAndIdUser(UUID id, UUID idUser);
}
