package trm.agenda.categorias.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import trm.agenda.categorias.domain.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {

    // Función que busca tarea por id de la tarea e id del owner
    public Optional<Categoria> findByIdAndOwnerId(UUID id, UUID ownerId);

}
