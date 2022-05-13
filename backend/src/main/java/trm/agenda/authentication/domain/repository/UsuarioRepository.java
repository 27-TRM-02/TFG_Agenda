package trm.agenda.authentication.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import trm.agenda.authentication.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    public Optional<Usuario> findByUsername(String username);

    public Boolean existsByUsername(String username);
}
