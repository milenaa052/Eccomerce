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