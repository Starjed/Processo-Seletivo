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

`git clone https://github.com/seu-usuario/seu-repositorio.git`

`./gradlew clean build`

`docker-compose up --build`

A aplicação estará disponível em: http://localhost:8080

O MinIO estará disponível em: http://localhost:9001

Acesso: minioadmin / minioadmin

**Essas portas podem ser configuradas na application properties caso queira outra porta**

# Autenticação 

Acesse o endpoint passando os dados:

`{
"username": "admin",
"password": "1234"
}`

`POST http://localhost:8080/auth/login`

**Você receberá um JWT token, que deve ser usado em requisições:**

Authorization: Bearer SEU_TOKEN

# Upload de Imagens

**Upload de uma ou várias imagens para uma pessoa:**

`POST /api/fotos/pessoa/{pessoaId}/upload`

**Listar URLs temporárias das fotos da pessoa:**

````GET /api/fotos/pessoa/{pessoaId}/links````

Os links gerados são válidos por 5 minutos e acessíveis via navegador.

# Requisitos solicitados

Todos os serviços estão isolados por containers.

Não é aceito nenhuma requisição de domínios externos e o CORS não está habilitado para acesso ao frontend, porém ficará habilitado ao tirar o comentário da linha

`config.setAllowedOrigins(List.of("http://localhost:4200"));`

Os links e o token de autenticação ambos são gerados por 5 minutos,

O JWT garante segurança no acesso à API, retornando não autorizado caso não possua o token.

Consulta por unidade: /api/servidores-efetivos/unidade/{unidId}

Endereço funcional por nome: /api/servidores-efetivos/enderecos-funcionais?nome=gabriel (pode ser alterado para gab, ou inicio de nome que tenha sido cadastrado)