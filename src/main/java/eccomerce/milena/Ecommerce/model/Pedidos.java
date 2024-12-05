package eccomerce.milena.Ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedidos;

    @Column(name = "data")
    private Date data;

    @Column(name = "total", nullable = false)
    private Double total;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItensPedido> itensPedidos =new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "usuarioId", referencedColumnName = "idUsuario")
    @JsonIgnore
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "ItensPedido",
            joinColumns = @JoinColumn(name = "pedidoId", referencedColumnName = "idPedidos"),
            inverseJoinColumns = @JoinColumn(name = "produtoId", referencedColumnName = "idProdutos")
    )
    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

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

    public List<ItensPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(List<ItensPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}