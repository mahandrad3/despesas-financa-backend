package br.com.andrad3.despesasfinancasbackend.repositories;

import br.com.andrad3.despesasfinancasbackend.domain.Category;
import br.com.andrad3.despesasfinancasbackend.domain.enums.TypeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value ="SELECT c FROM Category c where c.user.id = :id")
    List<Category> findByAllforCategoriesForId(@Param("id") Long id);

    @Query(value = "SELECT c FROM Category c WHERE c.user.id = :id AND c.tipo = :tipo AND c.nome = :nome")
    Category findByTypeEnumForIdAndName(@Param("id") Long id,
                                        @Param("tipo") TypeTransaction tipo,
                                        @Param("nome") String nome);
}
