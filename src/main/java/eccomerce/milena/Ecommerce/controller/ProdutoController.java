package eccomerce.milena.Ecommerce.controller;

import eccomerce.milena.Ecommerce.dto.ProdutoRequestDTO;
import eccomerce.milena.Ecommerce.model.Produto;
import eccomerce.milena.Ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;


    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> produtos = this.repository.findAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public Produto findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Produto não encontrado"));
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody ProdutoRequestDTO dto) {
        if(dto.descricao().isEmpty()) {
            return ResponseEntity.status(428).build();
        }

        Produto produto = new Produto();
        produto.setDescricao(dto.descricao());
        produto.setPreco_un(dto.preco_un());
        produto.setQuantidade(dto.quantidade());
        produto.setCor(dto.cor());
        produto.setCategoria_id(dto.categoria_id());

        this.repository.save(produto);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Produto produto = this.repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Produto não encontrado"));

        this.repository.delete(produto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Integer id, @RequestBody ProdutoRequestDTO dto) {
        if (dto.descricao().isEmpty()) {
            return ResponseEntity.status(428).build();
        }

        Produto produto = this.repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Produto não encontrado"));

        produto.setDescricao(dto.descricao());
        produto.setPreco_un(dto.preco_un());
        produto.setQuantidade(dto.quantidade());
        produto.setCor(dto.cor());
        produto.setCategoria_id(dto.categoria_id());

        this.repository.save(produto);
        return ResponseEntity.ok(produto);
    }

}