package br.com.andrad3.despesasfinancasbackend.domain;

import br.com.andrad3.despesasfinancasbackend.domain.enums.TypeTransaction;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

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
    @ManyToOne
    private User user;

    public Category() {
    }

    public Category(Long id, String nome, Integer idCon, TypeTransaction tipo, User user) {
        this.id = id;
        this.nome = nome;
        this.idCon = idCon;
        this.tipo = tipo;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdCon() {
        return idCon;
    }

    public void setIdCon(Integer idCon) {
        this.idCon = idCon;
    }

    public TypeTransaction getTipo() {
        return tipo;
    }

    public void setTipo(TypeTransaction tipo) {
        this.tipo = tipo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(nome, category.nome) && Objects.equals(idCon, category.idCon) && tipo == category.tipo && Objects.equals(user, category.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, idCon, tipo, user);
    }
}
