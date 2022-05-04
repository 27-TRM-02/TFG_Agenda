package trm.agenda.tareas.domain.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
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

    // Declaracion de campo description
    @Size(max = 1024)
    @ColumnDefault("''")
    private String description;

    // Declaracion de campo date
    @NotNull
    @DateTimeFormat(pattern = "MM/DD/YYYY")
    private Date date;
    // Declaracion de campo Highlighted
    @NotNull
    @ColumnDefault("false")
    private Boolean highlighted;

    // Constructor de la clase vacio para que no salte excepcion
    public Tarea() {
    }

    // Constructor de los campos de la clase
    public Tarea(String title, String description, Date date, Boolean highlighted) {
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
    public Date getDate() {
        return date;
    }

    public Tarea setDate(Date date) {
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

}
