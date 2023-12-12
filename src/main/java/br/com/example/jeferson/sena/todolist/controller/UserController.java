package br.com.example.jeferson.sena.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.example.jeferson.sena.todolist.model.UserModel;
import br.com.example.jeferson.sena.todolist.repositories.IUserRepository;


@RestController
@RequestMapping("/users")
//localhost:8080/
public class UserController {
    
     /**
     * String (Texto)
     * Interger (int) números inteiros.
     * Double (double) números 0.0000.
     * Float (float) números 0.000.
     * char (A C)
     * Date (data)
     * void - Logica executada detrno do método
     */
    /*
     * Body - dentro da estrutura do corpo da requisição
     */

    @Autowired //gerenciar o ciclo de vida
    private IUserRepository userRepository; // Chamando a interface
    @PostMapping("/")
    public ResponseEntity create (@RequestBody UserModel userModel){
        var user = this.userRepository.findByUsername(userModel.getUsername());
        
        if (user != null) {
            // mensagem de erro
            //status code http
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já exixtente");
        }
        var passwordHashed = BCrypt.withDefaults()
        .hashToString(12, userModel.getPassword().toCharArray()); // fazendo hash da senha, Criptografando!

        userModel.setPassword(passwordHashed);

        var userCreated = this.userRepository.save(userModel); // métodos dentro do JpaRepository
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
}
