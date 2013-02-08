create table carga (id integer not null auto_increment, peso double precision, primary key (id));
create table navio (id integer not null auto_increment, nome varchar(255), primary key (id));
create table usuario (id integer not null auto_increment, login varchar(255), senha varchar(255), primary key (id));
