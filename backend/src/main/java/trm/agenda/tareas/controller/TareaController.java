package trm.agenda.tareas.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trm.agenda.authentication.domain.model.Usuario;
import trm.agenda.authentication.domain.repository.UsuarioRepository;
import trm.agenda.authentication.utility.AuthenticationUtility;
import trm.agenda.response.exception.EntityNotFoundException;
import trm.agenda.tareas.domain.model.Tarea;
import trm.agenda.tareas.domain.repository.TareaRepository;

@RestController
@RequestMapping("/tarea")
public class TareaController {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Lista todas las tareas
    @GetMapping("")
    private ResponseEntity<List<Tarea>> list() {
        return ResponseEntity.ok(this.tareaRepository.findAll());
    }

    // Crea nueva tarea
    @PostMapping("/new")
    public ResponseEntity<Tarea> newTask(@Valid @RequestBody Tarea task) {
        // Busca por el id al Usuario activo creando la tarea
        Usuario currentUser = usuarioRepository.findById(AuthenticationUtility.getCurrentUser().getId()).get();
        // Setea el owner
        task.setOwner(currentUser);
        this.tareaRepository.save(task);
        return ResponseEntity.ok(task);
    }

    // Edita tarea
    @PatchMapping("/edit/{id}")
    public ResponseEntity<Tarea> updateTask(@PathVariable UUID id, @Valid @RequestBody Tarea task) {
        Optional<Tarea> tarea = this.tareaRepository.findById(id);
        tarea.orElseThrow(() -> new EntityNotFoundException(id, Tarea.class));
        this.tareaRepository.save(task.setId(tarea.get().getId()));
        return ResponseEntity.ok(task);
    }

    // Busca por id
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> findById(@PathVariable UUID id) {
        // Almacenamamos en "tarea" la tarea buscada por su id
        Optional<Tarea> tarea = this.tareaRepository.findById(id);
        // Lanza excepcion -> return respuesta
        tarea.orElseThrow(() -> new EntityNotFoundException(id, Tarea.class));
        return ResponseEntity.ok(tarea.get());
    }

    // Busca tareas destacadas
    @GetMapping("/highlighted")
    public ResponseEntity<List<Tarea>> findHighlighted() {
        UUID ownerId = AuthenticationUtility.getCurrentUser().getId();
        return ResponseEntity.ok(this.tareaRepository.findAllByHighlightedIsTrueAndOwnerId(ownerId));
    }

    // Busca tareas que esten cerca de la fecha actual
    @GetMapping("/upcoming")
    public ResponseEntity<List<Tarea>> findUpcomingDateDefault() {
        LocalDateTime timeWithAddedDays = LocalDateTime.now().plusDays(3);
        UUID ownerId = AuthenticationUtility.getCurrentUser().getId();
        return ResponseEntity.ok(this.tareaRepository.findAllByDateLessThanEqualAndOwnerId(timeWithAddedDays, ownerId));
    }

    // Busca tareas que esten cerca de la fecha actual
    @GetMapping("/upcoming/{days}")
    public ResponseEntity<List<Tarea>> findUpcomingDate(@PathVariable Long days) {
        LocalDateTime timeWithAddedDays = LocalDateTime.now().plusDays(days);
        UUID ownerId = AuthenticationUtility.getCurrentUser().getId();
        return ResponseEntity.ok(this.tareaRepository.findAllByDateLessThanEqualAndOwnerId(timeWithAddedDays, ownerId));
    }

    // Borra tarea por su id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Tarea> deleteById(@PathVariable UUID id) {
        // Almacenamamos en "tarea" la tarea buscada por su id
        Optional<Tarea> tarea = this.tareaRepository.findById(id);
        // Lanza excepcion -> return respuesta
        tarea.orElseThrow(() -> new EntityNotFoundException(id, Tarea.class));
        // Borramos tarea de la bbdd -> se busca por el objeto
        this.tareaRepository.delete(tarea.get());
        // Devuelve json de la tarea eliminada
        return ResponseEntity.ok(tarea.get());
    }

    // Busca tareas por categoria (id)
    @GetMapping("/search/category/{id}")
    public ResponseEntity<List<Tarea>> findByCategory(@PathVariable UUID id) {
        UUID ownerId = AuthenticationUtility.getCurrentUser().getId();
        return ResponseEntity.ok(this.tareaRepository.findAllByCategoriesIdAndOwnerId(id, ownerId));
    }

}
