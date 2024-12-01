package eccomerce.milena.Ecommerce.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Pagamentos")

public abstract class Pagamentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPagamentos;

    @OneToOne
    @JoinColumn(name = "pedidoId", referencedColumnName = "idPedidos")
    private Pedidos pedidoId;

    @Enumerated(EnumType.STRING)
    @Column(name = "meioPagamento", nullable = false)
    private MeioPagamento meioPagamento;

    public abstract void processarPagamento();

    public Integer getIdPagamentos() {
        return idPagamentos;
    }

    public void setIdPagamentos(Integer idPagamentos) {
        this.idPagamentos = idPagamentos;
    }

    public Pedidos getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Pedidos pedidoId) {
        this.pedidoId = pedidoId;
    }

    public MeioPagamento getMeioPagamento() {
        return meioPagamento;
    }

    public void setMeioPagamento(MeioPagamento meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamentos that = (Pagamentos) o;
        return Objects.equals(idPagamentos, that.idPagamentos) && Objects.equals(pedidoId, that.pedidoId) && meioPagamento == that.meioPagamento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPagamentos, pedidoId, meioPagamento);
    }
}