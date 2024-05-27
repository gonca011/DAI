# ---------------------------------------------------------------------- #
# Script generated with: deZign for databases 14.4.1                     #
# Target dBMS:           MySQL 8                                         #
# project file:          TuB_Gamefication.dez                            #
# project name:          TuB_Gamefication                                #
# author:                                                                #
# Script type:           database creation script                        #
# created on:            2024-05-23 18:54                                #
# ---------------------------------------------------------------------- #
DROP TABLE `loggedinutilizador_tipoperfil`;
DROP TABLE `logged_in_utilizadores`;
DROP TABLE `paragens_percursos_schedule`;
DROP TABLE `autocarro`;
DROP TABLE `anunciodealteracaoderota`;
DROP TABLE `paragem`;
DROP TABLE `percurso`;
DROP TABLE `parceiros`;
DROP TABLE `premio`;
DROP TABLE `anunciosdeatraso`;
DROP TABLE `codigodesconto`;
DROP TABLE `promocao`;
DROP TABLE `utilizador`;
DROP TABLE `tipoperfil`;
DROP  TABLE `utilizador_tipoperfil`;
DROP TABLE `utilizador_promocao`;
DROP TABLE `utilizador_premio`;
DROP TABLE `paragens_percursos`;
DROP TABLE `qrcode_image`;

CREATE TABLE `paragens_percursos_schedule` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `nparagem` VARCHAR(40) NOT NULL,
    `idpercurso` VARCHAR(40) NOT NULL,
    `schedule` TIME,
    `idatraso` VARCHAR(40),
    FOREIGN KEY (`nparagem`) REFERENCES `paragem`(`nparagem`),
    FOREIGN KEY (`idpercurso`) REFERENCES `percurso`(`idpercurso`),
    FOREIGN KEY (`idatraso`) REFERENCES `anunciosdeatraso`(`idatraso`)
);

CREATE TABLE `autocarro` (
    `matricula` VARCHAR(40) nOT nuLL,
    `capacidade` inTEGEr,
    `numkm` inTEGEr,
    PRIMARY KEY (`matricula`)
);

CREATE TABLE `anunciodealteracaoderota` (
    `idalteracao` VARCHAR(40) nOT nuLL,
    `motivo` TEXT,
    `nparagem` TEXT,
    `idpercurso` TEXT,
    PRIMARY KEY (`idalteracao`)
);

CREATE TABLE `paragem` (
    `nparagem` VARCHAR(40) nOT nuLL,
    `nome` TEXT,
    `rua` TEXT,
    PRIMARY KEY (`nparagem`)
);

CREATE TABLE `percurso` (
    `idpercurso` VARCHAR(40) nOT nuLL,
    `origem` TEXT,
    `destino` TEXT,
    PRIMARY KEY (`idpercurso`)
);

CREATE TABLE `paragens_percursos` (
    `nparagem` VARCHAR(40) nOT nuLL,
    `idpercurso` VARCHAR(40) nOT nuLL,
    PRIMARY KEY (`nparagem`, `idpercurso`),
    FOREIGN KEY (`idpercurso`) REFERENCES `percurso` (`idpercurso`),
    FOREIGN KEY (`nparagem`) REFERENCES `paragem` (`nparagem`)
);

CREATE TABLE `utilizador_premio` (
    `utilizador_ndoc` VARCHAR(40) NOT NULL,
    `premio_id` VARCHAR(40) NOT NULL,
    PRIMARY KEY (`utilizador_ndoc`, `premio_id`),
    FOREIGN KEY (`utilizador_ndoc`) REFERENCES `utilizador`(`numdocidentificacao`),
    FOREIGN KEY (`premio_id`) REFERENCES `premio`(`id`)
);

CREATE TABLE `utilizador_promocao`(
	`utilizador_ndoc` VARCHAR(40) NOT NULL,
    `promocao_id` VARCHAR(40) NOT NULL,
    PRIMARY KEY (`utilizador_ndoc`, `promocao_id`),
    FOREIGN KEY (`utilizador_ndoc`) REFERENCES `utilizador`(`numdocidentificacao`),
    FOREIGN KEY (`promocao_id`) REFERENCES `promocao`(`id`)
);

CREATE TABLE `parceiros` (
    `id` VARCHAR(40) nOT nuLL,
    `nome` TEXT,
    `descricao` TEXT,
    PRIMARY KEY (`id`)
);

CREATE TABLE `premio` (
    `id` VARCHAR(40) nOT nuLL,
    `nome` TEXT,
    `descricao` TEXT,
    `criteriodeatribuicao` TEXT,
    cOnSTrainT `pk_premio` PRIMARY KEY (`id`)
);

CREATE TABLE `anunciosdeatraso` (
    `idatraso` VARCHAR(40) nOT nuLL,
    `tempoatraso` TEXT,
    `motivoatraso` TEXT,
    PRIMARY KEY (`idatraso`)
);

CREATE TABLE `codigodesconto` (
    `id` VARCHAR(40) nOT nuLL,
    `nome` TEXT,
    `descricao` TEXT,
    `criteriodeatribuicao` TEXT,
    PRIMARY KEY (`id`)
);

CREATE TABLE `promocao` (
    `id` VARCHAR(40) nOT nuLL,
    `nome` TEXT,
    `descricao` TEXT,
    `criteriodeatribuicao` TEXT,
    PRIMARY KEY (`id`)
);

CREATE TABLE `logged_in_utilizadores` (
    `id` INT AUTO_INCREMENT,
    `nif` TEXT,
    `nome` TEXT,
    `email` TEXT,
    `numdocidentificacao` VARCHAR(40) NOT NULL,
    `datadenascimento` TEXT,
    `datavalidadedocidentificacao` TEXT,
    `codigopostal` TEXT,
    `morada` TEXT,
    `porta` TEXT,
    `telemovel` TEXT,
    `pontos` INTEGER,
    `tipoperfil` VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE `tipoperfil` (
	`id` INT NOT NULL,
	`perfil` VARCHAR(255),
	PRIMARY KEY (`id`)
);

CREATE TABLE `utilizador_tipoperfil` (
    `utilizador_ndoc` VARCHAR(40),
    `tipoperfil_id` INT,
    PRIMARY KEY (`utilizador_ndoc`, `tipoperfil_id`),
    FOREIGN KEY (`utilizador_ndoc`) REFERENCES `utilizador`(`numdocidentificacao`),
    FOREIGN KEY (`tipoperfil`_id`) REFERENCES `tipoperfil(`id`)
);

CREATE TABLE `loggedinutilizador_tipoperfil` (
    `loggedinutilizador_id` INT,
    `tipoperfil_id` INT,
    PRIMARY KEY (`loggedinutilizador_id`, `tipoperfil_id`),
    FOREIGN KEY (`loggedinutilizador_id`) REFERENCES `logged_in_utilizadores`(`id`),
    FOREIGN KEY (`tipoperfil_id`) REFERENCES `tipoperfil`(`id`)
);

CREATE TABLE `utilizador` (
    `nif` TEXT,
    `nome` TEXT,
    `email` TEXT,
    `numdocidentificacao` VARCHAR(40) NOT NULL,
    `datadenascimento` TEXT,
    `datavalidadedocidentificacao` TEXT,
    `codigopostal` TEXT,
    `morada` TEXT,
    `porta` TEXT,
    `telemovel` TEXT,
    `pontos` inTEGEr,
    PRIMARY KEY (`numdocidentificacao`)
);


CREATE TABLE `qrcode_image` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `text` VARCHAR(255),
    `qrcodeimage` BLOB,
    `utilizador_id` VARCHAR(40),
    FOREIGN KEY (`utilizador_id`) REFERENCES `utilizador`(`numdocidentificacao`)
);

INSERT INTO `tipoperfil` (id, perfil) VALUES (1, 'CLIENTE');
INSERT INTO `tipoperfil` (id, perfil) VALUES (2, 'ADMINISTRADOR');
INSERT INTO `tipoperfil` (id, perfil) VALUES (3, 'COORDENADOR');
INSERT INTO `tipoperfil` (id, perfil) VALUES (4, 'GESTOR_DE_FROTA');
INSERT INTO `tipoperfil` (id, perfil) VALUES (5, 'MOTORISTA');

SELECT * FROM paragens_percursos_schedule;
SELECT * FROM utilizador;
SELECT * FROM logged_in_utilizadores;
SELECT * FROM percurso ;
SELECT * FROM anunciosdeatraso ;
SELECT * FROM paragem ;
SELECT * FROM paragens_percursos ;
SELECT * FROM utilizador_tipoperfil;
SELECT * FROM parceiros ;
SELECT * FROM premio ;
SELECT * FROM codigodesconto ;
SELECT * FROM promocao ;
SELECT * from anunciodealteracaoderota ;
SELECT * FROM autocarro ;
SELECT * FROM utilizador_premio;
SELECT * FROM qrcode_image;
SELECT * FROM tipoperfil ;

SHOW STATUS WHERE `variable_name` = 'Threads_connected';
SHOW STATUS WHERE `variable_name` = 'Max_used_connections';