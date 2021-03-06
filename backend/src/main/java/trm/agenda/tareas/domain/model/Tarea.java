package trm.agenda.tareas.domain.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import trm.agenda.authentication.domain.model.Usuario;
import trm.agenda.categorias.domain.model.Categoria;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Entity
@DynamicUpdate
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
    @Column(nullable = false)
    @JsonFormat(shape = Shape.STRING)
    @JsonInclude(Include.NON_NULL)
    private LocalDateTime date;

    // Declaracion de campo description
    @Size(max = 1024)
    @ColumnDefault("''")
    private String description;

    // Declaracion de campo Highlighted
    @NotNull
    @ColumnDefault("false")
    private Boolean highlighted = false;

    // Declaracion de campo categorias
    @ManyToMany()
    private List<Categoria> categories;

    // Declaración de campo propietario (owner) -> Usuario.id
    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(referencedColumnName = "id", updatable = false, name = "owner_id")
    @Type(type = "uuid-char")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Usuario owner;

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

    // Constructores de campo owner
    public Usuario getOwner() {
        return owner;
    }

    public Tarea setOwner(Usuario owner) {
        this.owner = owner;
        return this;
    }

}
