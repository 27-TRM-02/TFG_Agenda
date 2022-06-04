package trm.agenda.categorias.controller;

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
import trm.agenda.categorias.domain.model.Categoria;
import trm.agenda.categorias.domain.model.dto.DeleteCategoriaDto;
import trm.agenda.categorias.domain.repository.CategoriaRepository;
import trm.agenda.response.exception.EntityNotFoundException;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    // Devuelve listado con todas las categorias
    @GetMapping("")
    private ResponseEntity<List<Categoria>> list() {
        return ResponseEntity.ok(this.categoriaRepository.findAll());
    }

    // Crea nueva categoria
    @PostMapping("/new")
    public ResponseEntity<Categoria> newCategory(@Valid @RequestBody Categoria categoria) {
        // Busca por el id al Usuario activo creando la categoría
        Usuario currentUser = usuarioRepository.findById(AuthenticationUtility.getCurrentUser().getId()).get();
        // Setea el owner
        categoria.setOwner(currentUser);
        this.categoriaRepository.save(categoria);
        return ResponseEntity.ok(categoria);
    }

    // Busca por id
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable UUID id) {
        // UUID del usuario activo
        UUID ownerId = AuthenticationUtility.getCurrentUser().getId();
        // Almacenamamos en "categoria" la categoria buscada por su id y su owner
        Optional<Categoria> categoria = this.categoriaRepository.findByIdAndOwnerId(id, ownerId);
        // Lanza excepcion -> return respuesta
        categoria.orElseThrow(() -> new EntityNotFoundException(id, Categoria.class));
        return ResponseEntity.ok(categoria.get());
    }

    // Edita Categoria
    @PatchMapping("/edit/{id}")
    public ResponseEntity<Categoria> updateTask(@PathVariable UUID id, @Valid @RequestBody Categoria category) {
        // UUID del usuario activo
        UUID ownerId = AuthenticationUtility.getCurrentUser().getId();
        // Almacenamamos en "categoria" la categoria buscada por su id y su owner
        Optional<Categoria> categoria = this.categoriaRepository.findByIdAndOwnerId(id, ownerId);
        // Lanza excepcion -> return respuesta
        categoria.orElseThrow(() -> new EntityNotFoundException(id, Categoria.class));

        this.categoriaRepository.save(this.actualizarCategoria(categoria.get(), category));
        return ResponseEntity.ok(categoria.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteCategoriaDto> deleteById(@PathVariable UUID id) {
        // UUID del usuario activo
        UUID ownerId = AuthenticationUtility.getCurrentUser().getId();
        // Almacenamamos en "categoria" la categoria buscada por su id
        Optional<Categoria> categoria = this.categoriaRepository.findByIdAndOwnerId(id, ownerId);
        // Lanza excepcion -> return respuesta
        categoria.orElseThrow(() -> new EntityNotFoundException(id, Categoria.class));
        // Borramos categoria de la bbdd -> se busca por el objeto
        this.categoriaRepository.delete(categoria.get());
        // Devuelve json de la tarea eliminada
        return ResponseEntity.ok(new DeleteCategoriaDto(categoria.get().getId(), categoria.get().getTitle()));
    }

    // Actualiza Categoría
    private Categoria actualizarCategoria(Categoria originalC, Categoria nuevaC) {

        @SuppressWarnings({ "unchecked" })
        Map<String, Object> originalProperties = this.objectMapper.convertValue(originalC, Map.class);

        @SuppressWarnings({ "unchecked" })
        Map<String, Object> partialProperties = this.objectMapper.convertValue(nuevaC, Map.class);

        // Remueve id que se le pasa
        partialProperties.remove("id");
        partialProperties.forEach((key, value) -> {
            if (Objects.nonNull(value)) {
                originalProperties.put(key, value);
            }
        });

        return this.objectMapper.convertValue(originalProperties, originalC.getClass());

    }

}
