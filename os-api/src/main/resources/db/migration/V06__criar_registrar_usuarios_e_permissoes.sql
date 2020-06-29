CREATE TABLE usuario (

	id BIGINT(20) PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (

	id BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao(
    
    id_usuario BIGINT(20) NOT NULL,
    id_permissao BIGINT(20) NOT NULL,
    PRIMARY KEY (id_usuario, id_permissao),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_permissao) REFERENCES permissao(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Inserção usuario
INSERT INTO usuario (id, nome, email, senha) VALUES (1, 'Administrador', 'felipeferreira_811@hotmail.com', '$2a$10$qcJ9j9TftEI42cZwIidZwOSzKTy/2lGxcWy5KN3smBF/NCz/N018m'); 
INSERT INTO usuario (id, nome, email, senha) VALUES (2, 'Maria Silva', 'maria777@hotmail.com', '$2a$10$v996.SuHkEGWIUBGm8OCfOCDhkD6hyfAheWybi2vzc534jg3ypaG.'); 

-- Inserção permissao
INSERT INTO permissao (id, descricao) VALUES (1, 'ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissao (id, descricao) VALUES (2, 'ROLE_PESQUISAR_CATEGORIA');

INSERT INTO permissao (id, descricao) VALUES (3, 'ROLE_PESQUISAR_PESSOA');
INSERT INTO permissao (id, descricao) VALUES (4, 'ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao (id, descricao) VALUES (5, 'ROLE_REMOVER_PESSOA');

INSERT INTO permissao (id, descricao) VALUES (6, 'ROLE_PESQUISAR_OS');
INSERT INTO permissao (id, descricao) VALUES (7, 'ROLE_CADASTRAR_OS');
INSERT INTO permissao (id, descricao) VALUES (8, 'ROLE_REMOVER_OS');


-- Inserção usuario_permissao para admin
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES (1, 1);
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES (1, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES (1, 3);
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES (1, 4);
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES (1, 5);
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES (1, 6);
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES (1, 7);
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES (1, 8);

-- Inserção usuario_permissao para maria
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES (2, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES (2, 3);
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES (2, 6);
