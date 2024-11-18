CREATE TABLE Categorias (
  idCategoria INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL
);

CREATE TABLE Produtos (
  idProdutos INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(255) NOT NULL,
  precoUn DECIMAL(10,2) NOT NULL,
  quantidade INT NOT NULL,
  cor VARCHAR(50),
  categoriaId INT,
  FOREIGN KEY (categoriaId) REFERENCES Categorias(idCategoria)
);

CREATE TABLE Pedidos (
  idPedidos INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  data DATE NOT NULL,
  total DECIMAL(10,2) NOT NULL
);

CREATE TABLE ItensPedido (
  PRIMARY KEY (pedidoId, produtoId),
  pedidoId INT,
  produtoId INT,
  quantidade INT NOT NULL,
  precoProdutos DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (pedidoId) REFERENCES Pedidos(idPedidos),
  FOREIGN KEY (produtoId) REFERENCES Produtos(idProdutos)
);

CREATE TABLE Pagamentos (
  idPagamentos INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  pedidoId INT,
  meioPagamento VARCHAR(50) NOT NULL,
  FOREIGN KEY (pedidoId) REFERENCES Pedidos(idPedidos)
);

CREATE TABLE FormaPGTO (
  pagamentoId INT PRIMARY KEY,
  formaPgto VARCHAR(50) NOT NULL,
  qntdParcelas INT,
  FOREIGN KEY (pagamentoId) REFERENCES Pagamentos(idPagamentos)
);