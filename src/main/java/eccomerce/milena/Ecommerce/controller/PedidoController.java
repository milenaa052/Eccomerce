package eccomerce.milena.Ecommerce.controller;

import eccomerce.milena.Ecommerce.dto.PedidoRequestDTO;
import eccomerce.milena.Ecommerce.model.*;
import eccomerce.milena.Ecommerce.repository.PedidoRepository;
import eccomerce.milena.Ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Pedidos>> findAll() {
        List<Pedidos> pedidos = this.pedidoRepository.findAll();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public Pedidos findById(@PathVariable Integer id) {
        return this.pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pedido não encontrada"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedidos> update(@PathVariable Integer id, @RequestBody PedidoRequestDTO dto) {
        Pedidos pedido = this.pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pedido não encontrado"));

        //double total = processarItensPedido(pedido, dto.itens());
        //pedido.setTotal(total);

        pedidoRepository.save(pedido);
        return ResponseEntity.ok(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Pedidos pedido = this.pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pedido não encontrada"));

        for (ItensPedido item : pedido.getItensPedidos()) {
            Produto produto = item.getProduto();
            produto.setQuantidade(produto.getQuantidade() + item.getQuantidade());
            produtoRepository.save(produto);
        }

        this.pedidoRepository.delete(pedido);
        return ResponseEntity.noContent().build();
    }
}