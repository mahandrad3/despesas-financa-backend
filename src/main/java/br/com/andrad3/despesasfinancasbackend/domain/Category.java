package br.com.andrad3.despesasfinancasbackend.domain;

import br.com.andrad3.despesasfinancasbackend.domain.enums.TypeTransaction;
import br.com.andrad3.despesasfinancasbackend.dtos.CategoryDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor //cONSTRUTOR VAZIO SPRING FUNCIONAR
@AllArgsConstructor
@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", length = 45)
    private String nome;
    @Column(name = "idIcon", length = 100)
    private Integer idCon;
    @Enumerated(EnumType.STRING)
    private TypeTransaction tipo;
    @JsonIgnore
    @ManyToOne
    private User user;

    public Category(CategoryDTO objDTO , User user){
        this.nome = objDTO.getNome();
        this.idCon = objDTO.getIdCon();
        this.tipo = objDTO.getTipo();
        this.user = user;
    }

}
