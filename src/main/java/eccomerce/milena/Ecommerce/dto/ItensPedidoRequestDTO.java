package eccomerce.milena.Ecommerce.dto;

import eccomerce.milena.Ecommerce.model.Pedidos;
import eccomerce.milena.Ecommerce.model.Produto;

public record ItensPedidoRequestDTO(Pedidos pedido, Produto produto, Integer quantidade, Double preco_produtos) {
}