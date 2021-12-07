INSERT INTO AUTOR (ID, NOME)
 VALUES 
(1, 'Jose Saramago'),
(2, 'Clarice Lispector'),
(3, 'Edgar Allan Poe'),
(4, 'Fiódor Dostoiévski'),
(5, 'William Shakespeare'),
(6, 'Marcel Proust'),
(7, 'Miguel de Cervantes'),
(8, 'Gabriel García Márquez'),
(9, 'Franz Kafka'),
(10, 'Jorge Luis Borges'),
(11, 'George R. R. Martin'),
(12, 'George Orwell'),
(13, 'Thierry Murat'),
(14, 'Stephen King'),
(15, 'Philip K. Dick'),
(16, 'Neal Stephenson'),
(17, 'Kurt Vonnegut'),
(18, 'Ann Leckie'),
(19, 'James S A Corey'),
(20, 'Daniel Keyes'),
(21, 'Stanislaw Lem'),
(22, 'John Scalzi'),
(23, 'William Gibson'),
(24, 'Frank Herbert'),
(25, 'Isaac Asimov'),
(26, 'Anthony Burgess'),
(27, 'Walter M Miller Jr'),
(28, 'Arthur C. Clarke'),
(29, 'John Scalzi'),
(30, 'Michael Crichton'),
(31, 'Pierre Boulle'),
(32, 'Richard Matheson');



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
('McGraw-Hill Education'),
('Aleph');

INSERT INTO LIVRO (TITULO, ISBN, AUTOR_ID, EDITORA_ID, PRECO_UNITARIO, DISPONIVEL, URL_IMAGE)
 VALUES
('Terra do Pecado', '12', 1, 1, 300.00, TRUE, 'https://images-na.ssl-images-amazon.com/images/I/41ADIni5wrL.jpg'),
('Perto do coração selvagem', '123', 2, 1, 59.90, TRUE, 'https://m.media-amazon.com/images/P/B07ZH1RNVB.01._SCLZZZZZZZ_SX500_.jpg'),
('O corvo', '1234', 3, 1, 29.90, TRUE, 'https://images-na.ssl-images-amazon.com/images/I/515xkV5l3XL._SX331_BO1,204,203,200_.jpg'),
('O idiota', '12345', 4, 1, 199.90, TRUE, 'https://m.media-amazon.com/images/P/B08FRJKSM9.01._SCLZZZZZZZ_SX500_.jpg'),
('irmãos Karamazov', '123456', 1, 2, 99.90, TRUE, 'https://images-na.ssl-images-amazon.com/images/I/51g-IHo9qCL._SX301_BO1,204,203,200_.jpg'),
('Hamlet ', '1122', 5, 3, 79.90, TRUE, 'https://m.media-amazon.com/images/P/B00GGNTHW2.01._SCLZZZZZZZ_SX500_.jpg'),
('A Guerra dos Tronos : As Crônicas de Gelo e Fogo','2233', 11, 1 , 59.90 , TRUE, 'https://m.media-amazon.com/images/P/B07PPB9QW1.01._SCLZZZZZZZ_SX500_.jpg'),
('A fúria dos reis: As Crônicas de Gelo e Fogo','3322', 11, 1 , 49.90 , TRUE, 'https://m.media-amazon.com/images/P/B07QYPLKV1.01._SCLZZZZZZZ_SX500_.jpg'),
('A tormenta de espadas: As Crônicas de Gelo e Fogo','3344', 11, 1 , 39.90 , TRUE, 'https://m.media-amazon.com/images/P/B07QXSYDSB.01._SCLZZZZZZZ_SX500_.jpg'),
('O Festim dos Corvos: As Crônicas de Gelo e Fogo','3355', 11, 1 , 59.60 , TRUE, 'https://m.media-amazon.com/images/P/B081284MLJ.01._SCLZZZZZZZ_SX500_.jpg'),
('A dança dos dragões: As Crônicas de Gelo e Fogo','3366', 11, 1 , 48.62 , TRUE, 'https://m.media-amazon.com/images/P/B084SS25D3.01._SCLZZZZZZZ_SX500_.jpg'),
('Fogo & Sangue','3377', 11, 1 , 36.60 , TRUE, 'https://m.media-amazon.com/images/P/B07HKPLNHC.01._SCLZZZZZZZ_SX500_.jpg'),
('A revolução dos bichos: Um conto de fadas','3388', 12, 1 , 14.90 , TRUE, 'https://images-na.ssl-images-amazon.com/images/I/61owA5ey3iL._SX324_BO1,204,203,200_.jpg'),
('1984','3323', 12, 1 , 8.90 , TRUE, 'https://m.media-amazon.com/images/P/B08X1WDMQ2.01._SCLZZZZZZZ_SX500_.jpg'),
('O velho e o mar','1132', 13, 1 , 34.80 , TRUE, 'https://m.media-amazon.com/images/P/B0774WN7QF.01._SCLZZZZZZZ_SX500_.jpg'),
('O cemitério','1136', 14, 1 , 34.90 , TRUE, 'https://m.media-amazon.com/images/P/B00CEZUV26.01._SCLZZZZZZZ_SX500_.jpg'),
('It: A coisa','1131', 14, 1 , 39.90 , TRUE, 'https://m.media-amazon.com/images/P/B00MEQSA4Q.01._SCLZZZZZZZ_SX500_.jpg'),
('O iluminado','1139', 14, 1 , 59.90 , TRUE, 'https://m.media-amazon.com/images/P/B0743MBY15.01._SCLZZZZZZZ_SX500_.jpg'),
('The Stand','1137', 14, 1 , 59.90 , TRUE, 'https://m.media-amazon.com/images/P/B001C4NXKM.01._SCLZZZZZZZ_SX500_.jpg'),
('Ubik','1111', 15, 11 , 29.90 , TRUE, 'https://m.media-amazon.com/images/I/512GxL+qpfL.jpg'),
('Snow Crash','2222', 16, 11 , 29.90 , TRUE, 'https://m.media-amazon.com/images/I/51FXJ6YVt-L.jpg'),
('As sereias de Titã','3333', 17, 11 , 39.90 , TRUE, 'https://m.media-amazon.com/images/I/41y4Fxyzr7L.jpg'),
('Cama de gato','4444', 17, 11 , 39.90 , TRUE, 'https://m.media-amazon.com/images/I/418+pYdDS+L.jpg'),
('Justiça ancilar','5555', 18, 11 , 39.90 , TRUE, 'https://m.media-amazon.com/images/I/512VCELmT+L.jpg'),
('Leviatã desperta','6666', 19, 11 , 39.90 , TRUE, 'https://m.media-amazon.com/images/I/51r+D-LRExL._SX260_.jpg'),
('Flores Para Algernon','7777', 20, 11 , 29.90 , TRUE, 'https://m.media-amazon.com/images/I/51OoTRAChFL.jpg'),
('Solaris','8888', 21, 11 , 29.90 , TRUE, 'https://m.media-amazon.com/images/I/415rcj3hKLL.jpg'),
('Encarcerados','9999', 22, 11 , 29.90 , TRUE, 'https://m.media-amazon.com/images/I/51+t4NIVN+L.jpg'),
('Neuromancer','4321', 23, 11 , 29.90 , TRUE, 'https://m.media-amazon.com/images/I/61YOe9tGVPL.jpg'),
('Duna','1112', 24, 11 , 69.90 , TRUE, 'https://images-na.ssl-images-amazon.com/images/I/41MRn6hy8-L._SY344_BO1,204,203,200_QL70_ML2_.jpg'),
('Messias de Duna','1113', 24, 11 , 69.90 , TRUE, 'https://images-na.ssl-images-amazon.com/images/I/51DhRBNvM1L._SX339_BO1,204,203,200_.jpg'),
('Filhos de Duna','1114', 24, 11 , 69.90 , TRUE, 'https://images-na.ssl-images-amazon.com/images/I/51mqUvezh2L._SX348_BO1,204,203,200_.jpg'),
('O fim da eternidade','1115', 25, 11 , 29.90 , TRUE, 'https://m.media-amazon.com/images/I/61p+TILWn-L.jpg'),
('Laranja mecânica','1116', 26, 11 , 49.90 , TRUE, 'https://m.media-amazon.com/images/I/51AES0xVwHL.jpg'),
('Um cântico para Leibowitz','1117', 27, 11 , 39.90 , TRUE, 'https://m.media-amazon.com/images/I/51hf42R1XiL.jpg'),
('2001: Uma Odisseia no Espaço','1118', 28, 11 , 29.90 , TRUE, 'https://m.media-amazon.com/images/I/51rAMvZ01ZL.jpg'),
('Guerra Do Velho','1118', 29, 11 , 29.90 , TRUE, 'https://m.media-amazon.com/images/I/512AyY2uYoL._SX260_.jpg'),
('Mona Lisa Overdrive','1119', 23, 11 , 29.90 , TRUE, 'https://m.media-amazon.com/images/I/61S0fEjVhOL.jpg'),
('Count Zero','1120', 23, 11 , 29.90 , TRUE, 'https://m.media-amazon.com/images/I/51RG3slItLL.jpg'),
('O Enigma de Andrômeda','1121', 30, 11 , 24.90 , TRUE, 'https://m.media-amazon.com/images/I/51Caun-ejJL.jpg'),
('O Planeta dos Macacos','1122', 31, 11 , 24.90 , TRUE, 'https://m.media-amazon.com/images/I/61YCQnRJZYL.jpg'),
('Jurassic Park','1123', 30, 11 , 39.90 , TRUE, 'https://m.media-amazon.com/images/I/51ImWkbpsjL.jpg'),
('O homem duplo','1124', 15, 11 , 29.90 , TRUE, 'https://m.media-amazon.com/images/I/518c1ZbYYYL.jpg'),
('Eu Sou A Lenda','1125', 32, 11 , 49.90 , TRUE, 'https://m.media-amazon.com/images/I/61jhTWJ4nkL.jpg');

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




