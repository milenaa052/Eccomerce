package eccomerce.milena.Ecommerce.dto;

import java.util.List;

public record PedidoRequestDTO(List<ItensPedidoRequestDTO> itens) {
}