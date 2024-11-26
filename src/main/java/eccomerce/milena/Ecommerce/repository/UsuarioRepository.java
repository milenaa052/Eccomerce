package eccomerce.milena.Ecommerce.repository;

import eccomerce.milena.Ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
