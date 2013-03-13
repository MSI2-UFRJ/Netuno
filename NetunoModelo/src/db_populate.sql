use netuno;

insert into porto(localizacao, nome) values ('RJ', 'Rio');
insert into porto(localizacao, nome) values ('SP', 'Sao Paulo');
insert into porto(localizacao, nome) values ('BA', 'Bahia');
insert into porto(localizacao, nome) values ('SV', 'Salvador');
insert into porto(localizacao, nome) values ('ES', 'Espirito Santo');

-- AGENTE CARGA
INSERT INTO `netuno`.`usuario` VALUES(1,'12345678910','carga1','carga1','carga1');
INSERT INTO netuno.agentecarga VALUES(1,1);

INSERT INTO `netuno`.`usuario` VALUES(2,'12345678911','carga2','carga2','carga2');
INSERT INTO netuno.agentecarga VALUES(2,2);

INSERT INTO `netuno`.`usuario` VALUES(11,'12345678912','cargaD1','cargaD1','cargaD1');
INSERT INTO netuno.agentecarga VALUES(11,1);
-- AGENTE LOGISTICA
INSERT INTO `netuno`.`usuario` VALUES(3,'12345678914','logistica1','logistica1','logistica1');
INSERT INTO netuno.agentelogistica VALUES(3);

INSERT INTO `netuno`.`usuario` VALUES(4,'12345678915','logistica2','logistica2','logistica2');
INSERT INTO netuno.agentelogistica VALUES(4);
-- AGENTE PORTO
INSERT INTO `netuno`.`usuario` VALUES(5,'12345678916','porto1','porto1','porto1');
INSERT INTO netuno.agenteporto VALUES(5,1);

INSERT INTO `netuno`.`usuario` VALUES(6,'12345678917','porto2','porto2','porto2');
INSERT INTO netuno.agenteporto VALUES(6,2);
-- AGENTE ROTA
INSERT INTO `netuno`.`usuario` VALUES(7,'12345678918','rota1','rota1','rota1');
INSERT INTO netuno.agenterota VALUES(7);

INSERT INTO `netuno`.`usuario` VALUES(8,'12345678919','rota2','rota2','rota2');
INSERT INTO netuno.agenterota VALUES(8);
-- CONTRATANTE 
INSERT INTO `netuno`.`usuario` VALUES(9,'12345678920','c','contratante1','c');
INSERT INTO netuno.contratante VALUES(9);

INSERT INTO `netuno`.`usuario` VALUES(10,'12345678921','contratante2','contratante2','contratante2');
INSERT INTO netuno.contratante VALUES(10);
-- ATENDENTE 
INSERT INTO `netuno`.`usuario` VALUES(12,'12345678922','a','Atendente','a');
INSERT INTO netuno.atendente VALUES(12);
-- REPRESENTANTE -- 
-- INSERT INTO `netuno`.`usuario` VALUES(11,'representante1','representante1','representante1');
-- INSERT INTO netuno.representante VALUES(11);
-- 
-- INSERT INTO `netuno`.`usuario` VALUES(12,'representante2','representante2','representante2');
-- INSERT INTO netuno.representante VALUES(12);



-- PATIOS
insert into patio () values ();
insert into patio () values ();
insert into patio () values ();
insert into patio () values ();
insert into patio () values ();

-- EMPRESAS
insert empresatransporte(nome) values ('ABCTrans');
insert empresatransporte(nome) values ('XYZTrans');

-- NAVIOS
insert into navio(nome) values ('Australis');
insert into navio(nome) values ('Borealis');

-- CONTRATOS
insert into contrato(data_criacao, data_estimada, situacao, portoDestino_id, portoOrigem_id, contratante_id, forma_pagamento, preco) values ('2013-03-05 10:00:00', '2013-03-20 16:00:00', 1, 2, 1, 9, 0, 10000.50);
insert into contrato(data_criacao, data_estimada, situacao, portoDestino_id, portoOrigem_id, contratante_id, forma_pagamento, preco) values ('2013-03-06 11:00:00', '2013-03-22 17:00:00', 3, 2, 1, 9, 1, 100000.0);
insert into contrato(data_criacao, data_estimada, situacao, portoDestino_id, portoOrigem_id, contratante_id, forma_pagamento, preco) values ('2013-03-06 15:00:00', '2013-03-20 16:00:00', 1, 2, 1, 10, 0, 0.50);


-- CARGAS
	insert into netuno.carga_componente values (1,100, 1, NULL, NULL, 1);
insert into netuno.carga values(0,'a',1,1);

insert into netuno.carga_componente values (2, 550, 1, NULL, NULL, 1);
insert into netuno.carga values(0,'b',2,1);

insert into netuno.carga_componente values (3,1000, 1, NULL, NULL, 1);
insert into netuno.carga values(0,'c',3,1);

insert into netuno.carga_componente values (4,5000, 2, NULL, NULL, 2);
insert into netuno.carga values(0,'d',4,2);

-- PERECIVEL
insert into netuno.carga_componente values (5, 10000, 2, NULL, NULL, 2);
insert into netuno.carga values(0,'e',5,3);
insert into carga_perecivel values('2013-04-28 00:00:00',5);

insert into pregao(abertoPor_id, anuncia_id) values (3, 1);
insert into pregao(abertoPor_id, anuncia_id) values (4, 2);

insert into conteiner values(1,20000,1);
insert into conteiner values(2,20000,1);
insert into conteiner values(3,20000,2);