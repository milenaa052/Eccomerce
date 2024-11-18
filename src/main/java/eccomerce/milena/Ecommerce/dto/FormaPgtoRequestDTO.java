package eccomerce.milena.Ecommerce.dto;

import eccomerce.milena.Ecommerce.model.Pagamentos;

public record FormaPgtoRequestDTO(Pagamentos pagamento, String formPgto, Integer qntdParcelas) {
}