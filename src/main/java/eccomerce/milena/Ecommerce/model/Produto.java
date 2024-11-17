package eccomerce.milena.Ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_produtos;

    @Column
    private String descricao;

    @Column
    private Double preco_un;

    @Column
    private Integer quantidade;

    @Column
    private String cor;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id_categoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "produto")
    @JsonIgnoreProperties("produto")
    private List<ItensPedido> itensPedidos;

    public Integer getId_produtos() {
        return id_produtos;
    }

    public void setId_produtos(Integer id_produtos) {
        this.id_produtos = id_produtos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco_un() {
        return preco_un;
    }

    public void setPreco_un(Double preco_un) {
        this.preco_un = preco_un;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Categoria getCategoria_id() {
        return categoria;
    }

    public void setCategoria_id(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<ItensPedido> getProdutos() {
        return itensPedidos;
    }

    public void setProdutos(List<ItensPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id_produtos, produto.id_produtos) && Objects.equals(descricao, produto.descricao) && Objects.equals(preco_un, produto.preco_un) && Objects.equals(quantidade, produto.quantidade) && Objects.equals(cor, produto.cor) && Objects.equals(categoria, produto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_produtos, descricao, preco_un, quantidade, cor, categoria);
    }
}