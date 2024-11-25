package eccomerce.milena.Ecommerce.repository;

import eccomerce.milena.Ecommerce.model.ItensPedido;
import eccomerce.milena.Ecommerce.model.ItensPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItensPedidoRepository extends JpaRepository<ItensPedido, ItensPedidoPK> {
    List<ItensPedido> findByPedido_IdPedidos(Integer pedidoId);
}