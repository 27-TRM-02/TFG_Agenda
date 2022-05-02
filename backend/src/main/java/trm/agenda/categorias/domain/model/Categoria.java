package trm.agenda.categorias.domain.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

@Entity
public class Categoria {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue
    private UUID id;

    @NotNull
    @Size(max = 60)
    private String title;

    @Size(max = 1024)
    @ColumnDefault("''")
    private String description;

    // Constructor de la clase vacio para que no salte excepcion
    public Categoria() {
    }

    public Categoria(String title, String description) {
        this.setTitle(title).setDescription(description);
    }

    public String getTitle() {
        return title;
    }

    public Categoria setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Categoria setDescription(String description) {
        this.description = description;
        return this;
    }

}
