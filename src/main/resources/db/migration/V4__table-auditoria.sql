CREATE TABLE audita_preco_produto (
	idAuditoria INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	dataModificacao DATE NOT NULL,
	produtoId INT,
	FOREIGN KEY (produtoId) REFERENCES Produtos(idProdutos),
	precoAntigo DECIMAL(10,2),
	PrecoNovo DECIMAL(10,2),
	Motivo VARCHAR(255)
)