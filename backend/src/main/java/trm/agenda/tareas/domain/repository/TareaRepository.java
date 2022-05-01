package trm.agenda.tareas.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import trm.agenda.tareas.domain.model.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, UUID> {

}
