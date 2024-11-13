package eccomerce.milena.Ecommerce.dto;

import eccomerce.milena.Ecommerce.model.Pagamentos;

public record FormaPgtoRequestDTO(Pagamentos pagamento, String form_pgto, Integer qntd_parcelas) {
}