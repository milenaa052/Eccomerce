package eccomerce.milena.Ecommerce.controller;

import eccomerce.milena.Ecommerce.dto.ItensPedidoRequestDTO;
import eccomerce.milena.Ecommerce.dto.PedidoRequestDTO;
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
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItensPedidoRepository itensPedidoRepository;

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

    @PostMapping
    public ResponseEntity<Pedidos> save(@RequestBody PedidoRequestDTO dto) {
        if(dto.data() == null || dto.itens().isEmpty()) {
            return ResponseEntity.status(428).build();
        }

        Pedidos pedido = new Pedidos();
        pedido.setData(dto.data());

        double total = processarItensPedido(pedido, dto.itens());
        pedido.setTotal(total);

        this.pedidoRepository.save(pedido);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedidos> update(@PathVariable Integer id, @RequestBody PedidoRequestDTO dto) {
        Pedidos pedido = this.pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pedido não encontrado"));

        if (dto.data() != null) {
            pedido.setData(dto.data());
        }

        double total = processarItensPedido(pedido, dto.itens());
        pedido.setTotal(total);

        pedidoRepository.save(pedido);
        return ResponseEntity.ok(pedido);
    }

    private double processarItensPedido(Pedidos pedido, List<ItensPedidoRequestDTO> itens) {
        double total = 0.0;

        for (ItensPedidoRequestDTO itemDto : itens) {
            Produto produto = produtoRepository.findById(itemDto.produtoId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

            if (produto.getQuantidade() < itemDto.quantidade()) {
                throw new IllegalArgumentException("Quantidade indisponível para o produto: " + produto.getDescricao());
            }

            produto.setQuantidade(produto.getQuantidade() - itemDto.quantidade());
            produtoRepository.save(produto);

            ItensPedido itemPedido = new ItensPedido();
            ItensPedidoPK pk = new ItensPedidoPK();
            pk.setPedidoId(pedido.getIdPedidos());
            pk.setProdutoId(produto.getIdProdutos());
            itemPedido.setId(pk);
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemDto.quantidade());
            itemPedido.setPrecoProdutos(produto.getPrecoUn() * itemDto.quantidade());

            itensPedidoRepository.save(itemPedido);

            total += itemPedido.getPrecoProdutos();
        }

        return total;
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