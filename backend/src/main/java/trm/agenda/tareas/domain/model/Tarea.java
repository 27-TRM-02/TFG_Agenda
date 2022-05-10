package trm.agenda.tareas.domain.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import trm.agenda.categorias.domain.model.Categoria;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;

@Entity
public class Tarea {

    // Declaracion de campo id (PK)
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue
    private UUID id;

    // Declaracion de campo title
    @NotNull
    @Size(max = 60)
    private String title;

    // Declaracion de campo date
    @FutureOrPresent
    @NotNull
    @JsonFormat(shape = Shape.STRING)
    private LocalDateTime date;

    // Declaracion de campo description
    @Size(max = 1024)
    @ColumnDefault("''")
    private String description;

    // Declaracion de campo Highlighted
    @NotNull
    @ColumnDefault("false")
    private Boolean highlighted;

    // Declaracion de campo categorias
    @OneToMany()
    private List<Categoria> categories;

    // Constructor de la clase vacio para que no salte excepcion
    public Tarea() {
    }

    // Constructor de los campos de la clase
    public Tarea(String title, String description, LocalDateTime date, Boolean highlighted) {
        this.setTitle(title).setDescription(description).setDate(date).setHighlighted(highlighted);
    }

    // Constructores de campo id
    public UUID getId() {
        return id;
    }

    public Tarea setId(UUID id) {
        this.id = id;
        return this;
    }

    // Constructores de campo title
    public String getTitle() {
        return title;
    }

    public Tarea setTitle(String title) {
        this.title = title;
        return this;
    }

    // Constructores de campo description
    public String getDescription() {
        return description;
    }

    public Tarea setDescription(String description) {
        this.description = description;
        return this;
    }

    // Constructores de campo date
    public LocalDateTime getDate() {
        return date;
    }

    public Tarea setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    // Constructores de campo Highlighted
    public Boolean getHighlighted() {
        return highlighted;
    }

    public Tarea setHighlighted(Boolean highlighted) {
        this.highlighted = highlighted;
        return this;
    }

    // Constructores de campo categories
    public List<Categoria> getCategories() {
        return categories;
    }

    public Tarea setCategories(List<Categoria> categories) {
        this.categories = categories;
        return this;
    }

}
