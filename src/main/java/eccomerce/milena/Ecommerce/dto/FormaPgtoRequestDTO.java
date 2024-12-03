package eccomerce.milena.Ecommerce.dto;

import eccomerce.milena.Ecommerce.model.FormaPagamento;
import eccomerce.milena.Ecommerce.model.MeioPagamento;

public record FormaPgtoRequestDTO(Integer pedidoId, MeioPagamento meioPagamento, FormaPagamento formaPgto, Integer qntdParcelas) {
}