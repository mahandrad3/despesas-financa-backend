package br.com.andrad3.despesasfinancasbackend.repositories;

import br.com.andrad3.despesasfinancasbackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT u FROM User u where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
    @Query(value = "SELECT u FROM User u where u.email = :email")
    UserDetails findByLogin(@Param("email") String email);

}
