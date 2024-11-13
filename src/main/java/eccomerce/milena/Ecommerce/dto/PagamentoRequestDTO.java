package eccomerce.milena.Ecommerce.dto;

import eccomerce.milena.Ecommerce.model.FormaPgto;
import eccomerce.milena.Ecommerce.model.Pedidos;

public record PagamentoRequestDTO(Pedidos pedido, String meio_pagamento, FormaPgto pagamentos) {
}