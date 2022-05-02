package trm.agenda.tareas.domain.model;

import org.hibernate.annotations.Type;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.UUID;

import javax.persistence.Entity;

@Entity
public class Tarea {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue
    private UUID id;

    @NotNull
    @Size(max = 60)
    private String title;

    // Constructor de la clase vacio para que no salte excepcion
    public Tarea() {
    }

    public Tarea(String title) {
        this.setTitle(title);
    }

    public UUID getId() {
        return id;
    }

    public Tarea setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Tarea setTitle(String title) {
        this.title = title;
        return this;
    }

}
