package eccomerce.milena.Ecommerce.controller;

import eccomerce.milena.Ecommerce.dto.ItensPedidoRequestDTO;
import eccomerce.milena.Ecommerce.model.*;
import eccomerce.milena.Ecommerce.repository.ItensPedidoRepository;
import eccomerce.milena.Ecommerce.repository.PedidoRepository;
import eccomerce.milena.Ecommerce.repository.ProdutoRepository;
import eccomerce.milena.Ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @Autowired
    private UsuarioRepository usuarioRepository;

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
    public ResponseEntity<List<ItensPedido>> save(@RequestBody List<ItensPedidoRequestDTO> itens, @RequestParam Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));


        Pedidos pedido = new Pedidos();
        pedido.setUsuario(usuario);
        pedido.setData(new Date());

        double total = processarItensPedido(pedido, itens);
        pedido.setTotal(total);

        pedidoRepository.save(pedido);
        return ResponseEntity.ok(pedido.getItensPedidos());
    }

    private double processarItensPedido(Pedidos pedido, List<ItensPedidoRequestDTO> itens) {
        double total = 0;

        for (ItensPedidoRequestDTO itemDto : itens) {
            Produto produto = produtoRepository.findById(itemDto.produtoId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

            if (produto.getQuantidade() < itemDto.quantidade()) {
                throw new IllegalArgumentException("Quantidade indisponível para o produto: " + produto.getDescricao());
            }

            produto.setQuantidade(produto.getQuantidade() - itemDto.quantidade());
            produtoRepository.save(produto);

            ItensPedidoPK pk = new ItensPedidoPK();
            pk.setPedidoId(pedido.getIdPedidos());
            pk.setProdutoId(produto.getIdProdutos());

            ItensPedido item = new ItensPedido();
            item.setId(pk);
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemDto.quantidade());
            item.setPrecoProdutos(produto.getPrecoUn() * itemDto.quantidade());

            pedido.getItensPedidos().add(item);

            total += item.getPrecoProdutos();
        }

        return total;
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