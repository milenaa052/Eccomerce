package eccomerce.milena.Ecommerce.dto;

import eccomerce.milena.Ecommerce.model.Pedidos;
import eccomerce.milena.Ecommerce.model.Produto;

public record ItensPedidoRequestDTO(Integer produtoId, Integer quantidade) {
}