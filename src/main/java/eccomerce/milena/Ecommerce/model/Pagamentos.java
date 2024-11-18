package eccomerce.milena.Ecommerce.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Pagamentos")

public class Pagamentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPagamentos;

    @OneToOne
    @JoinColumn(name = "pedidoId", referencedColumnName = "idPedidos")
    private Pedidos pedido;

    @Column
    private String meioPagamento;

    public Integer getIdPagamentos() {
        return idPagamentos;
    }

    public void setIdPagamentos(Integer idPagamentos) {
        this.idPagamentos = idPagamentos;
    }

    public Pedidos getPedido() {
        return pedido;
    }

    public void setPedido(Pedidos pedido) {
        this.pedido = pedido;
    }

    public String getMeioPagamento() {
        return meioPagamento;
    }

    public void setMeioPagamento(String meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamentos that = (Pagamentos) o;
        return Objects.equals(idPagamentos, that.idPagamentos) && Objects.equals(pedido, that.pedido) && Objects.equals(meioPagamento, that.meioPagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPagamentos, pedido, meioPagamento);
    }
}