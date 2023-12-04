package br.com.andrad3.despesasfinancasbackend.repositories;

import br.com.andrad3.despesasfinancasbackend.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query(value = "SELECT t FROM Transaction t where t.id = :id ")
    Optional<Transaction> findById(@Param("id")Long id);


    @Query("SELECT t FROM Transaction t WHERE t.idTransacaoPai = :id")
    List<Transaction> findAllTransactionsByParentId(@Param("id") Long id);

}
