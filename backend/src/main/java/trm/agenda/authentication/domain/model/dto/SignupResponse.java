package trm.agenda.authentication.domain.model.dto;

import java.util.UUID;

public class SignupResponse {

    private UUID id;

    private String username;

    public SignupResponse(UUID id, String username) {
        this.id = id;
        this.username = username;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
