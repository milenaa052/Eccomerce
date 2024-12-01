package eccomerce.milena.Ecommerce.dto;

import eccomerce.milena.Ecommerce.model.MeioPagamento;

public record PagamentoRequestDTO(MeioPagamento meioPagamento, Integer pedidoId) {
}