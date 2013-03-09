insert into agentecarga(id, login, nome, senha) values (1, 'ana', 'Ana', 'ana');
insert into agentecarga(id, login, nome, senha) values (2, 'beto', 'Beto', 'beto');

insert into agentelogistica(id,login, nome, senha) values (3, 'carla', 'Carla', 'carla');
insert into agentelogistica(id,login, nome, senha) values (4, 'daniel', 'Daniel', 'daniel');

insert into porto(localizacao, nome) values ('RJ', 'Rio');
insert into porto(localizacao, nome) values ('SP', 'Sao Paulo');
insert into porto(localizacao, nome) values ('BA', 'Bahia');
insert into porto(localizacao, nome) values ('SV', 'Salvador');
insert into porto(localizacao, nome) values ('ES', 'Espirito Santo');

insert into agenteporto(id, login, nome, senha, pertence_id) values (5,'eduardo','Eduardo','eduardo', 1);
insert into agenteporto(id, login, nome, senha, pertence_id) values (6,'felipe','Felipe','felipe', 2);

insert into agenterota(id, login, nome, senha) values (7,'gustavo','Gustavo','gustavo');
insert into agenterota(id, login, nome, senha) values (8,'henrique','Henrique','henrique');

insert into contratante(id, login, nome, senha) values (9, 'igor', 'Igor', 'igor');
insert into contratante(id, login, nome, senha) values (10, 'joao', 'João', 'joao');

insert into patio () values ();
insert into patio () values ();
insert into patio () values ();
insert into patio () values ();
insert into patio () values ();

insert empresatransporte(nome) values ('ABCTrans');
insert empresatransporte(nome) values ('XYZTrans');

insert into represemptrans(id, login, nome, senha, representa_id) values (11, 'lucia', 'Lúcia', 'lucia', 1);
insert into represemptrans(id, login, nome, senha, representa_id) values (12, 'maria', 'Maria', 'maria', 2);

insert into navio(nome) values ('Australis');
insert into navio(nome) values ('Borealis');

insert into contrato(data_criacao, data_estimada, situacao, portoDestino_id, portoOrigem_id, contratante_id) values ('2013-03-05 10:00:00', '2013-03-20 16:00:00', 'aberto', 2, 1, 9);
insert into contrato(data_criacao, data_estimada, situacao, portoDestino_id, portoOrigem_id, contratante_id) values ('2013-03-06 11:00:00', '2013-03-22 17:00:00', 'finalizado', 2, 1, 9);
insert into contrato(data_criacao, data_estimada, situacao, portoDestino_id, portoOrigem_id, contratante_id) values ('2013-03-06 15:00:00', '2013-03-20 16:00:00', 'aberto', 2, 1, 10);

insert into carga(id, peso, patio_id, conteiner_id, agenteEmbarque_id, agenteDesembarque_id, contrato_id) values (1, 100, 1, NULL, 1, 2, 1);
insert into carga(id, peso, patio_id, conteiner_id, agenteEmbarque_id, agenteDesembarque_id, contrato_id) values (2, 200, 2, NULL, 1, 2, 2);

insert into carga_perecivel(id, peso, patio_id, conteiner_id, agenteEmbarque_id, agenteDesembarque_id, contrato_id, data_validade) values (3, 200, 2, NULL, 2, 1, 3, '2013-03-28 00:00:00');

insert into pregao(abertoPor_id, anuncia_id) values (3, 1);
insert into pregao(abertoPor_id, anuncia_id) values (4, 2);

insert into conteiner() values ();
insert into conteiner() values ();
insert into conteiner() values ();

