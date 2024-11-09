package eccomerce.milena.Ecommerce.repository;

import eccomerce.milena.Ecommerce.model.Pagamentos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamentos, Integer> {
}
