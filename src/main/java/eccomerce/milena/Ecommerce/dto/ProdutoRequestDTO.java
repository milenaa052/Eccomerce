package eccomerce.milena.Ecommerce.dto;

import eccomerce.milena.Ecommerce.model.Categoria;

public record ProdutoRequestDTO(String descricao, Double preco_un, Integer quantidade, String cor, Categoria categoria_id) {
}