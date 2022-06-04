package trm.agenda.categorias.domain.model.dto;

import java.util.UUID;

public class DeleteCategoriaDto {

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

    public DeleteCategoriaDto(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

}
