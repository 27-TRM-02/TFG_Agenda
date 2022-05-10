package trm.agenda.categorias.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import trm.agenda.categorias.domain.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {

}
