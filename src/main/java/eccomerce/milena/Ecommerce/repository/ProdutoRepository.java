package eccomerce.milena.Ecommerce.repository;

import eccomerce.milena.Ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
