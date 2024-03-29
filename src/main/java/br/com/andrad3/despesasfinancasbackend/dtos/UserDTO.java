package br.com.andrad3.despesasfinancasbackend.dtos;

import java.time.LocalDate;
import java.util.Objects;

public class UserDTO {

    private Long id;
    private String name;
    private String password;
    private String email;
    private LocalDate creationDate = LocalDate.now();

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(name, userDTO.name) && Objects.equals(password, userDTO.password) && Objects.equals(email, userDTO.email) && Objects.equals(creationDate, userDTO.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, email, creationDate);
    }

}
