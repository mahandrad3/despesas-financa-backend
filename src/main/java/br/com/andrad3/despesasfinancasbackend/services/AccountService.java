package br.com.andrad3.despesasfinancasbackend.services;

import br.com.andrad3.despesasfinancasbackend.domain.Account;
import br.com.andrad3.despesasfinancasbackend.dtos.AccountDTO;
import br.com.andrad3.despesasfinancasbackend.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;


    public List<Account> findAllById(Long id){
        List<Account> listAccount = accountRepository.findAllById(id);
        return listAccount;
    }

    public Account findById(Long id) {
        Optional<Account> obj = accountRepository.findById(id);
        return obj.orElseThrow();
    }

    public Account createAccount(AccountDTO objDTO) {
        objDTO.setId(null);
        return accountRepository.save(new Account(objDTO.getId(),objDTO.getName(),objDTO.getUser(),objDTO.getTransactions()));
    }

    public void deleteAccount(Long id){
        Account objRecuperado = findById(id);
        if (!(objRecuperado == null)) {
            accountRepository.deleteById(id);
        }else{
            throw new DataIntegrityViolationException("Nao possue no banco para ser deletado");
        }
    }

    public Account updateAccount(AccountDTO accountDTO){
        return this.createAccount(accountDTO);
    }
}
