create database jbuscamina;

use jbuscamina;

create table puntaje(
	cod_pun int auto_increment primary key,
	nom_pun varchar(50),
	tie_pun int,
	niv_pun varchar(50),
	min_pun int,
	por_pun int,
	pun_pun int
)TYPE=InnoDB;