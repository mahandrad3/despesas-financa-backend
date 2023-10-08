package br.com.andrad3.despesasfinancasbackend.services;

import br.com.andrad3.despesasfinancasbackend.domain.User;
import br.com.andrad3.despesasfinancasbackend.dtos.UserDTO;
import br.com.andrad3.despesasfinancasbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    DBService dbService;

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

}
