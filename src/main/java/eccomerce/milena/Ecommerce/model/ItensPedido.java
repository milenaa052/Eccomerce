package eccomerce.milena.Ecommerce.model;

import jakarta.persistence.*;

import java.util.Objects;

public class ItensPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_itens_pedido;

    @OneToMany
    @JoinColumn(name = "pedido_id", referencedColumnName = "id_pedidos")
    private Pedidos pedido_id;

    @OneToMany
    @JoinColumn(name = "produto_id", referencedColumnName = "id_produtos")
    private Produto produto_id;

    @Column
    private Integer quantidade;

    @Column
    private Number preco_produtos;

    public Integer getId_itens_pedido() {
        return id_itens_pedido;
    }

    public void setId_itens_pedido(Integer id_itens_pedido) {
        this.id_itens_pedido = id_itens_pedido;
    }

    public Pedidos getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(Pedidos pedido_id) {
        this.pedido_id = pedido_id;
    }

    public Produto getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Produto produto_id) {
        this.produto_id = produto_id;
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

    public void setPreco_produtos(Number preco_produtos) {
        this.preco_produtos = preco_produtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItensPedido that = (ItensPedido) o;
        return Objects.equals(id_itens_pedido, that.id_itens_pedido) && Objects.equals(pedido_id, that.pedido_id) && Objects.equals(produto_id, that.produto_id) && Objects.equals(quantidade, that.quantidade) && Objects.equals(preco_produtos, that.preco_produtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_itens_pedido, pedido_id, produto_id, quantidade, preco_produtos);
    }
}
