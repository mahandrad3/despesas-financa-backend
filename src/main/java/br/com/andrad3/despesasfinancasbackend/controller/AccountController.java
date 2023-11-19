package br.com.andrad3.despesasfinancasbackend.controller;

import br.com.andrad3.despesasfinancasbackend.Exceptions.InvalidEnumException;
import br.com.andrad3.despesasfinancasbackend.domain.Account;
import br.com.andrad3.despesasfinancasbackend.domain.User;
import br.com.andrad3.despesasfinancasbackend.dtos.AccountDTO;
import br.com.andrad3.despesasfinancasbackend.services.AccountService;
import br.com.andrad3.despesasfinancasbackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value = "/api/account")
@Tag(name = "Account", description = "endpoints for account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;




    @Operation(summary = "Busca todas as contas vinculadas ao id", method = "GET")
    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Account>> findAll(@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = extractTokenFromAuthorizationHeader(authorizationHeader);
        if(this.userService.userIsPermissonForId(token,id)) {
            List<Account> accountList = this.accountService.findAllById(id);
            return ResponseEntity.ok().body(accountList);
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "cria uma nova conta", method = "POST")
    @PostMapping
    public ResponseEntity<AccountDTO> create(@RequestBody AccountDTO objDTO) {
        accountService.createAccount(objDTO);
        return ResponseEntity.created(null).build();
    }

    @Operation(summary = "Deleta UMA CONTA", method = "DELETE")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> delete(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.created(null).build();
    }

    private String extractTokenFromAuthorizationHeader(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }


}
