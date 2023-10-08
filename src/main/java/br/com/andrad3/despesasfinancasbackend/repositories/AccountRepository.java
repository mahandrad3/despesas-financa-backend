package br.com.andrad3.despesasfinancasbackend.repositories;

import br.com.andrad3.despesasfinancasbackend.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

    @Query(value = "SELECT a FROM Account a where a.user.id = :id ")
    List<Account> findAllById(@Param("id")Long id);
}
