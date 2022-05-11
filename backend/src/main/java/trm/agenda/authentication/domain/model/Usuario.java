package trm.agenda.authentication.domain.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    @Pattern(regexp = "^([a-zA-Z0-9_]){8,20}$")
    private String usurname;

    // Constructor de la clase vacio para que no salte excepcion
    public Usuario() {

    }

    public Usuario(String usurname) {
        this.setUsurname(usurname);

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
    public String getUsurname() {
        return usurname;
    }

    public Usuario setUsurname(String usurname) {
        this.usurname = usurname;
        return this;
    }

}
