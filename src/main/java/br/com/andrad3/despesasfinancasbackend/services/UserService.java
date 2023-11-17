package br.com.andrad3.despesasfinancasbackend.services;

import br.com.andrad3.despesasfinancasbackend.Exceptions.InvalidEnumException;
import br.com.andrad3.despesasfinancasbackend.domain.User;
import br.com.andrad3.despesasfinancasbackend.dtos.*;
import br.com.andrad3.despesasfinancasbackend.repositories.UserRepository;
import br.com.andrad3.despesasfinancasbackend.security.TokenService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    DBService dbService;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    TokenService tokenService;

    public User addUser(UserDTO objDTO){
        objDTO.setId(null);
        validaPorEmail(objDTO);
        User newUser = new User(objDTO);
        User salvoNoBanco = repository.save(newUser);
        dbService.setDefaultCategories(salvoNoBanco.getId());
        dbService.setDefaultAccount(salvoNoBanco.getId());
        return salvoNoBanco;
    }

    private void validaPorEmail(UserDTO objDTO) {
        Optional<User> user = repository.findByEmail(objDTO.getEmail());
        if(user.isPresent()) {
            throw new DataIntegrityViolationException("email Já cadastrado");
        }
    }

    public User findById(Long id){
        Optional<User> objRecuperado = repository.findById(id);
        return objRecuperado.orElseThrow();
    }

    public Optional<User> findByLogin(String email){
        Optional<User> user = repository.findByEmail(email);
        return user;
    }

    public void addUser(User newUser) {
        User salvoNoBanco = repository.save(newUser);
        dbService.setDefaultCategories(salvoNoBanco.getId());
        dbService.setDefaultAccount(salvoNoBanco.getId());
    }

    public void isValidRegister(RegisterDTO obj){
        if(findByLogin(obj.getEmail()).isPresent()) throw new InvalidEnumException("Email já cadastrado no sistema");
        if(obj.getPassword().trim().length() <= 4 ) throw new InvalidEnumException("Senha menor ou igual a 4 caracteres");
    }

    public void isValidLogin(loginDTO obj){
        if(findByLogin(obj.getEmail()).isEmpty()) throw new InvalidEnumException("Email nao cadastrado no sistema");
        if(obj.getPassword().trim().length() <= 4 ) throw new InvalidEnumException("Senha menor ou igual a 4 caracteres");
    }

    public void sendEmailForPassword(String email) throws MessagingException {
        Optional<User> byLogin = findByLogin(email);
        if(byLogin.isEmpty()) throw new InvalidEnumException("Email nao cadastrado no sistema");
        emailSenderService.sendEmail(email,byLogin.get());
    }

    public void changePassword(TokenValidDTO objDTO) {
        String emailRecuperado = this.tokenService.validateToken(objDTO.getToken());
        Optional<User> userRecuperado = this.findByLogin(emailRecuperado);
        if(userRecuperado.isPresent()){
            String encryptedPassword = new BCryptPasswordEncoder().encode(objDTO.getSenha());
            userRecuperado.get().setPassword(encryptedPassword);
            this.repository.save(userRecuperado.get());
        }
    }
}
