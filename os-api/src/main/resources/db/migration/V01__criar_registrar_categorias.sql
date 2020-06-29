CREATE TABLE categoria (
	
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(30) NOT NULL
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome) VALUES ('PC Desktop');
INSERT INTO categoria (nome) VALUES ('Notebook');
INSERT INTO categoria (nome) VALUES ('Celular');
INSERT INTO categoria (nome) VALUES ('Outros');