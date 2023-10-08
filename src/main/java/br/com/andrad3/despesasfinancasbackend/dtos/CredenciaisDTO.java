package br.com.andrad3.despesasfinancasbackend.dtos;


import jakarta.validation.constraints.NotNull;

public class CredenciaisDTO {
    @NotNull(message = "O campo email é requerido")
    private String email;
    @NotNull(message = "O campo password é requerido")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
