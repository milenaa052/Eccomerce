CREATE TABLE Categorias (
  id_categoria INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL
);

CREATE TABLE Produtos (
  id_produtos INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(255) NOT NULL,
  preco_un DECIMAL(10,2) NOT NULL,
  quantidade INT NOT NULL,
  cor VARCHAR(50),
  categoria_id INT,
  FOREIGN KEY (categoria_id) REFERENCES Categorias(id_categoria)
);

CREATE TABLE Pedidos (
  id_pedidos INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  data DATE NOT NULL,
  total DECIMAL(10,2) NOT NULL
);

CREATE TABLE Itens_Pedido (
  id_itens_pedido INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  pedido_id INT,
  produto_id INT,
  quantidade INT NOT NULL,
  preco_produtos DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (pedido_id) REFERENCES Pedidos(id_pedidos),
  FOREIGN KEY (produto_id) REFERENCES Produtos(id_produtos)
);

CREATE TABLE Pagamentos (
  id_pagamentos INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  pedido_id INT,
  meio_pagamento VARCHAR(50) NOT NULL,
  FOREIGN KEY (pedido_id) REFERENCES Pedidos(id_pedidos)
);

CREATE TABLE Forma_PGTO (
  id_formPgto INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  pagamento_id INT,
  forma_pgto VARCHAR(50) NOT NULL,
  qntd_parcelas INT,
  FOREIGN KEY (pagamento_id) REFERENCES Pagamentos(id_pagamentos)
);