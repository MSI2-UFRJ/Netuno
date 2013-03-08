insert into agentecarga(login, nome, senha) values ('ana','Ana','ana');
insert into agentecarga(login, nome, senha) values ('beto','Beto','beto');

insert into agentelogistica(login, nome, senha) values ('carla','Carla','carla');
insert into agentelogistica(login, nome, senha) values ('daniel','Daniel','daniel');

insert into porto(nome) values ('RJ');
insert into porto(nome) values ('SP');
insert into porto(nome) values ('BA');
insert into porto(nome) values ('SV');
insert into porto(nome) values ('ES');

insert into agenteporto(login, nome, senha, pertence_id) values ('eduardo','Eduardo','eduardo', 1);
insert into agenteporto(login, nome, senha, pertence_id) values ('felipe','Felipe','felipe', 2);

insert into agenterota(login, nome, senha) values ('gustavo','Gustavo','gustavo');
insert into agenterota(login, nome, senha) values ('henrique','Henrique','henrique');

insert into contratante(login, nome, senha) values ('igor', 'Igor', 'igor');
insert into contratante(login, nome, senha) values ('joao', 'João', 'joao');

insert into patio () values ();
insert into patio () values ();
insert into patio () values ();
insert into patio () values ();
insert into patio () values ();

insert empresatransporte(nome) values ('ABCTrans');
insert empresatransporte(nome) values ('XYZTrans');

insert into represemptrans(login, nome, senha, representa_id) values ('lucia', 'Lúcia', 'lucia', 1);
insert into represemptrans(login, nome, senha, representa_id) values ('maria', 'Maria', 'maria', 2);

insert into navio(nome) values ('Australis');
insert into navio(nome) values ('Borealis');

insert into contrato(data_criacao, data_estimada, situacao, portoDestino_id, portoOrigem_id, contratante_id) values ('2013-03-05 10:00:00', '2013-03-20 16:00:00', 'aberto', 2, 1, 1);
insert into contrato(data_criacao, data_estimada, situacao, portoDestino_id, portoOrigem_id, contratante_id) values ('2013-03-06 11:00:00', '2013-03-22 17:00:00', 'finalizado', 2, 1, 1);
insert into contrato(data_criacao, data_estimada, situacao, portoDestino_id, portoOrigem_id, contratante_id) values ('2013-03-06 15:00:00', '2013-03-20 16:00:00', 'aberto', 2, 1, 2);

insert into carga(peso, patio_id, conteiner_id, agenteEmbarque_id, agenteDesembarque_id, contrato_id) values (100, 1, NULL, 1, 2, 1);
insert into carga(peso, patio_id, conteiner_id, agenteEmbarque_id, agenteDesembarque_id, contrato_id) values (200, 2, NULL, 1, 2, 2);

insert into carga_perecivel(peso, patio_id, conteiner_id, agenteEmbarque_id, agenteDesembarque_id, contrato_id, data_validade) values (200, 2, NULL, 2, 1, 3, '2013-03-28 00:00:00');

insert into pregao(abertoPor_id, anuncia_id) values (1, 1);
insert into pregao(abertoPor_id, anuncia_id) values (2, 2);

insert into conteiner() values ();
insert into conteiner() values ();
insert into conteiner() values ();

