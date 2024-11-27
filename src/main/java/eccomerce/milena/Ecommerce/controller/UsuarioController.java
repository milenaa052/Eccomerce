package eccomerce.milena.Ecommerce.controller;

import eccomerce.milena.Ecommerce.dto.UsuarioRequestDTO;
import eccomerce.milena.Ecommerce.model.Usuario;
import eccomerce.milena.Ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> usuarios = this.repository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public Usuario findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuario não encontrada"));
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody UsuarioRequestDTO dto) {
        if(dto.nome().isEmpty() || dto.email().isEmpty() || dto.senha().isEmpty()) {
            return ResponseEntity.status(428).build();
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());

        this.repository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id, @RequestBody UsuarioRequestDTO dto) {
        if(dto.nome().isEmpty() || dto.email().isEmpty() || dto.senha().isEmpty()) {
            return ResponseEntity.status(428).build();
        }

        Usuario usuario = this.repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuario não encontrada"));

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());

        this.repository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Usuario usuario = this.repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuario não encontrada"));

        this.repository.delete(usuario);
        return ResponseEntity.noContent().build();
    }
}