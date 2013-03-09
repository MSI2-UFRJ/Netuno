create table agenteCarga (id integer not null, primary key (id));
create table agenteLogistica (id integer not null, primary key (id));
create table agentePorto (id integer not null, pertence_id integer, primary key (id));
create table agenteRota (id integer not null, primary key (id));
create table carga (id integer not null, contrato_id integer, primary key (id));
create table carga_componente (id integer not null auto_increment, peso double precision, agenteDesembarque_id integer, agenteEmbarque_id integer, conteiner_id integer, patio_id integer, primary key (id));
create table carga_perecivel (data_validade datetime, id integer not null, primary key (id));
create table cargalog (id integer not null auto_increment, data datetime, descricao varchar(255), carga_id integer, primary key (id));
create table conteiner (id integer not null auto_increment, primary key (id));
create table contratante (id integer not null, primary key (id));
create table contrato (id integer not null auto_increment, data_criacao datetime, data_estimada datetime, situacao varchar(255), portoDestino_id integer, portoOrigem_id integer, contratante_id integer, primary key (id));
create table empresatransporte (id integer not null auto_increment, nome varchar(255), primary key (id));
create table navio (id integer not null auto_increment, nome varchar(255), primary key (id));
create table parte_carga (id integer not null, carga_id integer, primary key (id));
create table patio (id integer not null auto_increment, primary key (id));
create table porto (id integer not null auto_increment, localizacao varchar(255) not null, nome varchar(255) not null, primary key (id), unique (localizacao, nome));
create table pregao (id integer not null auto_increment, abertoPor_id integer, anuncia_id integer, primary key (id));
create table represemptrans (id integer not null, representa_id integer, primary key (id));
create table requisicao (id integer not null auto_increment, data datetime, agenteCarga_id integer, conteiner_id integer, navio_id integer, agenteRota_id integer, primary key (id));
create table usuario (id integer not null auto_increment, login varchar(255), nome varchar(255), senha varchar(255), primary key (id));
alter table agenteCarga add index FK7E7F998EEE599A3A (id), add constraint FK7E7F998EEE599A3A foreign key (id) references usuario (id);
alter table agenteLogistica add index FK3E4D9BE1EE599A3A (id), add constraint FK3E4D9BE1EE599A3A foreign key (id) references usuario (id);
alter table agentePorto add index FK7F3D29EEEE599A3A (id), add constraint FK7F3D29EEEE599A3A foreign key (id) references usuario (id);
alter table agentePorto add index FK7F3D29EE5DE95A77 (pertence_id), add constraint FK7F3D29EE5DE95A77 foreign key (pertence_id) references porto (id);
alter table agenteRota add index FKB9C9138AEE599A3A (id), add constraint FKB9C9138AEE599A3A foreign key (id) references usuario (id);
alter table carga add index FK5A0E7AE578C2B62 (id), add constraint FK5A0E7AE578C2B62 foreign key (id) references carga_componente (id);
alter table carga add index FK5A0E7AE32DFF069 (contrato_id), add constraint FK5A0E7AE32DFF069 foreign key (contrato_id) references contrato (id);
alter table carga_componente add index FKE389F8397E6B894B (conteiner_id), add constraint FKE389F8397E6B894B foreign key (conteiner_id) references conteiner (id);
alter table carga_componente add index FKE389F8398F485731 (agenteDesembarque_id), add constraint FKE389F8398F485731 foreign key (agenteDesembarque_id) references agenteCarga (id);
alter table carga_componente add index FKE389F83969DA4B4B (patio_id), add constraint FKE389F83969DA4B4B foreign key (patio_id) references patio (id);
alter table carga_componente add index FKE389F83951723E83 (agenteEmbarque_id), add constraint FKE389F83951723E83 foreign key (agenteEmbarque_id) references agenteCarga (id);
alter table carga_perecivel add index FKCFEA583E1F7556FA (id), add constraint FKCFEA583E1F7556FA foreign key (id) references carga (id);
alter table cargalog add index FKFFB274D61F278D2B (carga_id), add constraint FKFFB274D61F278D2B foreign key (carga_id) references carga (id);
alter table contratante add index FK82561591EE599A3A (id), add constraint FK82561591EE599A3A foreign key (id) references usuario (id);
alter table contrato add index FKDE35131CE8F5EF7F (portoDestino_id), add constraint FKDE35131CE8F5EF7F foreign key (portoDestino_id) references porto (id);
alter table contrato add index FKDE35131C8FA3758B (contratante_id), add constraint FKDE35131C8FA3758B foreign key (contratante_id) references contratante (id);
alter table contrato add index FKDE35131C20856862 (portoOrigem_id), add constraint FKDE35131C20856862 foreign key (portoOrigem_id) references porto (id);
alter table parte_carga add index FK52F68001578C2B62 (id), add constraint FK52F68001578C2B62 foreign key (id) references carga_componente (id);
alter table parte_carga add index FK52F680011F278D2B (carga_id), add constraint FK52F680011F278D2B foreign key (carga_id) references carga (id);
alter table pregao add index FKC594B55243F11C22 (abertoPor_id), add constraint FKC594B55243F11C22 foreign key (abertoPor_id) references agenteLogistica (id);
alter table pregao add index FKC594B552486CC404 (anuncia_id), add constraint FKC594B552486CC404 foreign key (anuncia_id) references carga (id);
alter table represemptrans add index FK9D5172C3EE599A3A (id), add constraint FK9D5172C3EE599A3A foreign key (id) references usuario (id);
alter table represemptrans add index FK9D5172C38E4FA557 (representa_id), add constraint FK9D5172C38E4FA557 foreign key (representa_id) references empresatransporte (id);
alter table requisicao add index FK904B149EB29344B (agenteCarga_id), add constraint FK904B149EB29344B foreign key (agenteCarga_id) references agenteCarga (id);
alter table requisicao add index FK904B1497E6B894B (conteiner_id), add constraint FK904B1497E6B894B foreign key (conteiner_id) references conteiner (id);
alter table requisicao add index FK904B1499D657B4B (navio_id), add constraint FK904B1499D657B4B foreign key (navio_id) references navio (id);
alter table requisicao add index FK904B149DE410A09 (agenteRota_id), add constraint FK904B149DE410A09 foreign key (agenteRota_id) references agenteRota (id);
