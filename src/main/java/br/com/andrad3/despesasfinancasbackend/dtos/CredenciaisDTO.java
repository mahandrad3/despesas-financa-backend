package br.com.andrad3.despesasfinancasbackend.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CredenciaisDTO {
    @NotNull(message = "O campo email é requerido")
    private String email;
    @NotNull(message = "O campo password é requerido")
    private String password;
    @NotNull(message = "O campo name é requerido")
    private String name;

}
