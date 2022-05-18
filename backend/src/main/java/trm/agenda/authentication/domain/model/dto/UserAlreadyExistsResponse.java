package trm.agenda.authentication.domain.model.dto;

public class UserAlreadyExistsResponse {

    private Integer status;

    private String message;

    public UserAlreadyExistsResponse(Integer status, String message) {
        this.setStatus(status).setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public UserAlreadyExistsResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public UserAlreadyExistsResponse setStatus(Integer status) {
        this.status = status;
        return this;
    }

}
