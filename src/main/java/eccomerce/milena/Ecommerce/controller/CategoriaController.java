package eccomerce.milena.Ecommerce.controller;

import eccomerce.milena.Ecommerce.model.Categoria;
import eccomerce.milena.Ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}