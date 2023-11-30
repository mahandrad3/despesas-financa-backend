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

import java.util.ArrayList;
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
    @GetMapping(value = "/findAllAccounts/{id}")
    public ResponseEntity<List<AccountDTO>> findAll(@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = extractTokenFromAuthorizationHeader(authorizationHeader);
        if(this.userService.userIsPermissonForId(token,id)) {
            List<Account> accountList = this.accountService.findAllById(id);
            List<AccountDTO> accountBody = new ArrayList<>();
            for(Account account: accountList){
                account.setTransactions(null);
                account.setUser(null);
                accountBody.add(new AccountDTO(account.getId(),account.getName(),account.getUser()));
            }
            return ResponseEntity.ok().body(accountBody);
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "cria uma nova conta", method = "POST")
    @PostMapping
    public ResponseEntity<Account> create(@RequestBody AccountDTO objDTO) {
        if(objDTO.getName() == null) throw new InvalidEnumException("Campo nome nao pode ser nulo ou vazio");
        Account acccount = accountService.createAccount(objDTO);
        return ResponseEntity.ok().body(acccount);
    }

    @Operation(summary = "Traz a conta pelo id , e as suas transacoes", method = "POST")
    @PostMapping(value = "/{id}")
    public ResponseEntity<Account> findAccountById(@PathVariable Long id){
        Account account = this.accountService.findById(id);
        return ResponseEntity.ok().body(account);
    }

    @Operation(summary = "Deleta uma conta", method = "DELETE")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> delete(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.created(null).build();
    }

    @Operation(summary = "EDita uma account", method = "PUT")
    @PutMapping(value = "/editar")
    public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO accountDTO){
        accountService.updateAccount(accountDTO);
        return ResponseEntity.ok().build();
    }




    private String extractTokenFromAuthorizationHeader(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }


}
