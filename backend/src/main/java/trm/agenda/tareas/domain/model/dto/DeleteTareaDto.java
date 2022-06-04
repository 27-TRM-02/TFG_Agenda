package trm.agenda.tareas.domain.model.dto;

import java.util.UUID;

public class DeleteTareaDto {

    private UUID id;

    private String title;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DeleteTareaDto(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

}
