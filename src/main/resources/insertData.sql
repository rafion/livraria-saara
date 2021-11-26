INSERT INTO AUTOR (NOME)
 VALUES 
('Jose Saramago'),
('Clarice Lispector'),
('Edgar Allan Poe'),
('Fiódor Dostoiévski'),
('William Shakespeare'),
('Marcel Proust'),
('Miguel de Cervantes'),
('Gabriel García Márquez'),
('Franz Kafka'),
('Jorge Luis Borges');


INSERT INTO EDITORA (NOME)
 VALUES
('Pearson'),
('RELX Group'),
('ThomsonReuters'),
('Bertelsmann'),
('Wolters Kluwer'),
('Hachette Livre'),
('Grupo Planeta'),
('Springer Nature'),
('Scholastic'),
('McGraw-Hill Education');

INSERT INTO LIVRO (TITULO, ISBN, AUTOR_ID, EDITORA_ID, PRECO_UNITARIO, DISPONIVEL)
 VALUES
('Terra do Pecado', '12345678', 1, 1, 49.90, TRUE),
('Perto do coração selvagem', '12345999', 2, 1, 59.90, TRUE),
('O corvo', '12345644', 3, 1, 29.90, TRUE),
('O idiota', '1234566', 4, 1, 199.90, TRUE),
('irmãos Karamazov', '123454158', 1, 2, 99.90, TRUE),
('Hamlet ', '123455158', 5, 3, 79.90, TRUE);

INSERT INTO MEIO_PAGAMENTO (NOME)
VALUES
('DINHEIRO'),
('CARTAO DE CREDITO'),
('CARTAO DE DEBITO'),
('BOLETO BANCARIO'),
('PIX');

--insere usuarios
insert into USUARIO (id,TIPO, ACESSO_ESPECIAL, nome, username, email, password) values
(1,'ADM', TRUE, 'admin','admin', 'admin@gmail.com', '$2a$10$CwYn1En7B.1bXVi98DpXZOxp7mIqnYyBslMct3ZFVtCtVwW9awKOa');

INSERT INTO permissao (id, descricao) values (1, 'ROLE_ADMIN');
INSERT INTO permissao (id, descricao) values (2, 'ROLE_USER');


--ADMIN PODE TUDO
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (1, 1);




