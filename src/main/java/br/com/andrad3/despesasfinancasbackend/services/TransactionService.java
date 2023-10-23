package br.com.andrad3.despesasfinancasbackend.services;

import br.com.andrad3.despesasfinancasbackend.domain.Transaction;
import br.com.andrad3.despesasfinancasbackend.dtos.TransactionDTO;
import br.com.andrad3.despesasfinancasbackend.repositories.AccountRepository;
import br.com.andrad3.despesasfinancasbackend.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.AcceptPendingException;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

//    public void addTransaction(TransactionDTO objDTO){
//        objDTO.setId(null);
//
//        Transaction transaction = new Transaction(objDTO);
//
//    }


}
