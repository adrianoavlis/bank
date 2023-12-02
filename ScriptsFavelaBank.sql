create database bank;
show databases;
use bank;

DROP TABLE IF EXISTS contas;
CREATE TABLE IF NOT EXISTS contas (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  email varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  cpf varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  telefone varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  saldo double,
  adm BOOLEAN NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS transacoes;
CREATE TABLE IF NOT EXISTS transacoes (
	id int NOT NULL AUTO_INCREMENT,
	tipo varchar(50) not null,
	valor double,
	data date DEFAULT NULL,
	saldo double,
	idusuario int NOT NULL,
	PRIMARY KEY (id),
    KEY FK_usuario (idusuario)
) ENGINE=InnoDB AUTO_INCREMENT=277 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE transacoes
  ADD CONSTRAINT FK_usuario FOREIGN KEY (idusuario) REFERENCES contas (id);
COMMIT;

DROP TABLE IF EXISTS investimentos;
CREATE TABLE IF NOT EXISTS investimentos (
	id int NOT NULL AUTO_INCREMENT,
    idusuario int NOT NULL,
    idtransacao int NOT NULL,
	ticker varchar(50) not null,
	nome varchar(50) not null,
	setor varchar(50) not null,
    valor_atual double,
	volume_negociado int,
	PRIMARY KEY (id),
    KEY FK_user (idusuario),
    KEY FK_transacao (idtransacao)
) ENGINE=InnoDB AUTO_INCREMENT=277 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE investimentos
	ADD CONSTRAINT FK_user FOREIGN KEY (idusuario) REFERENCES contas (id),
	ADD CONSTRAINT FK_transacao FOREIGN KEY (idtransacao) REFERENCES transacoes (id);
COMMIT;

DROP TABLE IF EXISTS ativos ;
CREATE TABLE IF NOT EXISTS ativos (
	id int NOT NULL AUTO_INCREMENT,
	ticker varchar(50) not null,
	nome varchar(50) not null,
	setor varchar(50) not null,
    valor_atual double,
	volume_negociado int,
	idinvestimento int NOT NULL,
	PRIMARY KEY (id),
    KEY FK_investimento (idinvestimento)
) ENGINE=InnoDB AUTO_INCREMENT=277 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE ativos
  ADD CONSTRAINT FK_investimento FOREIGN KEY (idinvestimento) REFERENCES investimentos (id);
COMMIT;

show tables;
drop table transacoes;
describe ativos;

/* CRUD CREATE */
insert into contas (nome, email, cpf, senha, telefone, adm) values ('Adriano','adriano@adm.com','adminAdriano', 'admin', '', '1' );
insert into contas (nome, email, cpf, senha, telefone, adm) values ('Isis','isis@adm.com','adminIsis', 'admin', '', '1' );
insert into contas (nome, email, cpf, senha, telefone, adm) values ('Samuel','samuel@adm.com','adminSamuel', 'admin', '', '1' );

/* CRUD READ */
select * from contas;
select * from contas order by id;
select * from contas where id = 9;


/* CRUD UPDATE */
update contas set cpf='adminIris' where id=2;
update contas set email='admin@live.com' where id=1;

/* CRUD DELETE */
delete from contas where id=1;
