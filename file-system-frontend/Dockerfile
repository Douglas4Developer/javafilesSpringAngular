# Etapa 1: Usar uma imagem oficial do Node.js como base para desenvolvimento
FROM node:18 AS development

# Diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar os arquivos package.json e package-lock.json para o contêiner
COPY package*.json ./

# Instalar as dependências da aplicação
RUN npm install

# Copiar o restante do código da aplicação para o contêiner
COPY . .

# Expor a porta 4200 para o ng serve
EXPOSE 4200

# Usar o ng serve para rodar a aplicação na porta 4200
CMD ["npm", "run", "start"]
