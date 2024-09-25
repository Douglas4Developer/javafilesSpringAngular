# Fase 1: Construindo a aplicação Angular
FROM node:16-alpine AS build

# Definindo o diretório de trabalho dentro do container
WORKDIR /usr/src/app

# Copiando os arquivos package.json e package-lock.json para instalar as dependências
COPY package*.json ./

# Instalando as dependências do projeto Angular
RUN npm install

# Copiando o restante dos arquivos do projeto Angular para o container
COPY . .

# Gerando o build da aplicação Angular para o ambiente de produção
RUN npm run build -- --configuration production

# Fase 2: Servindo a aplicação Angular com Nginx
FROM nginx:alpine

# Copiando o arquivo de configuração do Nginx para o container
COPY ./nginx.conf /etc/nginx/conf.d/default.conf

# Copiando os arquivos do build do Angular gerados na fase 1
COPY --from=build /usr/src/app/dist/file-system-frontend /usr/share/nginx/html

# Expondo a porta 80 para o Nginx
EXPOSE 80

# Iniciando o Nginx
CMD ["nginx", "-g", "daemon off;"]
