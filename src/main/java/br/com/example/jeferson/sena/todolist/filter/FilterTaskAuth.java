package br.com.example.jeferson.sena.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.example.jeferson.sena.todolist.repositories.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletpath = request.getServletPath();
        System.out.println("PATH " + servletpath);
        //servletpath.startsWith para pegar tanto o path quanto os ids que estiverem no conjunto
        if (servletpath.startsWith("/tasks/")) {  //equals seria usado se as rotas tivessem o mesmo path
            // pegar a autenticação (Usuário e senha).
            var authorization = request.getHeader("Authorization");
            var authEncoded = authorization.substring("Basic".length()).trim(); // procurar o basic e o tamanho e remover.
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);

            var authString = new String(authDecode);

            System.out.println("Authorization");
            System.out.println(authString);

            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];
            System.out.println("Authorization");
            System.out.println(username);
            System.out.println(password);
            // validar usuário.

            var user = this.userRepository.findByUsername(username);
            if (user == null) {
                response.sendError(401);
            } else {

                // validar senha.
                // comparando a senha inserida com senha no banco de dados.
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
                // continue
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

}
