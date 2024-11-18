package eccomerce.milena.Ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedidos;

    @Column
    private Date data;

    @Column
    private Double total;

    @OneToMany(mappedBy = "pedido")
    @JsonIgnoreProperties("pedido")
    private List<ItensPedido> itensPedidos;

    public Integer getIdPedidos() {
        return idPedidos;
    }

    public void setIdPedidos(Integer idPedidos) {
        this.idPedidos = idPedidos;
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