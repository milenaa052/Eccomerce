CREATE TABLE audita_preco_produto (
	idAuditoria INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	dataModificacao DATE NOT NULL,
	produtoId INT,
	FOREIGN KEY (produtoId) REFERENCES Produtos(idProdutos),
	precoAntigo DECIMAL(10,2),
	PrecoNovo DECIMAL(10,2),
	Motivo VARCHAR(255)
)

DELIMITER $$
	CREATE OR REPLACE TRIGGER trg_audita_preco_prod
	AFTER UPDATE ON Produtos
	FOR EACH ROW
	BEGIN
		IF(old.precoUn <> new.precoUn) THEN
			INSERT INTO audita_preco_produto(dataModificacao, produtoId, precoAntigo, precoNovo, motivo)
			VALUES(now(), old.idProdutos, old.precoUn, new.precoUn, "Alteração de preço do produto");
		END IF;
	END
$$
DELIMITER;