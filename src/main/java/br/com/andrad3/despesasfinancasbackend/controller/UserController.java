package br.com.andrad3.despesasfinancasbackend.controller;

import br.com.andrad3.despesasfinancasbackend.domain.User;
import br.com.andrad3.despesasfinancasbackend.dtos.UserDTO;
import br.com.andrad3.despesasfinancasbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User objRecuperado = service.findById(id);
        return ResponseEntity.ok().body(objRecuperado);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO objBody){
        User newObj = service.addUser(objBody);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return  ResponseEntity.created(uri).build();
    }


}
