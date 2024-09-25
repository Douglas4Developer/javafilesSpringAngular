# Usando a imagem do Node.js para o build do frontend
FROM node:18 AS build

WORKDIR /app

# Copiando os arquivos do projeto
COPY . .

# Instalando dependências e fazendo o build do projeto
RUN npm install
RUN npm run build --prod

# Usando uma imagem Nginx para servir o frontend
FROM nginx:alpine
COPY --from=build /app/dist/ /usr/share/nginx/html

# Expondo a porta 80
EXPOSE 80
