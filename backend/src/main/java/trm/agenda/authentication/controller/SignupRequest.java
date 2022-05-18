package trm.agenda.authentication.controller;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class SignupRequest {

    @NotEmpty
    @Length(min = 8, max = 20)
    private String username;

    @NotEmpty
    @Length(min = 8, max = 20)
    private String password;

    @NotEmpty
    @Length(min = 10, max = 40)
    private String email;

    public SignupRequest() {

    }

    public SignupRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
