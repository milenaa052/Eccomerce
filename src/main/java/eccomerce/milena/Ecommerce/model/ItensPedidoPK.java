package eccomerce.milena.Ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class ItensPedidoPK {

    @Column(name = "pedido_id")
    private Integer pedido_id;

    @Column(name = "produto_id")
    private Integer produto_id;

    public Integer getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Integer produto_id) {
        this.produto_id = produto_id;
    }

    public Integer getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(Integer pedido_id) {
        this.pedido_id = pedido_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItensPedidoPK that = (ItensPedidoPK) o;
        return Objects.equals(pedido_id, that.pedido_id) && Objects.equals(produto_id, that.produto_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido_id, produto_id);
    }
}
