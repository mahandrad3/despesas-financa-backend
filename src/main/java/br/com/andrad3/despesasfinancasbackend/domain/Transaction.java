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
    private Long idAccount;
    private Boolean recorencia;
    private Integer parcelas;
    private Long idTransacaoPai;
    private Boolean isPaga;

    public Transaction(TransactionDTO transactionDTO){
        this.id = transactionDTO.getIdTransaction();
        this.valor = transactionDTO.getValor();
        this.idCategory = transactionDTO.getIdCategory();
        this.descricao = transactionDTO.getDescricao();
        this.creationDate = transactionDTO.getDataTransacao();
        this.type = transactionDTO.getTipoTransacao();
        this.account = transactionDTO.getAccount();
        this.idAccount = transactionDTO.getIdAccount();
        this.isPaga = transactionDTO.getIsPaga();
    }


    public Transaction(Long idTransaction,BigDecimal valor,Long idCategory,String descricao,
                       LocalDate localDate,TypeTransaction type,Account account,
                       Long idAccount, Boolean recorencia, Integer parcelas, Boolean isPaga){
        this.id = idTransaction;
        this.valor = valor;
        this.idCategory = idCategory;
        this.descricao = descricao;
        this.creationDate = localDate;
        this.type = type;
        this.account = account;
        this.idAccount = idAccount;
        this.recorencia = recorencia;
        this.parcelas = parcelas;
        this.isPaga = isPaga;
    }
    public Transaction(Long idTransaction,BigDecimal valor,Long idCategory,String descricao,
                       LocalDate localDate,TypeTransaction type,Account account,
                       Long idAccount,Boolean recorencia, Long idTransacaoPai,Boolean isPaga){
        this.id = idTransaction;
        this.valor = valor;
        this.idCategory = idCategory;
        this.descricao = descricao;
        this.creationDate = localDate;
        this.type = type;
        this.account = account;
        this.idAccount = idAccount;
        this.recorencia = recorencia;
        this.idTransacaoPai = idTransacaoPai;
        this.isPaga = isPaga;
    }
    public Transaction(Long idTransaction,BigDecimal valor,Long idCategory,String descricao,
                       LocalDate localDate,TypeTransaction type,Account account,
                       Long idAccount,Boolean isPaga){
        this.id = idTransaction;
        this.valor = valor;
        this.idCategory = idCategory;
        this.descricao = descricao;
        this.creationDate = localDate;
        this.type = type;
        this.account = account;
        this.idAccount = idAccount;
        this.isPaga = isPaga;
    }

}
