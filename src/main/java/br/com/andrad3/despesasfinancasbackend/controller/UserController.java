package br.com.andrad3.despesasfinancasbackend.controller;

import br.com.andrad3.despesasfinancasbackend.Exceptions.InvalidEnumException;
import br.com.andrad3.despesasfinancasbackend.domain.User;
import br.com.andrad3.despesasfinancasbackend.dtos.*;
import br.com.andrad3.despesasfinancasbackend.security.TokenService;
import br.com.andrad3.despesasfinancasbackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


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
    @CrossOrigin(origins = "http://locahost:3000")
    @Operation(summary = "Realizar o login", method = "POST")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody  loginDTO data){
        this.service.isValidLogin(data);
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(),data.getPassword());

        try {
            Authentication authentication = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((User)authentication.getPrincipal());
            User user = this.service.getUserForToken(token).get();
            UserDTOforFront userFront = new UserDTOforFront(user.getId(),token );
            return ResponseEntity.ok(userFront);
        }catch (AuthenticationException e){
            throw new InvalidEnumException("Erro para se autenticar no sistema ",e);
        }
    }

    @CrossOrigin(origins = "http://locahost:3000")
    @Operation(summary = "Registra um novo usuario", method = "POST")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        this.service.isValidRegister(data);
        try {
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
            User newUser = new User(data.getEmail(),encryptedPassword,data.getName(),data.getRole());
            this.service.addUser(newUser);
            Optional<User> userRecup = this.service.findByLogin(newUser.getEmail());
            UserDTOforFront userFront = new UserDTOforFront(userRecup.get().getId(),this.tokenService.generateToken(userRecup.get()));
            return ResponseEntity.ok().body(userFront);
        }catch (RuntimeException e){
            throw new InvalidEnumException("Erro ao tentar cadastrar",e);
        }
    }

    @CrossOrigin(origins = "http://locahost:3000")
    @Operation(summary = "Envia um email para trocar a senha", method = "POST")
    @PostMapping("/sendEmailResetPassword")
    public ResponseEntity changePassword(@RequestBody @Valid CredenciaisForResetEmailDTO credenciaisForResetEmailDTO) throws MessagingException {
        this.service.sendEmailForPassword(credenciaisForResetEmailDTO.getEmail());
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @Operation(summary = "Verifica se o token é valido", method = "POST")
    @PostMapping("/validIsToken")
    public ResponseEntity validToken(@RequestBody @Valid TokenValidDTO objDTO){
        this.tokenService.validateToken(objDTO.getToken());
        return  ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://locahost:3000")
    @Operation(summary = "Confirma o email de usuario, e altera a senha", method = "PUT")
    @PutMapping("/changePassword")
    public ResponseEntity changePassword(@RequestBody @Valid TokenValidDTO objDTO) {
        this.service.changePassword(objDTO);
        return ResponseEntity.ok().build();
    }
}
