insert into organization (description) VALUES ('SECRETARIA DE FINANÇAS'), ('SECRETARIA DE CONSERVAÇÃO E SERVIÇOS PÚBLICOS'),
('SECRETARIA DE PLANEJAMENTO, ORÇAMENTO E GESTÃO'), ('INSTITUTO JOSÉ FROTA'), ('SECRETARIA EXECUTIVA REGIONAL I'),
('SECRETARIA EXECUTIVA REGIONAL II'), ('SECRETARIA EXECUTIVA REGIONAL III'), ('SECRETARIA DE EDUCAÇÃO'),
('SECRETARIA DE SAÚDE'),('HOSPITAL DA MULHER');
insert into sector (description) VALUES ('FINANCEIRO'), ('COMERCIAL'), ('MARKETING'), ('OPERACIONAL'), ('RECURSOS HUMANOS'),
('DIRETORIA'), ('ACESSORIA DE IMPRENSA');
insert into organization_sectors (organization_id, sectors_id) values (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7);
insert into goal ( description, goal_id, type, organization_id) values ( 'Test1', null, 'URGENTE', 1), ( 'Test2', 1, 'URGENTE', 1),
 ( 'Test3', null, 'TRIVIAL', 2), ( 'Test4', 3, 'IMPORTANTE', 2), ( 'Test5', 1, 'RELEVANTE', 1);