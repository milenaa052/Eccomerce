package eccomerce.milena.Ecommerce.dto;

import eccomerce.milena.Ecommerce.model.ItensPedido;

import java.util.Date;

public record PedidoRequestDTO(Date date, Double total, ItensPedido pedidos) {
}