package br.com.andrad3.despesasfinancasbackend.dtos;

import br.com.andrad3.despesasfinancasbackend.domain.Account;
import br.com.andrad3.despesasfinancasbackend.domain.enums.TypeTransaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDTO {

    private Long id;
    private BigDecimal valor;
    private Long idCategory;
    private TypeTransaction type;
    private Account account;
    private LocalDate localDate;
    private String descricao;

    public TransactionDTO() {
    }

    public TransactionDTO(Long id, BigDecimal valor, Long idCategory, TypeTransaction type, Account account, LocalDate localDate, String descricao) {
        this.id = id;
        this.valor = valor;
        this.idCategory = idCategory;
        this.type = type;
        this.account = account;
        this.localDate = localDate;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public TypeTransaction getType() {
        return type;
    }

    public void setType(TypeTransaction type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
