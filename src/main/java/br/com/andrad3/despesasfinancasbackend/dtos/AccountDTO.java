package br.com.andrad3.despesasfinancasbackend.dtos;

import br.com.andrad3.despesasfinancasbackend.domain.Transaction;
import br.com.andrad3.despesasfinancasbackend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long id;
    private String name;
    private User user;
    private List<Transaction> transactions;

    public AccountDTO(Long id, String name, BigDecimal saldo, User user, List<Transaction> transactions) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.transactions = transactions;
    }


}
