CREATE TABLE ordem_servico (
	
	os BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	id_pessoa BIGINT(20) NOT NULL,
	id_categoria BIGINT(20) NOT NULL,
	id_status BIGINT(20) NOT NULL,
	equipamento VARCHAR(50) NOT NULL,
	descricao VARCHAR(200),
	defeito VARCHAR(100),
	data_recebimento DATE NOT NULL,
	data_entrega DATE,
	valor DECIMAL(10,2),
	laudo_tecnico VARCHAR(100),
	garantia VARCHAR(20),
	FOREIGN KEY (id_pessoa) REFERENCES pessoa(id),
	FOREIGN KEY (id_categoria) REFERENCES categoria(id),
	FOREIGN KEY (id_status) REFERENCES status(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO ordem_servico (id_pessoa, id_categoria, id_status, equipamento, descricao, defeito, data_recebimento, 
	data_entrega, valor, laudo_tecnico, garantia)
	VALUES (1, 1, 1, 'Computador ITAUTEC', 'Falta parafusos', 'Não liga', '2020-05-03', null, 100.00, 
		'Problema na placa mãe', '60 dias');
INSERT INTO ordem_servico (id_pessoa, id_categoria, id_status, equipamento, descricao, defeito, data_recebimento, 
	data_entrega, valor, laudo_tecnico, garantia)
	VALUES (2, 2, 5, 'Sim positivo', 'Tela trincada, carcaça lateral rachada', 'Lentidão', '2020-05-01', '2020-05-02', 80.00, 
		'Virus', 'Sem garantia');
INSERT INTO ordem_servico (id_pessoa, id_categoria, id_status, equipamento, descricao, defeito, data_recebimento, 
	data_entrega, valor, laudo_tecnico, garantia)
	VALUES (3, 3, 4, 'Samsung Galaxy J5', 'Tela trincada', 'Sem imagem', '2020-05-01', null, 200.00, 
		null, null);
INSERT INTO ordem_servico (id_pessoa, id_categoria, id_status, equipamento, descricao, defeito, data_recebimento, 
	data_entrega, valor, laudo_tecnico, garantia)
    VALUES (4, 3, 3, 'LG K10', null, 'Não inicia', '2020-05-01', '2020-05-03', 120.00, 
		'Conta google bloqueada', 'Sem garantia');
INSERT INTO ordem_servico (id_pessoa, id_categoria, id_status, equipamento, descricao, defeito, data_recebimento, 
	data_entrega, valor, laudo_tecnico, garantia)
	VALUES (5, 2, 2, 'Notebook Samsung RV420', null, 'Liga e não da imagem', '2020-05-03', null, 0.00, 
		null, null);
		