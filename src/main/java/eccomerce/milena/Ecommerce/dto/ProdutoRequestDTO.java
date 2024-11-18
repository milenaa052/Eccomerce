package eccomerce.milena.Ecommerce.dto;

import eccomerce.milena.Ecommerce.model.Categoria;

public record ProdutoRequestDTO(String descricao, Double precoUn, Integer quantidade, String cor, Categoria categoriaId) {
}