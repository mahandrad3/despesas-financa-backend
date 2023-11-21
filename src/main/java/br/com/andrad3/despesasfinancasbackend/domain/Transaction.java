package br.com.andrad3.despesasfinancasbackend.domain;

import br.com.andrad3.despesasfinancasbackend.domain.enums.TypeTransaction;
import br.com.andrad3.despesasfinancasbackend.dtos.TransactionDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor //cONSTRUTOR VAZIO SPRING FUNCIONAR
@AllArgsConstructor
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
    @JsonIgnore
    private Account account;

    public Transaction(TransactionDTO transactionDTO){
        this.id = transactionDTO.getIdTransaction();
        this.valor = transactionDTO.getValor();
        this.idCategory = transactionDTO.getIdCategory();
        this.descricao = transactionDTO.getDescricao();
        this.creationDate = transactionDTO.getDataTransacao();
        this.type = transactionDTO.getTipoTransacao();
        this.account = transactionDTO.getAccount();

    }

}
