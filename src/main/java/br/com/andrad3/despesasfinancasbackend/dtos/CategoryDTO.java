package br.com.andrad3.despesasfinancasbackend.dtos;

import br.com.andrad3.despesasfinancasbackend.domain.User;
import br.com.andrad3.despesasfinancasbackend.domain.enums.TypeTransaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class CategoryDTO {


    private Long id;
    private String nome;
    private Integer idCon;
    private TypeTransaction tipo;
    private Long userId;


}
