package eccomerce.milena.Ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Objects;

@Entity
@Table(name = "itens_pedido")
public class ItensPedido {

    @EmbeddedId
    private ItensPedidoPK id;

    @ManyToOne
    @MapsId("pedido_id")
    @JoinColumn(name = "pedido_id", referencedColumnName = "id_pedidos")
    @JsonIgnoreProperties("pedidos")
    private Pedidos pedido;

    @ManyToOne
    @MapsId("produto_id")
    @JoinColumn(name = "produto_id", referencedColumnName = "id_produtos")
    @JsonIgnoreProperties("produtos")
    private Produto produto;

    @Column
    private Integer quantidade;

    @Column(nullable = false)
    @PositiveOrZero
    private Double preco_produtos;

    public ItensPedidoPK getId() {
        return id;
    }

    public void setId(ItensPedidoPK id) {
        this.id = id;
    }

    public Pedidos getPedido() {
        return pedido;
    }

    public void setPedido(Pedidos pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco_produtos() {
        return preco_produtos;
    }

    public void setPreco_produtos(Double preco_produtos) {
        this.preco_produtos = preco_produtos;
    }
}
