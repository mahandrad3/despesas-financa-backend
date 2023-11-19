package br.com.andrad3.despesasfinancasbackend.domain;

import br.com.andrad3.despesasfinancasbackend.domain.enums.UserRole;
import br.com.andrad3.despesasfinancasbackend.dtos.UserDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor //cONSTRUTOR VAZIO SPRING FUNCIONAR
@AllArgsConstructor
@Entity
@Table(name = "Usuarios")
public class User  implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "nome", length = 45)
    private String name;
    @NotNull
    @JsonIgnore
    @Column(name = "password")
    private String password;
    @NotNull
    @Column(name = "email", length = 255, unique = true)
    private String email;
    @Column(name = "dataCriacao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate creationDate = LocalDate.now();
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Category> categories;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> contas;
    private UserRole role;

    public User(UserDTO objdto) {
        this.id = objdto.getId();
        this.name = objdto.getName();
        this.password = objdto.getPassword();
        this.email = objdto.getEmail();
        this.creationDate = objdto.getCreationDate();
    }

    public User(String email, String encryptedPassword, String name, UserRole role) {
        this.email = email;
        this.password = encryptedPassword;
        this.name = name;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
