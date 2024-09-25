
# Gerenciador de Arquivos e Diretórios
![image](https://github.com/user-attachments/assets/1f1b8a90-4fcf-428d-b8fe-cfbfbc334eba)

Este projeto é um sistema de gerenciamento de arquivos e diretórios, desenvolvido com **Java 21**, **Spring Boot**, **Angular 18** com **standalone components**, **PostgreSQL**, e **Docker**. Ele permite visualizar uma estrutura de diretórios, incluindo subdiretórios e arquivos, de forma visual e interativa, com uma interface baseada em Material Design.

## Tecnologias Utilizadas

- **Backend**: 
  - Java 21
  - Spring Boot
  - Spring Data JPA
  - PostgreSQL
  - Swagger para documentação
  - Docker para conteinerização
- **Frontend**:
  - Angular 18 com **standalone components**
  - Angular Material Design (mat-tree para exibir a estrutura de diretórios)
  - TypeScript
- **Versionamento de Banco**: 
  - Flyway para migrações de banco de dados

## Funcionalidades

1. **Listar diretórios e subdiretórios**: Exibe uma árvore de diretórios, onde cada diretório pode conter subdiretórios e arquivos.
2. **Gerenciar arquivos**: Cada diretório pode conter múltiplos arquivos de diferentes tipos (.txt, .jpg, etc).
3. **Inserção de dados com Docker e Flyway**: O projeto está preparado para ser executado em um ambiente Docker com migrações de banco de dados realizadas pelo Flyway.

## Como Rodar o Projeto

### Pré-requisitos

- **Docker** instalado na sua máquina.
- **JDK 21** (Java 21) para o backend.
- **Node.js e npm** para o frontend.

### Passos para Rodar o Projeto com Docker

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/Douglas4Developer/gerenciador-arquivos-diretorios.git
   cd gerenciador-arquivos-diretorios
   ```

2. ### Docker Setup

Para facilitar a execução do projeto, utilizamos contêineres Docker para o **backend** (Java Spring Boot) e o **frontend** (Angular ou React). Com o uso do **docker-compose**, é possível subir ambos os serviços de forma orquestrada.
Segue a explicação de como o Docker foi configurado:

1. **Dockerfile do Backend**:
    - O backend utiliza uma imagem base do OpenJDK 21 para executar a aplicação Spring Boot.
    - A aplicação é copiada para o contêiner e executada com o comando `java -jar app.jar`.
    - Porta mapeada: `8080`.

2. **Dockerfile do Frontend**:
    - Para o frontend, utilizamos o **Node.js** para fazer o build do projeto.
    - Após o build, os arquivos resultantes são copiados para uma imagem do **Nginx**, que é usada para servir a aplicação.
    - Porta mapeada: `80`.

3. **Docker Compose**:
    - O arquivo `docker-compose.yml` orquestra ambos os serviços.
    - Ele constrói as imagens e sobe os contêineres do **backend** e **frontend**.
    - Ambos os serviços compartilham a mesma rede, facilitando a comunicação.
    - Com o comando `docker-compose up --build`, você pode levantar todo o sistema com um único comando.

---

### Endpoints da API

- `GET /api/diretorios`: Lista todos os diretórios (apenas diretórios raiz são listados, subdiretórios aparecem dentro dos diretórios pais).
- `POST /api/diretorios`: Cria um novo diretório.
- `POST /api/arquivos`: Cria um novo arquivo dentro de um diretório existente.

### Documentação da API com Swagger

A documentação da API pode ser acessada em: `http://localhost:8080/swagger-ui/index.html`.

## Estrutura do Projeto

- **Backend**:
  - `DiretorioController`: Controlador que gerencia diretórios e subdiretórios.
  - `ArquivoController`: Controlador que gerencia arquivos.
  - `DiretorioService` e `ArquivoService`: Serviços que encapsulam as regras de negócios.
  - `Flyway` é utilizado para as migrações de banco de dados.
- **Frontend**:
  - `DirectoryListComponent`: Componente Angular que exibe a árvore de diretórios.
  - `Material Design`: Utilizamos Angular Material para a estrutura de árvore (`mat-tree`), ícones e cartões (`mat-card`).


---

Essa parte explica como o Docker foi usado para orquestrar o backend e o frontend com base na configuração que fizemos anteriormente.

## Banco de Dados

O banco de dados é composto por duas tabelas principais: `diretorio` e `arquivo`. A relação entre as tabelas é representada da seguinte forma:

- Um `diretorio` pode conter vários `arquivos` e subdiretórios.
- Um `arquivo` pertence a apenas um `diretorio`.

### Migração de Banco de Dados

O arquivo de migração inicial se encontra no diretório `src/main/resources/db/migration` com o nome `V1__Create_initial_schema.sql`.

```sql
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
```

## Inserções no Banco para Exibição Inicial

Aqui está um script SQL com inserções fictícias para popular o banco de dados com alguns diretórios e arquivos.

```sql
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
```

### Exemplo de Estrutura:

1. Diretório raiz: **Projetos**
   - Subdiretório: **Fotos de Viagem**
     - Arquivo: **foto1.jpg**
     - Arquivo: **foto2.jpg**
   - Arquivo: **relatorio.txt**
   
2. Diretório raiz: **Pessoal**
   - Subdiretório: **Documentos**
     - Arquivo: **certidao.pdf**
   - Arquivo: **curriculo.pdf**

## Melhorias Futuras

- Adicionar funcionalidade para upload de arquivos diretamente pela interface.
- Implementar a funcionalidade de deletar diretórios e arquivos via API e frontend.
- Melhorar a interface para permitir a busca por arquivos e diretórios.

---
