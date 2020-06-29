CREATE TABLE servico (

	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	os_ordem_servico BIGINT(20) NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	valor DECIMAL(10,2) NOT NULL,
	FOREIGN KEY (os_ordem_servico) REFERENCES ordem_servico(os)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO servico (os_ordem_servico, descricao, valor) VALUES (1, 'Reparo na placa mãe', 100.00);

