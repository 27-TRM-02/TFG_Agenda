package trm.agenda.categorias.domain.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

@Entity
public class Categoria {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue
    private UUID id;

    public UUID getId() {
        return id;
    }

    public Categoria setId(UUID id) {
        this.id = id;
        return this;
    }

}
