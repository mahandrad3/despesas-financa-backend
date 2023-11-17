package br.com.andrad3.despesasfinancasbackend.dtos;

import lombok.Data;

@Data
public class TokenValidDTO {

    private String token;

    private String senha;
}
