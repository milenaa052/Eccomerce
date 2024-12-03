package eccomerce.milena.Ecommerce.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProdutos;

    @Column
    private String descricao;

    @Column
    private Double precoUn;

    @Column
    private Integer quantidade;

    @Column
    private String cor;

    @ManyToOne
    @JoinColumn(name = "categoriaId", referencedColumnName = "idCategoria")
    private Categoria categoria;

    public Integer getIdProdutos() {
        return idProdutos;
    }

    public void setIdProdutos(Integer idProdutos) {
        this.idProdutos = idProdutos;
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

    public Double getPrecoUn() {
        return precoUn;
    }

    public void setPrecoUn(Double precoUn) {
        this.precoUn = precoUn;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Categoria getCategoriaId() {
        return categoria;
    }

    public void setCategoriaId(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(idProdutos, produto.idProdutos) && Objects.equals(descricao, produto.descricao) && Objects.equals(precoUn, produto.precoUn) && Objects.equals(quantidade, produto.quantidade) && Objects.equals(cor, produto.cor) && Objects.equals(categoria, produto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProdutos, descricao, precoUn, quantidade, cor, categoria);
    }
}