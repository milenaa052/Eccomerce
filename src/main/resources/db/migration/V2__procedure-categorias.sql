DELIMITER $$
	CREATE OR REPLACE PROCEDURE InserirCategorias()
BEGIN
    INSERT INTO categorias (nome)
    VALUES
        ('Calçados'),
        ('Roupas Masculinas'),
        ('Roupas Femininas'),
        ('Roupas íntimas'),
        ('Meias'),
        ('Roupas Infantil'),
        ('Roupas Fitnnes'),
        ('Roupas de Esportes'),
        ('Roupas recém-nascido'),
        ('Joias'),
        ('Maquiagens'),
        ('Brinquedos'),
        ('Ferramentas'),
        ('Peças de Carro'),
        ('Peças de Moto'),
        ('Utensílios de cozinha'),
        ('Eletrodomésticos'),
        ('Eletrônicos'),
        ('Móveis'),
        ('Perfumes'),
        ('Skin Care'),
        ('Pet'),
        ('Produtos para cabelo');
END$$
DELIMITER ;

call InserirCategorias();