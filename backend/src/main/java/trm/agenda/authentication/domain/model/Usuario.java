package trm.agenda.authentication.domain.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Type;

@Entity
public class Usuario {

    // Declaracion de campo id (PK)
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue
    private UUID id;

    // Declaracion de campo usurname
    @NotNull
    @Column(nullable = false, length = 20)
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^([a-zA-Z0-9_-]){8,20}$")
    private String username;

    // Declaracion de campo usurname
    @NotNull
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    // Declaracion de campo email
    @NotNull
    @Column(nullable = false, length = 40)
    @Size(min = 10, max = 40)
    private String email;

    // Constructor de la clase vacio para que no salte excepcion
    public Usuario() {

    }

    public Usuario(String username, String password, String email) {
        this.setUsername(username).setPassword(password).setEmail(email);
    }

    // Constructores de campo id
    public UUID getId() {
        return id;
    }

    public Usuario setId(UUID id) {
        this.id = id;
        return this;
    }

    // Constructores de campo usurname
    public String getUsername() {
        return username;
    }

    public Usuario setUsername(String username) {
        this.username = username;
        return this;
    }

    // Constructores de campo password
    public String getPassword() {
        return password;
    }

    public Usuario setPassword(String password) {
        this.password = password;
        return this;
    }

    // Constructores de campo email
    public String getEmail() {
        return email;
    }

    public Usuario setEmail(String email) {
        this.email = email;
        return this;
    }

}
