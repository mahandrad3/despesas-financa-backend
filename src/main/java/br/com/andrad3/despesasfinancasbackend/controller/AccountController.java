package br.com.andrad3.despesasfinancasbackend.controller;

import br.com.andrad3.despesasfinancasbackend.domain.Account;
import br.com.andrad3.despesasfinancasbackend.dtos.AccountDTO;
import br.com.andrad3.despesasfinancasbackend.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.List;


@Controller
@RequestMapping(value ="/api/account")
@Tag(name = "Account", description = "endpoints for account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Operation(summary = "Busca todas as contas vinculadas ao id", method = "GET")
    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Account>> findAll(@PathVariable Long id){
        List<Account> accountList = accountService.findAllById(id);
        return ResponseEntity.ok().body(accountList);
    }
    @Operation(summary = "cria uma nova conta", method = "POST")
    @PostMapping
    public ResponseEntity<AccountDTO> create(@RequestBody AccountDTO objDTO){
        accountService.createAccount(objDTO);
        return ResponseEntity.created(null).build();
    }
    @Operation(summary = "DELETA UMA CONTA", method="DELETE")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> delete(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.created(null).build();
    }



}
