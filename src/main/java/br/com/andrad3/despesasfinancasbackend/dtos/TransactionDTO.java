package br.com.andrad3.despesasfinancasbackend.dtos;

import br.com.andrad3.despesasfinancasbackend.domain.Account;
import br.com.andrad3.despesasfinancasbackend.domain.Transaction;
import br.com.andrad3.despesasfinancasbackend.domain.enums.TypeTransaction;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long idTransaction;
    private Long idUser;
    private BigDecimal valor;
    private Long idCategory;
    private String descricao;
    private Long idAccount;
    private Boolean recorencia;
    private Integer parcelas;
    private Boolean isPaga;


    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataTransacao;
    @NotNull
    private TypeTransaction tipoTransacao;
    @NotNull
    private Account account;



}
