package trm.agenda.tareas.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;

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
import trm.agenda.tareas.domain.model.dto.DeleteTareaDto;
import trm.agenda.tareas.domain.repository.TareaRepository;

@RestController
@RequestMapping("/tarea")
public class TareaController {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjectMapper objectMapper;

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
        // UUID del usuario activo
        UUID ownerId = AuthenticationUtility.getCurrentUser().getId();
        // Almacenamamos en "tarea" la tarea buscada por su id y su owner
        Optional<Tarea> tarea = this.tareaRepository.findByIdAndOwnerId(id, ownerId);
        // Lanza excepcion -> return respuesta
        tarea.orElseThrow(() -> new EntityNotFoundException(id, Tarea.class));

        this.tareaRepository.save(this.actualizarTarea(tarea.get(), task));
        return ResponseEntity.ok(tarea.get());
    }

    // Busca por id
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> findById(@PathVariable UUID id) {
        // UUID del usuario activo
        UUID ownerId = AuthenticationUtility.getCurrentUser().getId();
        // Almacenamamos en "tarea" la tarea buscada por su id
        Optional<Tarea> tarea = this.tareaRepository.findByIdAndOwnerId(id, ownerId);
        // Lanza excepcion -> return respuesta
        tarea.orElseThrow(() -> new EntityNotFoundException(id, Tarea.class));
        return ResponseEntity.ok(tarea.get());
    }

    // Busca tareas destacadas
    @GetMapping("/highlighted")
    public ResponseEntity<List<Tarea>> findHighlighted() {
        // UUID del usuario activo
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
        // UUID del usuario activo
        UUID ownerId = AuthenticationUtility.getCurrentUser().getId();
        return ResponseEntity.ok(this.tareaRepository.findAllByDateLessThanEqualAndOwnerId(timeWithAddedDays, ownerId));
    }

    // Borra tarea por su id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteTareaDto> deleteById(@PathVariable UUID id) {
        // UUID del usuario activo
        UUID ownerId = AuthenticationUtility.getCurrentUser().getId();
        // Almacenamamos en "tarea" la tarea buscada por su id
        Optional<Tarea> tarea = this.tareaRepository.findByIdAndOwnerId(id, ownerId);
        // Lanza excepcion -> return respuesta
        tarea.orElseThrow(() -> new EntityNotFoundException(id, Tarea.class));
        // Borramos tarea de la bbdd -> se busca por el objeto
        this.tareaRepository.delete(tarea.get());
        // Devuelve json de la tarea eliminada
        return ResponseEntity.ok(new DeleteTareaDto(tarea.get().getId(), tarea.get().getTitle()));
    }

    // Busca tareas por categoria (id)
    @GetMapping("/search/category/{id}")
    public ResponseEntity<List<Tarea>> findByCategory(@PathVariable UUID id) {
        // UUID del usuario activo
        UUID ownerId = AuthenticationUtility.getCurrentUser().getId();
        return ResponseEntity.ok(this.tareaRepository.findAllByCategoriesIdAndOwnerId(id, ownerId));
    }

    // Actualiza Tarea
    private Tarea actualizarTarea(Tarea originalT, Tarea nuevaT) {

        @SuppressWarnings({ "unchecked" })
        Map<String, Object> originalProperties = this.objectMapper.convertValue(originalT, Map.class);

        @SuppressWarnings({ "unchecked" })
        Map<String, Object> partialProperties = this.objectMapper.convertValue(nuevaT, Map.class);

        // Remueve id que se le pasa
        partialProperties.remove("id");
        partialProperties.forEach((key, value) -> {
            if (Objects.nonNull(value)) {
                originalProperties.put(key, value);
            }
        });

        return this.objectMapper.convertValue(originalProperties, originalT.getClass());

    }

}
