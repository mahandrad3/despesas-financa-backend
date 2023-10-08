package br.com.andrad3.despesasfinancasbackend.dtos;

import br.com.andrad3.despesasfinancasbackend.domain.Transaction;
import br.com.andrad3.despesasfinancasbackend.domain.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class AccountDTO {

    private Long id;
    private String name;
    private BigDecimal saldo;
    private User user;
    private List<Transaction> transactions;

    public AccountDTO() {
    }

    public AccountDTO(Long id, String name, BigDecimal saldo, User user, List<Transaction> transactions) {
        this.id = id;
        this.name = name;
        this.saldo = saldo;
        this.user = user;
        this.transactions = transactions;
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
        AccountDTO that = (AccountDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(saldo, that.saldo) && Objects.equals(user, that.user) && Objects.equals(transactions, that.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, saldo, user, transactions);
    }
}
