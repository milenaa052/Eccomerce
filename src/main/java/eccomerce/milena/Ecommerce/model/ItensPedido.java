package eccomerce.milena.Ecommerce.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "itens_pedido")
public class ItensPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItensPedido;

    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id_pedidos")
    private Pedidos pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id_produtos")
    private Produto produto;

    @Column
    private Integer quantidade;

    @Column
    private Double preco_produtos;

    public Integer getIdItensPedido() {
        return idItensPedido;
    }

    public void setIdItensPedido(Integer idItensPedido) {
        this.idItensPedido = idItensPedido;
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

    public Number getPreco_produtos() {
        return preco_produtos;
    }

    public void setPreco_produtos(Double preco_produtos) {
        this.preco_produtos = preco_produtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItensPedido that = (ItensPedido) o;
        return Objects.equals(idItensPedido, that.idItensPedido) && Objects.equals(pedido, that.pedido) && Objects.equals(produto, that.produto) && Objects.equals(quantidade, that.quantidade) && Objects.equals(preco_produtos, that.preco_produtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idItensPedido, pedido, produto, quantidade, preco_produtos);
    }
}