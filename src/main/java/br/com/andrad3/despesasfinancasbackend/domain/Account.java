package br.com.andrad3.despesasfinancasbackend.domain;

import br.com.andrad3.despesasfinancasbackend.dtos.AccountDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor //cONSTRUTOR VAZIO SPRING FUNCIONAR
@AllArgsConstructor
@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal saldo;
    @JsonIgnore
    @ManyToOne
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> transactions;

    public Account(AccountDTO objDTO) {
        this.id = objDTO.getId();
        this.name = objDTO.getName();
        this.saldo = objDTO.getSaldo();
        this.user = objDTO.getUser();
        this.transactions = objDTO.getTransactions();
    }




}
