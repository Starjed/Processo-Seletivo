# Implementação Backend para processo seletivo

Projeto - Processo Seletivo

Candidato: Gabriel Albuquerque

Inscrição: 

Tecnologias utilizadas: Java 17, Spring Boot, PostgreSQL, MinIO, Docker, Docker Compose

Objetivo: Criar uma API REST para gerenciar servidores efetivos/temporários, unidades e lotações com suporte a autenticação JWT, upload de imagens para MinIO e consultas personalizadas.

Obs: No final do arquivo deixarei um link com export dos curls para facilitar a chamada dos endpoints 

# Requisitos 

Docker + Docker Compose

Java 17 (para builds locais)

Gradle (caso deseje rodar localmente sem Docker)

#  Como executar o projeto com Docker

Clone o repositório:

`git clone https://github.com/Starjed/Processo-Seletivo`

`./gradlew clean build`

`docker-compose up --build`

O MinIO estará disponível em: http://localhost:9001

Acesso: minioadmin / minioadmin

# Autenticação 

Acesse o endpoint passando os dados:

`{
"username": "admin",
"password": "1234"
}`

`POST http://localhost:8080/auth/login`

**Você receberá um JWT token, que deve ser usado em todas as requisições:**

Authorization: Bearer SEU_TOKEN

Os links gerados são válidos por 5 minutos.

Você poderá dar refresh no seu token para adicionar mais 5 minutos ao chamar o `http://localhost:8080/auth/refresh`

incluindo no body `{"refreshToken": "35bda744-9ff2-45a9-afa1-e822385fbe1e"}` e o seu token antigo, assim ele irá gerar um novo token

com as mesmas roles.

# Endpoints principais para o crud

obs: irei anexar um link do youtube contendo o passo a passo além de incluir o arquivo yml para fazer a simulação das requisições via insomnia

# Upload de Imagens

**Upload de uma ou várias imagens para uma pessoa:**

`POST /api/fotos/pessoa/{pessoaId}/upload`

**Listar URLs temporárias das fotos da pessoa:**

`GET /api/fotos/pessoa/{pessoaId}/links`


