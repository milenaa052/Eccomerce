package eccomerce.milena.Ecommerce.dto;

public record ProdutoRequestDTO(String descricao, Double precoUn, Integer quantidade, String cor, Integer categoriaId) {
}