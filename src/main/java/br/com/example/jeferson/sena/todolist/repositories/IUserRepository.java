package br.com.example.jeferson.sena.todolist.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.jeferson.sena.todolist.model.UserModel;


public interface IUserRepository extends JpaRepository<UserModel, UUID>{  //Tipo de id e a entidade   // contrato dentro da aplicação, (Definição (Representação))
    UserModel findByUsername(String username); //select buscando por essa coluna "username"
}
