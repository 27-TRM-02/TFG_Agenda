package trm.agenda.tareas.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import trm.agenda.tareas.domain.model.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, UUID> {

    // Función que devuelve lista con tareas destacadas
    public List<Tarea> findAllByHighlightedIsTrue();

    // Funcion que devuelve lista con tareas cerca de la fecha actual
    public List<Tarea> findAllByDateLessThanEqual(LocalDateTime dateTime);

    // Funcion que devuelve lista con todas las tareas
    public List<Tarea> findAllByCategoriesId(UUID id);

}
