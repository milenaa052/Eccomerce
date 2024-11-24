package eccomerce.milena.Ecommerce.repository;

import eccomerce.milena.Ecommerce.model.ItensPedido;
import eccomerce.milena.Ecommerce.model.ItensPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedidoRepository extends JpaRepository<ItensPedido, ItensPedidoPK> {
}
