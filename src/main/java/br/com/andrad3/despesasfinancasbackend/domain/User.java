package br.com.andrad3.despesasfinancasbackend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Usuarios")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", length = 45)
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "email", length = 255, unique = true)
    private String email;
    @Column(name = "dataCriacao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate creationDate;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Category> categories;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Account> contas;

    public User() {
    }

    public User(Long id, String name, String password, String email, LocalDate creationDate, List<Category> categories, List<Account> contas) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.creationDate = creationDate;
        this.categories = categories;
        this.contas = contas;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Account> getContas() {
        return contas;
    }

    public void setContas(List<Account> contas) {
        this.contas = contas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(creationDate, user.creationDate) && Objects.equals(categories, user.categories) && Objects.equals(contas, user.contas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, email, creationDate, categories, contas);
    }
}
