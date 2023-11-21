create database bank;
show databases;
use bank;
create table contas(
	id int primary key auto_increment,
    nome varchar(50) not null,
    email varchar(50),
    cpf varchar(50) not null,
    senha varchar(50) not null,
    telefone varchar(15),
    adm BOOLEAN NOT NULL
);
show tables;
drop table contas;
describe contas;

/* CRUD CREATE */
insert into contas (nome, email, cpf, senha, telefone, adm) values ('Adriano','adriano@adm.com','adminAdriano', 'admin', '', '1' );
insert into contas (nome, email, cpf, senha, telefone, adm) values ('Isis','isis@adm.com','adminIsis', 'admin', '', '1' );
insert into contas (nome, email, cpf, senha, telefone, adm) values ('Samuel','samuel@adm.com','adminSamuel', 'admin', '', '1' );

/* CRUD READ */
select * from contas;
select * from contas order by id;
select * from contas where idcon = 9;


/* CRUD UPDATE */
update contas set nome='Super ADM' where idcon=1;
update contas set email='admin@live.com' where idcon=1;

/* CRUD DELETE */
delete from contas where idcon=1;
