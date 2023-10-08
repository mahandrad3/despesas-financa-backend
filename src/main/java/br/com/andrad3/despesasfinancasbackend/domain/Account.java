package br.com.andrad3.despesasfinancasbackend.domain;

import br.com.andrad3.despesasfinancasbackend.dtos.AccountDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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

    public Account() {
    }

    public Account(Long id, String name, BigDecimal saldo, User user, List<Transaction> transactions) {
        this.id = id;
        this.name = name;
        this.saldo = saldo;
        this.user = user;
        this.transactions = transactions;
    }

    public Account(AccountDTO objDTO) {
        this.id = objDTO.getId();
        this.name = objDTO.getName();
        this.saldo = objDTO.getSaldo();
        this.user = objDTO.getUser();
        this.transactions = objDTO.getTransactions();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(saldo, account.saldo) && Objects.equals(user, account.user) && Objects.equals(transactions, account.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, saldo, user, transactions);
    }
}
