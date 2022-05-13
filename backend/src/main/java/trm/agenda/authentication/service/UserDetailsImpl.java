package trm.agenda.authentication.service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import trm.agenda.authentication.domain.model.Usuario;

public class UserDetailsImpl implements UserDetails {

    private UUID id;

    private String username;

    @JsonIgnore
    private String password;

    public UserDetailsImpl(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserDetailsImpl(Usuario user) {
        this(user.getId(), user.getUsername(), user.getPassword());
    }

    public static UserDetailsImpl build(Usuario user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getPassword());
    }

    public UUID getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
