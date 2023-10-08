package br.com.andrad3.despesasfinancasbackend.domain;

import br.com.andrad3.despesasfinancasbackend.domain.enums.TypeTransaction;
import br.com.andrad3.despesasfinancasbackend.dtos.TransactionDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private Long idCategory;
    private String descricao;
    @Column(name = "dataCriacao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate creationDate;
    @Enumerated(EnumType.STRING)
    private TypeTransaction type;
    @ManyToOne
    private Account account;

    public Transaction() {
    }

    public Transaction(Long id, BigDecimal valor, Long idCategory, String descricao, LocalDate creationDate, TypeTransaction type, Account account) {
        this.id = id;
        this.valor = valor;
        this.idCategory = idCategory;
        this.descricao = descricao;
        this.creationDate = creationDate;
        this.type = type;
        this.account = account;
    }

    public Transaction(TransactionDTO transactionDTO) {
        this.id = transactionDTO.getId();
        this.valor = transactionDTO.getValor();
        this.idCategory = transactionDTO.getIdCategory();
        this.creationDate = transactionDTO.getLocalDate();
        this.type = transactionDTO.getType();
        this.account = transactionDTO.getAccount();
        this.descricao = transactionDTO.getDescricao();
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(valor, that.valor) && Objects.equals(idCategory, that.idCategory) && Objects.equals(creationDate, that.creationDate) && type == that.type && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor, idCategory, creationDate, type, account);
    }
}
