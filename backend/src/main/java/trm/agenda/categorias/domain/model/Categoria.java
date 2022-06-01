package trm.agenda.categorias.domain.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import trm.agenda.AgendaApplication;
import trm.agenda.authentication.domain.model.Usuario;
import trm.agenda.categorias.domain.repository.CategoriaRepository;
import trm.agenda.response.exception.EntityNotFoundException;

@Entity
public class Categoria {

    // Declaración de campo id (PK)
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue
    private UUID id;

    // Declaración de campo title
    @NotNull
    @Size(max = 60)
    private String title;

    // Declaración de campo description
    @Size(max = 1024)
    @ColumnDefault("''")
    private String description;

    // Declaración de campo color
    @Column(nullable = false)
    @Size(min = 4, max = 7)
    @Pattern(regexp = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$")
    private String color;

    // Declaración de campo propietario (owner) -> Usuario.id
    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(referencedColumnName = "id", updatable = false, name = "owner_id")
    @Type(type = "uuid-char")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Usuario owner;

    // Constructor de la clase vacio para que no salte excepcion
    public Categoria() {
    }

    public Categoria(String id) {
        UUID uuid = UUID.fromString(id);
        Categoria category = AgendaApplication.getApplicationContext().getBean(CategoriaRepository.class)
                .findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(uuid, this.getClass()));
        this.setId(category.getId()).setOwner(category.getOwner()).setTitle(category.getTitle())
                .setDescription(category.getDescription())
                .setColor(category.getColor());
    }

    // Constructor de los campos de la clase
    public Categoria(String title, String description, String color) {
        this.setTitle(title).setDescription(description).setColor(color);
    }

    // Constructores de campo id
    public UUID getId() {
        return id;
    }

    public Categoria setId(UUID id) {
        this.id = id;
        return this;
    }

    // Constructores de campo title
    public String getTitle() {
        return title;
    }

    public Categoria setTitle(String title) {
        this.title = title;
        return this;
    }

    // Constructores de campo description
    public String getDescription() {
        return description;
    }

    public Categoria setDescription(String description) {
        this.description = description;
        return this;
    }

    // Constructores de campo color
    public String getColor() {
        return color;
    }

    public Categoria setColor(String color) {
        this.color = color;
        return this;
    }

    // Constructores de campo owner
    public Usuario getOwner() {
        return owner;
    }

    public Categoria setOwner(Usuario owner) {
        this.owner = owner;
        return this;
    }
}
