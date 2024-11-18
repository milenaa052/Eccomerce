package eccomerce.milena.Ecommerce.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pagamentos")

public class Pagamentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pagamentos;

    @OneToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id_pedidos")
    private Pedidos pedido;

    @Column
    private String meio_pagamento;

    public Integer getId_pagamentos() {
        return id_pagamentos;
    }

    public void setId_pagamentos(Integer id_pagamentos) {
        this.id_pagamentos = id_pagamentos;
    }

    public Pedidos getPedido() {
        return pedido;
    }

    public void setPedido(Pedidos pedido) {
        this.pedido = pedido;
    }

    public String getMeio_pagamento() {
        return meio_pagamento;
    }

    public void setMeio_pagamento(String meio_pagamento) {
        this.meio_pagamento = meio_pagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamentos that = (Pagamentos) o;
        return Objects.equals(id_pagamentos, that.id_pagamentos) && Objects.equals(pedido, that.pedido) && Objects.equals(meio_pagamento, that.meio_pagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_pagamentos, pedido, meio_pagamento);
    }
}