-- SELECT 'CREATE DATABASE tymed
--     WITH
--     OWNER = postgres
--     ENCODING = 'UTF8'
--     CONNECTION LIMIT = -1
--     IS_TEMPLATE = False' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'tymed')
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');


--Dev
--docker run -d -p 5445:5432 --name=esporte -e POSTGRES_PASSWORD=0000 postgres

--Prod
--docker network create --driver bridge esporte_ids
--docker run -d -p 5445:5432 --name=esporte -e POSTGRES_PASSWORD=0000 -e POSTGRES_DB=esporte --network=esporte_ids  postgres

--Criar o jar
--./mvnw package
--Limpar o maven antes de rodar as migrations
-- .\mvnw clean

-- insert into endereco (logradouro, numero, bairro, municipio, cep, estado, pais) values ('Green Ridge', '427', 'Texas', 'Conroe', 38000723, 'Alabama', 'Brasil');
-- insert into endereco (logradouro, numero, bairro, municipio, cep, estado, pais) values ('Talmadge', '575', 'Texas', 'El Paso', 76107707, 'Alabama', 'Brasil');
-- insert into endereco (logradouro, numero, bairro, municipio, cep, estado, pais) values ('Weeping Birch', '43259', 'Texas', 'Dallas', 45653973, 'Alabama', 'Brasil');
-- insert into endereco (logradouro, numero, bairro, municipio, cep, estado, pais) values ('Rockefeller', '40', 'Texas', 'Houston', 45311750, 'Alabama', 'Brasil');
-- insert into endereco (logradouro, numero, bairro, municipio, cep, estado, pais) values ('Veith', '2', 'Texas', 'Corpus Christi', 66720954, 'Alabama', 'Brasil');
-- insert into endereco (logradouro, numero, bairro, municipio, cep, estado, pais) values ('Bayside', '151', 'Texas', 'Dallas', 59066887, 'Alabama', 'Brasil');
-- insert into endereco (logradouro, numero, bairro, municipio, cep, estado, pais) values ('Lukken', '7495', 'Texas', 'Conroe', 94089487, 'Alabama', 'Brasil');
-- insert into endereco (logradouro, numero, bairro, municipio, cep, estado, pais) values ('Porter', '4897', 'Texas', 'Mesquite', 31028490, 'Alabama', 'Brasil');
-- insert into endereco (logradouro, numero, bairro, municipio, cep, estado, pais) values ('Pine View', '5541', 'Texas', 'Arlington', 44561746, 'Alabama', 'Brasil');
-- insert into endereco (logradouro, numero, bairro, municipio, cep, estado, pais) values ('6th', '34', 'Texas', 'Irving', 66940646, 'Alabama', 'Brasil');
-- insert into endereco (logradouro, numero, bairro, municipio, cep, estado, pais) values ('Chinook', '7435', 'Texas', 'Austin', 80159200, 'Alabama', 'Brasil');
-- insert into endereco (logradouro, numero, bairro, municipio, cep, estado, pais) values ('Main', '7680', 'Texas', 'Lubbock', 35289329, 'Alabama', 'Brasil');
-- insert into endereco (logradouro, numero, bairro, municipio, cep, estado, pais) values ('Sugar', '3085', 'Texas', 'Austin', 15900411, 'Alabama', 'Brasil');
-- insert into endereco (logradouro, numero, bairro, municipio, cep, estado, pais) values ('Menomonie', '196', 'Texas', 'Corpus Christi', 72252879, 'Alabama', 'Brasil');
-- insert into endereco (logradouro, numero, bairro, municipio, cep, estado, pais) values ('Canary', '46', 'Texas', 'Austin', 80632654, 'Alabama', 'Brasil');

-- insert into residente (nome, idade, cpf, telefone, email, situacao, tipoestadia, datahoraingresso, datahoraprevisaosaida,idendereco) values ('Sheffield', 100, 11696269040, 85936243238, 'sbee0@whitehouse.gov', 'ATIVO', 'SENIOR', '2022-03-17T07:26:45-03:00', '2022-07-09T03:28:15-03:00',1);
-- insert into residente (nome, idade, cpf, telefone, email, situacao, tipoestadia, datahoraingresso, datahoraprevisaosaida,idendereco) values ('Augustine', 4, 06911219078, 95189116925, 'ahedworth1@boston.com', 'INATIVO', 'SENIOR', '2022-08-03T18:11:17-03:00', '2022-07-12T20:42:01-03:00',2);
-- insert into residente (nome, idade, cpf, telefone, email, situacao, tipoestadia, datahoraingresso, datahoraprevisaosaida,idendereco) values ('Darn', 17, 54048538080, 85419725002, 'dleverage2@nature.com', 'ATIVO', 'PADRAO', '2022-09-19T09:04:24-03:00', '2023-01-11T00:38:58-03:00',3);
-- insert into residente (nome, idade, cpf, telefone, email, situacao, tipoestadia, datahoraingresso, datahoraprevisaosaida,idendereco) values ('Igor', 9, 30239966015, 85419725002, 'iwagenen3@sina.com.cn', 'ATIVO', 'SENIOR', '2022-05-04T19:46:47-03:00', '2022-09-26T05:01:57-03:00',4);
-- insert into residente (nome, idade, cpf, telefone, email, situacao, tipoestadia, datahoraingresso, datahoraprevisaosaida,idendereco) values ('Gayelord', 127, 34093184020, 88813237981, 'gianinotti4@parallels.com', 'INATIVO', 'SENIOR', '2022-10-08T15:14:26-03:00', '2022-11-21T09:32:20-03:00',5);
-- insert into residente (nome, idade, cpf, telefone, email, situacao, tipoestadia, datahoraingresso, datahoraprevisaosaida,idendereco) values ('Brenna', 131, 74966545072, 97159497514, 'bfurmonger5@quantcast.com', 'INATIVO', 'PADRAO', '2022-11-06T20:36:22-03:00', '2022-06-05T13:27:56-03:00',6);
-- insert into residente (nome, idade, cpf, telefone, email, situacao, tipoestadia, datahoraingresso, datahoraprevisaosaida,idendereco) values ('Harvey', 54, 22089978082, 89200054322, 'hpowderham6@weibo.com', 'INATIVO', 'SENIOR', '2022-03-22T07:49:32-03:00', '2022-03-29T03:43:27-03:00',7);
-- insert into residente (nome, idade, cpf, telefone, email, situacao, tipoestadia, datahoraingresso, datahoraprevisaosaida,idendereco) values ('Darn', 52, 85419725002, 86522885868, 'dguitel7@furl.net', 'ATIVO', 'PADRAO', '2022-06-22T01:56:13-03:00', '2022-08-11T17:17:19-03:00',8);
-- insert into residente (nome, idade, cpf, telefone, email, situacao, tipoestadia, datahoraingresso, datahoraprevisaosaida,idendereco) values ('Kinsley', 41, 97969671039, 88585863509, 'kbraxton8@ocn.ne.jp', 'ATIVO', 'PADRAO', '2022-04-03T08:52:50-03:00', '2022-08-17T04:16:25-03:00',9);
-- insert into residente (nome, idade, cpf, telefone, email, situacao, tipoestadia, datahoraingresso, datahoraprevisaosaida,idendereco) values ('Darrel', 21, 27574933022, 90085013502, 'dblack9@ebay.co.uk', 'INATIVO', 'SENIOR', '2022-03-04T14:33:41-03:00', '2022-10-31T03:54:51-03:00',10);
--
-- insert into profissional (nome, idade, cpf, telefone, email, dataadmissao, salario, situacao, funcao,idendereco) values ('Si', 74, 53593565021, 99471724462, 'sdannel0@irs.gov', '2022-09-18T00:08:57-03:00', 1263.23, 'ATIVO', 'FUNCIONARIO',11);
-- insert into profissional (nome, idade, cpf, telefone, email, dataadmissao, salario, situacao, funcao,idendereco) values ('Thane', 23, 71257335065, 87702759198, 'tsincock2@bloglovin.com', '2022-04-21T05:46:21-03:00', 0, 'ATIVO', 'VOLUNTARIO',12);
-- insert into profissional (nome, idade, cpf, telefone, email, dataadmissao, salario, situacao, funcao,idendereco) values ('Fair', 114, 78241831064, 90239488948, 'fpiele3@nifty.com', '2022-10-01T17:16:44-03:00', 2291.03, 'ATIVO', 'ENFERMEIRO',13);
-- insert into profissional (nome, idade, cpf, telefone, email, dataadmissao, salario, situacao, funcao,idendereco) values ('Pieter', 115, 76493969085, 95357166992, 'pfarey6@salon.com', '2022-03-03T14:54:11-03:00', 5362.83, 'ATIVO', 'MEDICO',14);
-- insert into profissional (nome, idade, cpf, telefone, email, dataadmissao, salario, situacao, funcao,idendereco) values ('Burnard', 58, 07908261000, 84825155904, 'bskeech8@google.nl', '2022-08-20T17:32:10-03:00', 1521.41, 'ATIVO', 'CUIDADOR',15);
