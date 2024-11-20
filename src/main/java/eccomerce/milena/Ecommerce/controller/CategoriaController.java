package eccomerce.milena.Ecommerce.controller;

import eccomerce.milena.Ecommerce.dto.CategoriaRequestDTO;
import eccomerce.milena.Ecommerce.model.Categoria;
import eccomerce.milena.Ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        List<Categoria> categorias = this.repository.findAll();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public Categoria findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Categoria não encontrada"));
    }

    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody CategoriaRequestDTO dto) {
        if(dto.nome().isEmpty()) {
            return ResponseEntity.status(428).build();
        }

        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());

        this.repository.save(categoria);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Integer id, @RequestBody CategoriaRequestDTO dto) {
        if (dto.nome().isEmpty()) {
            return ResponseEntity.status(428).build();
        }

        Categoria categoria = this.repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Categoria não encontrada"));

        categoria.setNome(dto.nome());

        this.repository.save(categoria);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Categoria categoria = this.repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Categoria não encontrada"));

        this.repository.delete(categoria);
        return ResponseEntity.noContent().build();
    }
}