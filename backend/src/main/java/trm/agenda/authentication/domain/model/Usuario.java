package trm.agenda.authentication.domain.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

@Entity
public class Usuario {

    // Declaracion de campo id (PK)
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue
    private UUID id;

    // Constructor de la clase vacio para que no salte excepcion
    public Usuario() {

    }

    // Constructores de campo id
    public UUID getId() {
        return id;
    }

    public Usuario setId(UUID id) {
        this.id = id;
        return this;
    }

}
