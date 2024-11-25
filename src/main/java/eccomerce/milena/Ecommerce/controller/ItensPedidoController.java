package eccomerce.milena.Ecommerce.controller;

import eccomerce.milena.Ecommerce.dto.ItensPedidoRequestDTO;
import eccomerce.milena.Ecommerce.model.ItensPedido;
import eccomerce.milena.Ecommerce.model.ItensPedidoPK;
import eccomerce.milena.Ecommerce.model.Pedidos;
import eccomerce.milena.Ecommerce.model.Produto;
import eccomerce.milena.Ecommerce.repository.ItensPedidoRepository;
import eccomerce.milena.Ecommerce.repository.PedidoRepository;
import eccomerce.milena.Ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-pedido")
public class ItensPedidoController {

    @Autowired
    private ItensPedidoRepository itensPedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public ResponseEntity<List<ItensPedido>> findAll() {
        List<ItensPedido> itens = this.itensPedidoRepository.findAll();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<List<ItensPedido>> findItensPedido(@PathVariable Integer pedidoId) {
        List<ItensPedido> itens = itensPedidoRepository.findByPedido_IdPedidos(pedidoId);
        if (itens.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itens);
    }

    @PostMapping
    public ResponseEntity<ItensPedido> save(@RequestBody ItensPedidoRequestDTO dto) {
        Pedidos pedido = pedidoRepository.findById(dto.pedidoId())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (produto.getQuantidade() < dto.quantidade()) {
            return ResponseEntity.status(400).body(null);
        }

        produto.setQuantidade(produto.getQuantidade() - dto.quantidade());
        produtoRepository.save(produto);

        ItensPedidoPK pk = new ItensPedidoPK();
        pk.setPedidoId(pedido.getIdPedidos());
        pk.setProdutoId(produto.getIdProdutos());

        ItensPedido item = new ItensPedido();
        item.setId(pk);
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(dto.quantidade());
        item.setPrecoProdutos(produto.getPrecoUn() * dto.quantidade());

        itensPedidoRepository.save(item);
        return ResponseEntity.ok(item);
    }

    @PutMapping("/{pedidoId}/{produtoId}")
    public ResponseEntity<ItensPedido> update(
            @PathVariable Integer pedidoId,
            @PathVariable Integer produtoId,
            @RequestBody ItensPedidoRequestDTO dto) {

        ItensPedidoPK pk = new ItensPedidoPK();
        pk.setPedidoId(pedidoId);
        pk.setProdutoId(produtoId);

        ItensPedido item = itensPedidoRepository.findById(pk)
                .orElseThrow(() -> new IllegalArgumentException("Item de pedido não encontrado"));

        Produto produto = item.getProduto();

        int diferencaQuantidade = dto.quantidade() - item.getQuantidade();
        if (produto.getQuantidade() < diferencaQuantidade) {
            return ResponseEntity.status(400).body(null);
        }

        produto.setQuantidade(produto.getQuantidade() - diferencaQuantidade);
        produtoRepository.save(produto);

        item.setQuantidade(dto.quantidade());
        item.setPrecoProdutos(produto.getPrecoUn() * dto.quantidade());

        itensPedidoRepository.save(item);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{pedidoId}/{produtoId}")
    public ResponseEntity<Void> delete(@PathVariable Integer pedidoId, @PathVariable Integer produtoId) {
        ItensPedidoPK pk = new ItensPedidoPK();
        pk.setPedidoId(pedidoId);
        pk.setProdutoId(produtoId);

        ItensPedido item = itensPedidoRepository.findById(pk)
                .orElseThrow(() -> new IllegalArgumentException("Item de pedido não encontrado"));

        Produto produto = item.getProduto();
        produto.setQuantidade(produto.getQuantidade() + item.getQuantidade());
        produtoRepository.save(produto);

        itensPedidoRepository.delete(item);
        return ResponseEntity.noContent().build();
    }
}