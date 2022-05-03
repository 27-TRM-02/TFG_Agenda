package trm.agenda.categorias.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trm.agenda.categorias.domain.model.Categoria;
import trm.agenda.categorias.domain.repository.CategoriaRepository;
import trm.agenda.response.exception.EntityNotFoundException;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Devuelve listado con todas las categorias
    @GetMapping("")
    private ResponseEntity<List<Categoria>> list() {
        return ResponseEntity.ok(this.categoriaRepository.findAll());
    }

    // Crea nueva categoria
    @PostMapping("/new")
    public ResponseEntity<Categoria> newCategory(@Valid @RequestBody Categoria categoria) {
        this.categoriaRepository.save(categoria);
        return ResponseEntity.ok(categoria);
    }

    // Busca por id
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable UUID id) {
        // Almacenamamos en "categoria" la categoria buscada por su id
        Optional<Categoria> categoria = this.categoriaRepository.findById(id);
        // Lanza excepcion -> return respuesta
        categoria.orElseThrow(() -> new EntityNotFoundException(id, Categoria.class));
        return ResponseEntity.ok(categoria.get());
    }

}
