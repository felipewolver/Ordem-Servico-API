CREATE TABLE pessoa (
	
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	cpf VARCHAR(15) NOT NULL,
	nome VARCHAR(50) NOT NULL,
	telefone VARCHAR(15) NOT NULL,
	logradouro VARCHAR(100),
	numero VARCHAR(10),
	complemento VARCHAR(10),
	bairro VARCHAR(50),
	cidade VARCHAR(50),
	estado VARCHAR(30),
	cep VARCHAR(15) NOT NULL

)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (cpf, nome, telefone, logradouro, numero, complemento, bairro, cidade, estado, cep)
	VALUES ('857.766.060-55', 'João Silva', '21525335452', 'Rua do Abacaxi', '10', null, 'Brasil', 'Uberlândia', 'MG', '234562-000');
INSERT INTO pessoa (cpf, nome, telefone, logradouro, numero, complemento, bairro, cidade, estado, cep) 
	values ('783.775.740-80','Maria Rita', '21525332222', 'Rua do Sabiá', '110', 'Apto 101', 'Colina', 'Ribeirão Preto', 'SP', '23456-531');
INSERT INTO pessoa (cpf, nome, telefone, logradouro, numero, complemento, bairro, cidade, estado, cep) 
	values ('227.485.370-33','Pedro Santos', '21525233333', 'Rua da Bateria', '23', null, 'Morumbi', 'Goiânia', 'GO', '34565-333');
INSERT INTO pessoa (cpf, nome, telefone, logradouro, numero, complemento, bairro, cidade, estado, cep) 
	values ('036.917.290-61','Ricardo Pereira', '21525334444', 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', 'Salvador', 'BA', '45674-444');
INSERT INTO pessoa (cpf, nome, telefone, logradouro, numero, complemento, bairro, cidade, estado, cep)
    values ('633.890.100-68','Josué Mariano', '21525335555', 'Av Rio Branco', '321', null, 'Jardins', 'Natal', 'RN', '56785-555');
INSERT INTO pessoa (cpf, nome, telefone, logradouro, numero, complemento, bairro, cidade, estado, cep) 
    values ('094.450.370-57','Pedro Barbosa', '21525336666', 'Av Brasil', '100', null, 'Tubalina', 'Porto Alegre', 'RS', '67897-666');
INSERT INTO pessoa (cpf, nome, telefone, logradouro, numero, complemento, bairro, cidade, estado, cep) 
    values ('662.332.630-85','Henrique Medeiros', '21525337777', 'Rua do Sapo', '26', 'Apto 201', 'Centro', 'Rio de Janeiro', 'RJ', '78907-777');
INSERT INTO pessoa (cpf, nome, telefone, logradouro, numero, complemento, bairro, cidade, estado, cep) 
    values ('555.793.030-25','Carlos Santana', '21525338888', 'Rua da Manga', '433', null, 'Centro', 'Belo Horizonte', 'MG', '87654-888');
INSERT INTO pessoa (cpf, nome, telefone, logradouro, numero, complemento, bairro, cidade, estado, cep) 
    values ('645.579.440-00','Leonardo Oliveira', '21525339999', 'Rua do Músico', '566', null, 'Segismundo Pereira', 'Uberlândia', 'MG', '92353-990');
INSERT INTO pessoa (cpf, nome, telefone, logradouro, numero, complemento, bairro, cidade, estado, cep)
    values ('560.288.880-28', 'Isabela Martins', '21525310000','Rua da Terra', '1233', 'Apto 10', 'Vigilato', 'Manaus', 'AM', '12324999');
