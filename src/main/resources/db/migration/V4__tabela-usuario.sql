CREATE TABLE Usuarios {
    idUsuario INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    pedidoId INT,
    FOREIGN KEY (pedidoId) REFERENCES Pedidos(idPedidos)
}