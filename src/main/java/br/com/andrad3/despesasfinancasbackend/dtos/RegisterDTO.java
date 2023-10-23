package br.com.andrad3.despesasfinancasbackend.dtos;

import br.com.andrad3.despesasfinancasbackend.domain.enums.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class RegisterDTO {


        @NotNull(message = "O campo email é requerido")
        private String email;
        @NotNull(message = "O campo password é requerido")
        private String password;
        @NotNull(message = "O campo name é requerido")
        private String name;
        private UserRole role;

}
