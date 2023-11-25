package br.com.andrad3.despesasfinancasbackend.controller;

import br.com.andrad3.despesasfinancasbackend.domain.Transaction;
import br.com.andrad3.despesasfinancasbackend.dtos.TransactionDTO;
import br.com.andrad3.despesasfinancasbackend.services.TransactionService;
import br.com.andrad3.despesasfinancasbackend.services.UserService;
import br.com.andrad3.despesasfinancasbackend.utils.ReutilizarCodigo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/transaction")
@Tag(name = "Transacao", description = "endpoints para transacoes Despesas/Receitas")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    UserService userService;

    @CrossOrigin(origins = "http://locahost:3000")
    @Operation(summary = "Cadastra uma transacao", method = "POST")
    @PostMapping("/addTransacao")
   public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO objBody,@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        String token = ReutilizarCodigo.extractTokenFromAuthorizationHeader(authorizationHeader);
        Transaction transaction = this.transactionService.addTransaction(objBody,token);
        return ResponseEntity.ok().body(transaction);
   }
    @CrossOrigin(origins = "http://locahost:3000")
    @Operation(summary = "Deleta uma transacao", method = "DELETE")
    @DeleteMapping("/remTransacao")
   public ResponseEntity<Transaction> deleteTransaction(@RequestBody TransactionDTO objBody){
        this.transactionService.removeTransaction(objBody);
        return ResponseEntity.ok().build();
   }
    @CrossOrigin(origins = "http://locahost:3000")
    @Operation(summary = "Altera uma transacao", method = "PUT")
    @PutMapping("/alteraTransacao")
   public ResponseEntity<Transaction> alterarTransaction(@RequestBody TransactionDTO objBody){
        this.transactionService.alterarTransacao(objBody);
        return ResponseEntity.ok().build();
   }
    @CrossOrigin(origins = "http://locahost:3000")
    @Operation(summary = "Altera uma transacao", method = "GET")
    @GetMapping("/getTransacoes/{id}")
   public ResponseEntity<List<Transaction>> getAllTransaction(@PathVariable("id")Long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        String token = ReutilizarCodigo.extractTokenFromAuthorizationHeader(authorizationHeader);
        List<Transaction> transactionList =this.transactionService.getAllTransactionForId(id,token);
        return ResponseEntity.ok().body(transactionList);
    }

}
