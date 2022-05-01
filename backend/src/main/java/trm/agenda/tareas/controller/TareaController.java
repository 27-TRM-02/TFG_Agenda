package trm.agenda.tareas.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.Entity;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trm.agenda.response.exception.EntityNotFoundException;
import trm.agenda.tareas.domain.model.Tarea;
import trm.agenda.tareas.domain.repository.TareaRepository;

@RestController
@RequestMapping("/tarea")
public class TareaController {

    @Autowired
    private TareaRepository tareaRepository;

    @GetMapping("")
    private ResponseEntity<List<Tarea>> list() {
        return ResponseEntity.ok(this.tareaRepository.findAll());
    }

    @PostMapping("/new")
    public ResponseEntity<Tarea> newTask(@Valid @RequestBody Tarea task) {
        this.tareaRepository.save(task);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> findById(@PathVariable UUID id) {
        // Almacenamamos en tarea
        Optional<Tarea> tarea = this.tareaRepository.findById(id);
        tarea.orElseThrow(() -> new EntityNotFoundException(id, Tarea.class));
        return ResponseEntity.ok(tarea.get());
    }

}
