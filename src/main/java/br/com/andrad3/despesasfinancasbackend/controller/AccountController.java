package br.com.andrad3.despesasfinancasbackend.controller;

import br.com.andrad3.despesasfinancasbackend.domain.Account;
import br.com.andrad3.despesasfinancasbackend.dtos.AccountDTO;
import br.com.andrad3.despesasfinancasbackend.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.List;

@Controller
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Account>> findAll(@PathVariable Long id){
        List<Account> accountList = accountService.findAllById(id);
        return ResponseEntity.ok().body(accountList);
    }

    @PostMapping
    public ResponseEntity<AccountDTO> create(@RequestBody AccountDTO objDTO){
        accountService.createAccount(objDTO);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> delete(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.created(null).build();
    }



}
