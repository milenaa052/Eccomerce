package eccomerce.milena.Ecommerce.repository;

import eccomerce.milena.Ecommerce.model.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedidos, Integer> {
}
