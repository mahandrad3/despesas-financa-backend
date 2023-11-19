package br.com.andrad3.despesasfinancasbackend.services;

import br.com.andrad3.despesasfinancasbackend.domain.Account;
import br.com.andrad3.despesasfinancasbackend.domain.Category;
import br.com.andrad3.despesasfinancasbackend.domain.Transaction;
import br.com.andrad3.despesasfinancasbackend.dtos.TransactionDTO;
import br.com.andrad3.despesasfinancasbackend.repositories.AccountRepository;
import br.com.andrad3.despesasfinancasbackend.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.AcceptPendingException;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

//    public void addTransaction(TransactionDTO objDTO){
//        Optional<Account> account = accountRepository.findById(objDTO.getIdAccount());
//        Optional<Category>
//
//
//
//
//    }


}
