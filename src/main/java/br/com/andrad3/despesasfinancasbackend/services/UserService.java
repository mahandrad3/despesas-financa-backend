package br.com.andrad3.despesasfinancasbackend.services;

import br.com.andrad3.despesasfinancasbackend.domain.User;

public interface UserService {

    User createUser(User user);
    User updateUser(User user);
    void deleteUser(User user);
    User getUserById(Long id);
}
