package eccomerce.milena.Ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pedidos;

    @Column
    private Date data;

    @Column
    private Double total;

    @OneToMany(mappedBy = "pedido")
    @JsonIgnoreProperties("pedido")
    private List<ItensPedido> itensPedidos;

    public Integer getId_pedidos() {
        return id_pedidos;
    }

    public void setId_pedidos(Integer id_pedidos) {
        this.id_pedidos = id_pedidos;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ItensPedido> getPedidos() {
        return itensPedidos;
    }

    public void setPedidos(List<ItensPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }
}