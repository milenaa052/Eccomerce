package eccomerce.milena.Ecommerce.dto;


import java.time.LocalDate;
import java.util.List;

public record PedidoRequestDTO(LocalDate data, List<ItensPedidoRequestDTO> itens) {
}