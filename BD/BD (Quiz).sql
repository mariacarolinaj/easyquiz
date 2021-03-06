CREATE TABLE Usuario
(
  cod_usuario Bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  cod_perfil Bigint NOT NULL,
  cod_escolaridade Bigint NOT NULL,
  nom_usuario Char(40) NOT NULL,
  dat_nascimento Date NOT NULL,
  txt_email Char(40) NOT NULL,
  txt_senha Char(100) NOT NULL,
  PRIMARY KEY (cod_usuario)
)
;

CREATE INDEX IX_Relationship1 ON Usuario (cod_perfil)
;

CREATE INDEX IX_Relationship18 ON Usuario (cod_escolaridade)
;

ALTER TABLE Usuario ADD UNIQUE txt_email (txt_email)
;

CREATE TABLE Perfil
(
  cod_perfil Bigint NOT NULL,
  nom_perfil Char(25) NOT NULL
)
;

ALTER TABLE Perfil ADD  PRIMARY KEY (cod_perfil)
;

CREATE TABLE Questao
(
  cod_questao Bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  cod_dificuldade Bigint NOT NULL,
  cod_disciplina Bigint UNSIGNED NOT NULL,
  cod_modulo Bigint UNSIGNED NOT NULL,
  cod_tipo Char(1) NOT NULL,
  txt_enunciado Text NOT NULL,
  img_enunciado Longblob,
  seq_questao_correta Bigint,
  txt_resposta_aberta Text,
  PRIMARY KEY (cod_questao)
)
;

CREATE INDEX IX_Relationship6 ON Questao (cod_dificuldade)
;

CREATE INDEX IX_Relationship7 ON Questao (cod_modulo,cod_disciplina)
;

CREATE TABLE Questao_Fechada
(
  cod_questao Bigint UNSIGNED NOT NULL,
  seq_alternativa Bigint UNSIGNED NOT NULL,
  txt_alternativa Text NOT NULL
)
;

ALTER TABLE Questao_Fechada ADD  PRIMARY KEY (cod_questao,seq_alternativa)
;

CREATE TABLE Disciplina
(
  cod_disciplina Bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  nom_disciplina Char(40) NOT NULL,
  PRIMARY KEY (cod_disciplina)
)
;

CREATE TABLE Modulo
(
  cod_disciplina Bigint UNSIGNED NOT NULL,
  cod_modulo Bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  nom_modulo Char(50) NOT NULL,
  PRIMARY KEY (cod_modulo,cod_disciplina)
)
;

CREATE TABLE Dificuldade
(
  cod_dificuldade Bigint NOT NULL,
  des_dificuldade Char(20) NOT NULL
)
;

ALTER TABLE Dificuldade ADD  PRIMARY KEY (cod_dificuldade)
;

CREATE TABLE Post
(
  cod_post Bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  cod_questao Bigint UNSIGNED NOT NULL,
  cod_usuario Bigint UNSIGNED NOT NULL,
  txt_conteudo Text NOT NULL,
  dat_criacao Datetime NOT NULL,
  PRIMARY KEY (cod_post)
)
;

CREATE INDEX IX_Relationship8 ON Post (cod_questao)
;

CREATE INDEX IX_Relationship17 ON Post (cod_usuario)
;

CREATE TABLE Sessao
(
  cod_usuario Bigint UNSIGNED NOT NULL,
  dat_inicio Datetime NOT NULL,
  dat_fim Datetime
)
;

ALTER TABLE Sessao ADD  PRIMARY KEY (dat_inicio,cod_usuario)
;

CREATE TABLE QuestaoFechadaResposta
(
  dat_inicio Datetime NOT NULL,
  cod_usuario Bigint UNSIGNED NOT NULL,
  cod_questao Bigint UNSIGNED NOT NULL,
  seq_questao_resposta Bigint NOT NULL
)
;

ALTER TABLE QuestaoFechadaResposta ADD  PRIMARY KEY (dat_inicio,cod_usuario,cod_questao)
;

CREATE TABLE Escolaridade
(
  cod_escolaridade Bigint NOT NULL,
  nom_escolaridade Char(40) NOT NULL
)
;

ALTER TABLE Escolaridade ADD  PRIMARY KEY (cod_escolaridade)
;

ALTER TABLE Usuario ADD CONSTRAINT Relationship1 FOREIGN KEY (cod_perfil) REFERENCES Perfil (cod_perfil) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE Questao_Fechada ADD CONSTRAINT Relationship2 FOREIGN KEY (cod_questao) REFERENCES Questao (cod_questao) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE Modulo ADD CONSTRAINT Relationship4 FOREIGN KEY (cod_disciplina) REFERENCES Disciplina (cod_disciplina) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE Questao ADD CONSTRAINT Relationship6 FOREIGN KEY (cod_dificuldade) REFERENCES Dificuldade (cod_dificuldade) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE Questao ADD CONSTRAINT Relationship7 FOREIGN KEY (cod_modulo, cod_disciplina) REFERENCES Modulo (cod_modulo, cod_disciplina) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE Post ADD CONSTRAINT Relationship8 FOREIGN KEY (cod_questao) REFERENCES Questao (cod_questao) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE Sessao ADD CONSTRAINT Relationship14 FOREIGN KEY (cod_usuario) REFERENCES Usuario (cod_usuario) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE QuestaoFechadaResposta ADD CONSTRAINT Relationship15 FOREIGN KEY (dat_inicio, cod_usuario) REFERENCES Sessao (dat_inicio, cod_usuario) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE QuestaoFechadaResposta ADD CONSTRAINT Relationship16 FOREIGN KEY (cod_questao) REFERENCES Questao (cod_questao) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE Post ADD CONSTRAINT Relationship17 FOREIGN KEY (cod_usuario) REFERENCES Usuario (cod_usuario) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE Usuario ADD CONSTRAINT Relationship18 FOREIGN KEY (cod_escolaridade) REFERENCES Escolaridade (cod_escolaridade) ON DELETE RESTRICT ON UPDATE RESTRICT
;


INSERT INTO `dificuldade` (`cod_dificuldade`, `des_dificuldade`) VALUES
(1, 'Desafio'),
(2, 'Difícil'),
(3, 'Médio'),
(4, 'Fácil');

INSERT INTO `disciplina` (`cod_disciplina`, `nom_disciplina`) VALUES
(1, 'Física'),
(2, 'História'),
(3, 'Matemática'),
(4, 'Português');

INSERT INTO `escolaridade` (`cod_escolaridade`, `nom_escolaridade`) VALUES
(1, 'Analfabeto'),
(2, 'Fundamental incompleto'),
(3, 'Fundamental completo'),
(4, 'Médio incompleto'),
(5, 'Médio completo'),
(6, 'Superior incompleto'),
(7, 'Superior completo'),
(8, 'Mestrado'),
(9, 'Doutorado');

INSERT INTO `perfil` (`cod_perfil`, `nom_perfil`) VALUES
(1, 'aluno'),
(2, 'professor');

