create database bank;
show databases;
use bank;

DROP TABLE IF EXISTS contas;
CREATE TABLE IF NOT EXISTS contas (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  email varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  cpf varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  senha varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  telefone varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  saldo double,
  adm BOOLEAN NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE transacoes
  ADD CONSTRAINT FK_usuario FOREIGN KEY (idusuario) REFERENCES contas (id);
COMMIT;

DROP TABLE IF EXISTS ativos ;
CREATE TABLE IF NOT EXISTS ativos (
	id int NOT NULL AUTO_INCREMENT,
	ticker varchar(50) not null,
	nome varchar(50) not null,
	setor varchar(50) not null,
    valor_atual double,
	volume_negociado int,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS investimentos;
CREATE TABLE IF NOT EXISTS investimentos (
	id int NOT NULL AUTO_INCREMENT,
    idusuario int NOT NULL,
    idtransacao int NOT NULL,
    idativo int NOT NULL,
	tipo varchar(10) not null,
	quantidade int not null,
    preco double,
    redimento double,
	data date not null,
	PRIMARY KEY (id),
    KEY FK_user (idusuario),
    KEY FK_transacao (idtransacao),
    KEY FK_ativo (idativo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE investimentos
	ADD CONSTRAINT FK_user FOREIGN KEY (idusuario) REFERENCES contas (id),
	ADD CONSTRAINT FK_transacao FOREIGN KEY (idtransacao) REFERENCES transacoes (id),
    ADD CONSTRAINT FK_ativo FOREIGN KEY (idativo) REFERENCES ativos (id);
COMMIT;


show tables;
drop table contas, investimentos, transacoes;
describe contas;

/* CRUD CREATE */
insert into contas (nome, email, cpf, senha, telefone, saldo, adm) values ('Adriano','adriano@adm.com','adminAdriano', 'admin', '932487238', 210.70 , 1);
insert into contas (nome, email, cpf, senha, telefone, saldo, adm) values ('Isis','isis@adm.com','adminIsis', 'admin', '823726353', 220.07, 1 );
insert into contas (nome, email, cpf, senha, telefone, saldo, adm) values ('Samuel','samuel@adm.com','adminSamuel', 'admin', '823753532', 250.25, 1 );
/*  insert into contas (nome, email, cpf, senha, telefone, saldo, adm) values ('Adriano','adriano@adm.com','adminAdriano', 'admin', '932487238', 210.70 , 1);
/* CRUD READ */
select * from contas;
select * from contas order by id;
select * from contas where id = ?;


/* CRUD UPDATE */
update contas set cpf='adminIris' where id=2;
update contas set email='admin@live.com' where id=1;

/* CRUD DELETE */
delete from contas where id=1;


select * from contas where cpf=? and senha =? LIMIT 1"
