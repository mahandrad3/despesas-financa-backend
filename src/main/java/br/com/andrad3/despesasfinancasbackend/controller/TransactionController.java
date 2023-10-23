package br.com.andrad3.despesasfinancasbackend.controller;

import br.com.andrad3.despesasfinancasbackend.domain.Transaction;
import br.com.andrad3.despesasfinancasbackend.dtos.TransactionDTO;
import br.com.andrad3.despesasfinancasbackend.services.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/transaction")
@Tag(name = "Account", description = "endpoints for account")
public class TransactionController {

    @Autowired
     private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO objBody){
//        Transaction objSalvado =  transactionService.addTransaction(objBody);
        return null;
    }

}
