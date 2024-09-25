# Usando a imagem do Node.js para o build do frontend
FROM node:18 AS build

WORKDIR /app

# Copiando os arquivos do projeto
COPY . .

# Instalando dependências
RUN npm install

# Compilando o projeto (ajuste de acordo com seu comando de build)
RUN npm run build --prod

# Usando uma imagem Nginx para servir o frontend
FROM nginx:alpine
COPY --from=build /app/dist/file-system-frontend /usr/share/nginx/html

EXPOSE 80
