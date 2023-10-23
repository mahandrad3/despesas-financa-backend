package br.com.andrad3.despesasfinancasbackend.controller;

import br.com.andrad3.despesasfinancasbackend.domain.User;
import br.com.andrad3.despesasfinancasbackend.dtos.CredenciaisDTO;
import br.com.andrad3.despesasfinancasbackend.dtos.RegisterDTO;
import br.com.andrad3.despesasfinancasbackend.dtos.UserDTO;
import br.com.andrad3.despesasfinancasbackend.dtos.loginDTO;
import br.com.andrad3.despesasfinancasbackend.security.TokenService;
import br.com.andrad3.despesasfinancasbackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@Controller
@RequestMapping("/api/user")
@Tag(name = "User", description = "endpoints for users, auth")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Operation(summary = "Busca conta por id", method = "POST")
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User objRecuperado = service.findById(id);
        return ResponseEntity.ok().body(objRecuperado);
    }

    @Operation(summary = "Realizar o login", method = "POST")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid loginDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(),data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User)auth.getPrincipal());
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "Registra um novo usuario", method = "POST")
    @PostMapping("/registrar")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.service.findByLogin(data.getEmail()).isEmpty()){
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
            User newUser = new User(data.getEmail(),encryptedPassword,data.getName(),data.getRole());
            this.service.addUser(newUser);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
