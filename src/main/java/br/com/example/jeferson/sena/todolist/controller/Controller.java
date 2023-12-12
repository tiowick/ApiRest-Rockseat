package br.com.example.jeferson.sena.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/primeiraRota")
//http://localhost:8080/
public class Controller {
    
    /**
     * Métodos de acesso do HTTP
     * GET - Buscar uma informação.
     * POST - Adicionar um dado/informação.
     * PUT - Alterar um dado/informação.
     * DELETE - Remover um dado/informação.
     * PATCH - Alterar somente uma parte do dado/ informação.
     */

    // Método (Funcionalidade) de uma classe 
    @GetMapping("/primeiroMetodo")
    public String primeiraMensagem(){
        return "TESTE DE CONTROLLER OK";
    }
}
