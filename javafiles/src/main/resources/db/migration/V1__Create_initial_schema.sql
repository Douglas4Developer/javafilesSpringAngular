-- V1__Create_initial_schema.sql

-- Criação da tabela Diretorio
CREATE TABLE diretorio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    diretorio_pai_id BIGINT DEFAULT NULL,
    CONSTRAINT fk_diretorio_pai FOREIGN KEY (diretorio_pai_id) REFERENCES diretorio(id) ON DELETE SET NULL
);

-- Criação da tabela Arquivo
CREATE TABLE arquivo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    diretorio_id BIGINT NOT NULL,
    CONSTRAINT fk_arquivo_diretorio FOREIGN KEY (diretorio_id) REFERENCES diretorio(id) ON DELETE CASCADE
);


-- Inserção de diretórios
INSERT INTO diretorio (nome, diretorio_pai_id) VALUES ('Projetos', NULL);  -- Diretório raiz
INSERT INTO diretorio (nome, diretorio_pai_id) VALUES ('Pessoal', NULL);  -- Diretório raiz
INSERT INTO diretorio (nome, diretorio_pai_id) VALUES ('Fotos de Viagem', 1);  -- Subdiretório de "Projetos"
INSERT INTO diretorio (nome, diretorio_pai_id) VALUES ('Documentos', 2);  -- Subdiretório de "Pessoal"

-- Inserção de arquivos
INSERT INTO arquivo (nome, diretorio_id) VALUES ('relatorio.txt', 1);  -- Arquivo no diretório "Projetos"
INSERT INTO arquivo (nome, diretorio_id) VALUES ('foto1.jpg', 3);  -- Arquivo no subdiretório "Fotos de Viagem"
INSERT INTO arquivo (nome, diretorio_id) VALUES ('foto2.jpg', 3);  -- Arquivo no subdiretório "Fotos de Viagem"
INSERT INTO arquivo (nome, diretorio_id) VALUES ('curriculo.pdf', 2);  -- Arquivo no diretório "Pessoal"
INSERT INTO arquivo (nome, diretorio_id) VALUES ('certidao.pdf', 4);  -- Arquivo no subdiretório "Documentos"


