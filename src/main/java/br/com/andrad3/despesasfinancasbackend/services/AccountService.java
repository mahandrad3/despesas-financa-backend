package br.com.andrad3.despesasfinancasbackend.services;

import br.com.andrad3.despesasfinancasbackend.domain.Account;

public interface AccountService {

    Account createAccount(Account account);
    Account updateUser(Account account);
    void deleteUser(Long id);

}
