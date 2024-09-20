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
