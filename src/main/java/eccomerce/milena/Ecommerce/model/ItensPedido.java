package eccomerce.milena.Ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Objects;

@Entity
@Table(name = "ItensPedido")
public class ItensPedido {

    @EmbeddedId
    private ItensPedidoPK id;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedidoId", referencedColumnName = "IdPedidos")
    @JsonIgnore
    private Pedidos pedido;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produtoId", referencedColumnName = "idProdutos")
    @JsonIgnoreProperties("produtos")
    private Produto produto;

    @Column
    private Integer quantidade;

    @Column(nullable = false)
    @PositiveOrZero
    private Double precoProdutos;

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

    public Double getPrecoProdutos() {
        return precoProdutos;
    }

    public void setPrecoProdutos(Double precoProdutos) {
        this.precoProdutos = precoProdutos;
    }
}
