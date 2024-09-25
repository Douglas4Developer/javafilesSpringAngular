
# Gerenciador de Arquivos e Diretórios
![image](https://github.com/user-attachments/assets/1f1b8a90-4fcf-428d-b8fe-cfbfbc334eba)

Este projeto é um sistema de gerenciamento de arquivos e diretórios, desenvolvido com **Java 21**, **Spring Boot**, **Angular 18** com **standalone components**, **MySQL**, e **Docker**. Ele permite visualizar uma estrutura de diretórios, incluindo subdiretórios e arquivos, de forma visual e interativa, com uma interface baseada em Material Design.

## Tecnologias Utilizadas

- **Backend**: 
  - Java 21
  - Spring Boot
  - Spring Data JPA
  - MySQL
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
   git clone https://github.com/Douglas4Developer/javafilesSpringReact.git
   cd javafilesSpringReact
   ```
Aqui está o README detalhado e aprimorado para o recrutador rodar seu projeto localmente:

---

# Sistema de Gerenciamento de Arquivos e Diretórios

Este projeto consiste em um sistema web para gerenciamento de arquivos e diretórios. O backend foi desenvolvido em **Java (Spring Boot)**, enquanto o frontend utiliza **Angular 18**. O sistema é orquestrado usando **Docker** para facilitar a execução e configuração do ambiente.

## Pré-requisitos

Antes de iniciar, certifique-se de que os seguintes softwares estão instalados no seu ambiente:

- [Docker](https://docs.docker.com/get-docker/) e [Docker Compose](https://docs.docker.com/compose/install/)
- Git

## Instruções para Clonar e Executar o Projeto

1. **Clone o Repositório**:

   Execute os seguintes comandos no terminal para clonar o projeto em sua máquina local:
   ```bash
   git clone https://github.com/Douglas4Developer/javafilesSpringReact.git
   cd javafilesSpringReact
   ```

2. **Configuração via Docker Compose**:

   O projeto foi configurado para rodar tanto o backend quanto o frontend em contêineres Docker. Abaixo estão as instruções detalhadas de como executar o projeto utilizando **Docker Compose**.

   ### Backend (Java Spring Boot):
   - **Base da Imagem**: OpenJDK 21.
   - **Porta Exposta**: `8080`.
   - O backend se comunica com o banco de dados MySQL e está configurado para usar **Flyway** para migrações de banco de dados.

   ### Frontend (Angular 18):
   - **Base da Imagem**: Node.js 18.
   - **Porta Exposta**: `4200` (para desenvolvimento com **ng serve**).
   - O frontend consome a API do backend e foi projetado para rodar em um ambiente separado, mas na mesma rede Docker.

3. **Subir o Sistema com Docker Compose**:

   No diretório raiz do projeto, execute o seguinte comando para construir as imagens e iniciar os contêineres:
   ```bash
   docker-compose up --build
   ```

   O **docker-compose.yml** foi configurado para:

   - Construir e rodar o backend na porta **8080**.
   - Rodar o frontend na porta **4200**, permitindo o acesso à aplicação Angular via navegador.
   - Gerenciar o banco de dados MySQL necessário para o backend.

4. **Acessar a Aplicação**:

   - **Backend**: A API pode ser acessada via `http://localhost:8080`.
   - **Frontend**: A interface web estará disponível em `http://localhost:4200`.

## Estrutura do Projeto

### Backend

- **Spring Boot**: Backend utilizando o framework Spring Boot com persistência JPA.
- **Banco de Dados MySQL**: O backend utiliza MySQL para armazenar os diretórios e arquivos.
- **Flyway**: Utilizado para versionamento e migrações do banco de dados.

### Frontend

- **Angular 18**: Desenvolvido com Angular standalone components, com foco em uma interface simples e responsiva para visualizar diretórios e arquivos.
- **Node.js**: Utilizado para compilar e servir o frontend localmente no ambiente de desenvolvimento.

## Variáveis de Ambiente

As variáveis de ambiente foram configuradas diretamente no arquivo `docker-compose.yml`. Se precisar fazer ajustes manuais, veja as configurações atuais:

```yml
environment:
  SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/javafiles_db
  SPRING_DATASOURCE_USERNAME: root
  SPRING_DATASOURCE_PASSWORD: 326598
  SPRING_DATASOURCE_DRIVER_CLASS_NAME: com.mysql.cj.jdbc.Driver
```

## Rodando Testes

- **Backend**: Os testes unitários podem ser executados com o Maven, se você deseja rodar localmente sem Docker:
  ```bash
  mvn clean test
  ```

## Informações Adicionais

- Se encontrar algum problema, certifique-se de que o **Docker** e o **Docker Compose** estão configurados corretamente no seu sistema.
- Para parar os contêineres, basta rodar:
  ```bash
  docker-compose down
  ```

---

Este README foi criado para facilitar o processo de execução do projeto. Espero que isso ajude a impressionar o recrutador e garantir que tudo funcione de forma fluida!

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

## Testes Unitários

### Backend

Os testes unitários para o backend foram implementados utilizando **JUnit 5** e **Mockito** para simular dependências. Eles garantem que as principais funcionalidades do sistema, como criação, listagem, busca e deleção de arquivos e diretórios, funcionem corretamente.

### Testes Implementados

Os testes cobrem as principais camadas de lógica de negócio do projeto:

- **ArquivoServiceTest**: Testa as funcionalidades relacionadas à criação, listagem, busca e remoção de arquivos no sistema.
  - Teste de criação de arquivo.
  - Teste de busca por ID de arquivo.
  - Teste de exclusão de arquivos.
  - Teste para validar exceções, como tentar deletar ou buscar arquivos que não existem.
  
- **DiretorioServiceTest**: Testa as operações sobre diretórios, garantindo que subdiretórios e arquivos sejam corretamente gerenciados.

### Rodando os Testes

Para rodar os testes localmente, basta utilizar o Maven. Dentro do diretório do backend (`javafiles`), execute o seguinte comando:

```bash
mvn clean test
```

Este comando irá rodar todos os testes unitários definidos no projeto e exibir o resultado no terminal. Você pode também verificar os relatórios de testes que são gerados na pasta `target/surefire-reports`.

### Cobertura de Testes

O objetivo principal dos testes é garantir que as regras de negócio sejam validadas adequadamente. É recomendável que você execute os testes sempre que realizar alterações significativas no código para garantir que não quebrou nenhuma funcionalidade existente.


## Rodando Testes

- **Backend**: Os testes unitários podem ser executados com o Maven, se você deseja rodar localmente sem Docker:
  ```bash
  mvn clean test
  ```

## Informações Adicionais

- Se encontrar algum problema, certifique-se de que o **Docker** e o **Docker Compose** estão configurados corretamente no seu sistema.
- Para parar os contêineres, basta rodar:
  ```bash
  docker-compose down
  ```

---
 
