package trm.agenda.tareas.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import trm.agenda.tareas.domain.model.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, UUID> {

    // Función que busca tarea por id de la tarea e id del owner
    public Optional<Tarea> findByIdAndOwnerId(UUID id, UUID ownerId);

    // Función que devuelve lista con tareas destacadas
    public List<Tarea> findAllByHighlightedIsTrueAndOwnerId(UUID ownerId);

    // Funcion que devuelve lista con tareas cerca de la fecha actual
    public List<Tarea> findAllByDateLessThanEqualAndOwnerId(LocalDateTime dateTime, UUID ownerId);

    // Funcion que devuelve lista con todas las tareas
    public List<Tarea> findAllByCategoriesIdAndOwnerId(UUID id, UUID ownerId);

}
