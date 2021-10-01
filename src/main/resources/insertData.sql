INSERT INTO AUTOR (NOME)
 VALUES 
('José Saramago'),
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

INSERT INTO LIVRO (TITULO, ISBN, AUTOR_ID, EDITORA_ID)
 VALUES
('Terra do Pecado', '12345678', 1, 1),
('Perto do coração selvagem', '12345999', 2, 1),
('O corvo', '12345644', 3, 1),
('O idiota', '1234566', 4, 1),
('irmãos Karamazov', '123454158', 1, 2),
('Hamlet ', '123455158', 5, 3);