package br.com.andrad3.despesasfinancasbackend.services;

import br.com.andrad3.despesasfinancasbackend.domain.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransaction();
    Transaction updateTransaction(Long id);
    Transaction createTransaction(Transaction transaction);
    Transaction getTransactionById(Long id);
    void deleteTransaction();
}
