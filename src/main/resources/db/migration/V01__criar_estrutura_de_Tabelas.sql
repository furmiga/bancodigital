CREATE TABLE cliente(
    CLIENTE_ID INTEGER NOT NULL AUTO_INCREMENT,
    NOME VARCHAR(150) NOT NULL,
    CPF VARCHAR(14) NOT NULL,
    EMAIL VARCHAR(30),
    ENDERECO VARCHAR(300) NOT NULL,
    CONSTRAINT PK_CLIENTE PRIMARY KEY(CLIENTE_ID)
);

CREATE TABLE conta(
    NUMERO_CONTA INTEGER NOT NULL,
    AGENCIA INTEGER NOT NULL,
    SENHA VARCHAR(6) NOT NULL,
    FK_CLIENTE_ID INTEGER NOT NULL,
    CONSTRAINT PK_CONTA PRIMARY KEY(NUMERO_CONTA));

CREATE TABLE produto_financeiro(
    PRODUTO_FINANCEIRO_ID INTEGER NOT NULL AUTO_INCREMENT,
    VALOR FLOAT,
    FK_NUMERO_CONTA INTEGER NOT NULL,
    FK_TIPO_PRODUTO_FINANCEIRO_ID INTEGER NOT NULL,
    CONSTRAINT PK_PRODUTO_FINANCEIERO PRIMARY KEY(PRODUTO_FINANCEIRO_ID)
);

CREATE TABLE tipo_produto_financeiro(
    TIPO_PRODUTO_FINANCEIRO_ID INTEGER NOT NULL AUTO_INCREMENT,
    DESCRICAO VARCHAR(20) NOT NULL,
    CONSTRAINT PK_TIPO_PRODUTO_FINANCEIRO PRIMARY KEY (TIPO_PRODUTO_FINANCEIRO_ID)
);

CREATE TABLE grupo (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  descricao varchar(255) DEFAULT NULL,
  nome varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE grupo_permissoes (
  grupos_id bigint(20) NOT NULL,
  permissoes_id bigint(20) NOT NULL,
  KEY FKayjy5tklv5pbug1fgcglrus6u (permissoes_id),
  KEY FKkbqtol0w45wis27ea6w8l2s73 (grupos_id)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE permissao (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE usuario (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  ativo bit(1) NOT NULL,
  login varchar(255) DEFAULT NULL,
  nome varchar(255) DEFAULT NULL,
  senha varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE usuario_grupos (
  usuarios_id bigint(20) NOT NULL,
  grupos_id bigint(20) NOT NULL,
  KEY FK92ff1v8fkwig9tqv9bk4nvi0t (grupos_id),
  KEY FKqnin6ok8ub4nmxkdm16v511a8 (usuarios_id)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE usuario_permissoes (
  usuarios_id bigint(20) NOT NULL,
  permissoes_id bigint(20) NOT NULL,
  KEY FKl7h1gyjrr1bnpqqx2wy55gisd (permissoes_id),
  KEY FKcx57iekp2ckyj80y8gyu71go5 (usuarios_id)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


ALTER TABLE conta ADD CONSTRAINT FK_CONTA_CLIENTE FOREIGN KEY (FK_CLIENTE_ID) REFERENCES cliente(CLIENTE_ID);


ALTER TABLE produto_financeiro ADD CONSTRAINT FK_PRODUTO_FINANCEIRO_TIPO_PRODUTO_FINANCEIRO FOREIGN KEY (FK_TIPO_PRODUTO_FINANCEIRO_ID) REFERENCES tipo_produto_financeiro (TIPO_PRODUTO_FINANCEIRO_ID);

ALTER TABLE produto_financeiro ADD CONSTRAINT FK_PRODUTO_FINANCEIRO_CONTA FOREIGN KEY (FK_NUMERO_CONTA) REFERENCES conta(NUMERO_CONTA);

insert into usuario (id, nome, login, senha, ativo) values (1, 'Victor', 'victor', '$2a$10$sRGVgmqnO5983xMeAmU1q.yd0R39YOUiX7kzmcF/94gCTPeYNF74G', true);

insert into grupo (id, nome, descricao) values (1, 'DEV', 'Grupo de dev');

insert into permissao (id, nome) values (1, 'USUARIO');

insert into usuario_grupos (usuarios_id, grupos_id) values (1, 1);

insert into grupo_permissoes (grupos_id, permissoes_id) values (1, 1);
