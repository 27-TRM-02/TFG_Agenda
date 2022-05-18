package trm.agenda.authentication.domain.model.dto;

import java.util.UUID;

public class JwtResponse {

    private String jwt;

    private UUID id;

    private String username;

    public JwtResponse(String jwt, UUID id, String username) {
        this.jwt = jwt;
        this.id = id;
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
