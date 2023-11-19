package br.com.andrad3.despesasfinancasbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTOforFront {

    private Long id;
    private String token;

}
