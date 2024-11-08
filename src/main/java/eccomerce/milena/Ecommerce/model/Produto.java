package eccomerce.milena.Ecommerce.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_produtos;

    @Column
    private String descricao;

    @Column
    private Number preco_un;

    @Column
    private Integer quantidade;

    @Column
    private String cor;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id_categoria")
    private Categoria categoria_id;

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

    public Number getPreco_un() {
        return preco_un;
    }

    public void setPreco_un(Number preco_un) {
        this.preco_un = preco_un;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Categoria getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Categoria categoria_id) {
        this.categoria_id = categoria_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id_produtos, produto.id_produtos) && Objects.equals(descricao, produto.descricao) && Objects.equals(preco_un, produto.preco_un) && Objects.equals(quantidade, produto.quantidade) && Objects.equals(cor, produto.cor) && Objects.equals(categoria_id, produto.categoria_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_produtos, descricao, preco_un, quantidade, cor, categoria_id);
    }
}
