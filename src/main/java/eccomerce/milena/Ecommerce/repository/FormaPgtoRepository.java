package eccomerce.milena.Ecommerce.repository;

import eccomerce.milena.Ecommerce.model.FormaPGTO;
import org.springframework.data.jpa.repository.JpaRepository;
import eccomerce.milena.Ecommerce.model.Pedidos;

public interface FormaPgtoRepository extends JpaRepository<FormaPGTO, Integer> {
    boolean existsByPedidoId(Pedidos pedido);
}
