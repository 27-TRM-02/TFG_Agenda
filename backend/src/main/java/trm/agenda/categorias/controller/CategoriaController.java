package trm.agenda.categorias.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trm.agenda.categorias.domain.model.Categoria;
import trm.agenda.categorias.domain.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("")
    private ResponseEntity<List<Categoria>> list() {
        return ResponseEntity.ok(this.categoriaRepository.findAll());
    }

    // Crea nueva tarea
    @PostMapping("/new")
    public ResponseEntity<Categoria> newTask(@Valid @RequestBody Categoria categoria) {
        this.categoriaRepository.save(categoria);
        return ResponseEntity.ok(categoria);
    }

}
