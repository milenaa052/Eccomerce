package eccomerce.milena.Ecommerce.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String senha;

    @OneToMany
    @JoinColumn(name = "pedidoId", referencedColumnName = "idPedidos")
    private Pedidos pedidoId;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Pedidos getPedidosId() {
        return pedidoId;
    }

    public void setPedidosId(Pedidos pedidoId) {
        this.pedidoId = pedidoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(idUsuario, usuario.idUsuario) && Objects.equals(nome, usuario.nome) && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha) && Objects.equals(pedidos, usuario.pedidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nome, email, senha, pedidoId);
    }
}