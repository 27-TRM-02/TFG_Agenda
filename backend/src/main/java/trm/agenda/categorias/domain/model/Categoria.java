package trm.agenda.categorias.domain.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @Column(nullable = false)
    @Size(min = 4, max = 7)
    @Pattern(regexp = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$")
    private String color;

    // Constructor de la clase vacio para que no salte excepcion
    public Categoria() {
    }

    // Constructor de los campos de la clase
    public Categoria(String title, String description, String color) {
        this.setTitle(title).setDescription(description).setColor(color);
    }

    public UUID getId() {
        return id;
    }

    public Categoria setId(UUID id) {
        this.id = id;
        return this;
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

    public String getColor() {
        return color;
    }

    public Categoria setColor(String color) {
        this.color = color;
        return this;
    }
}
