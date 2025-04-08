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

Este arquivo do drive possui todas as requests utilizando o aplicativo Insominia, o conteúdo dele é um yaml que pode ser importado para o aplicativo.

https://drive.google.com/file/d/1MTlKD2AymCgAh_G2ELq3OyDvCoaaV2cM/view

##  Pessoa

| Método | Endpoint                        | Descrição                           |
|--------|----------------------------------|-------------------------------------|
| GET    | `/api/pessoas`                  | Lista todas as pessoas              |
| GET    | `/api/pessoas/{id}`             | Busca uma pessoa por ID             |
| POST   | `/api/pessoas`                  | Cria uma nova pessoa                |
| PUT    | `/api/pessoas/editar/{id}`      | Edita os dados de uma pessoa        |
| DELETE | `/api/pessoas/excluir/{id}`     | Exclui uma pessoa                   |

---

##  Cidade

| Método | Endpoint                        | Descrição                           |
|--------|----------------------------------|-------------------------------------|
| GET    | `/api/cidades`                  | Lista todas as cidades              |
| POST   | `/api/cidades`                  | Cria uma nova cidade                |
| PUT    | `/api/cidades/editar/{id}`      | Edita dados de uma cidade           |
| DELETE | `/api/cidades/excluir/{id}`     | Remove uma cidade                   |

---

##  Unidade

| Método | Endpoint                        | Descrição                           |
|--------|----------------------------------|-------------------------------------|
| GET    | `/api/unidades`                 | Lista todas as unidades             |
| POST   | `/api/unidades`                 | Cria uma nova unidade               |
| PUT    | `/api/unidades/editar/{id}`     | Edita uma unidade                   |
| DELETE | `/api/unidades/excluir/{id}`    | Exclui uma unidade                  |

---

##  Lotação

| Método | Endpoint                        | Descrição                           |
|--------|----------------------------------|-------------------------------------|
| GET    | `/api/lotacoes`                 | Lista todas as lotações             |
| POST   | `/api/lotacoes`                 | Cria uma lotação                    |
| PUT    | `/api/edicao/lotacao/{id}`      | Edita uma lotação                   |
| DELETE | `/api/lotacoes/excluir/{id}`    | Exclui uma lotação                  |

---

## Servidor Efetivo

| Método | Endpoint                                     | Descrição                                 |
|--------|-----------------------------------------------|-------------------------------------------|
| GET    | `/api/servidores/por-unidade/{id}`            | Lista servidores por unidade              |
| GET    | `/api/servidores/efetivos/{id}`               | Unidade funcional do servidor efetivo     |
| POST   | `/api/servidores/efetivos`                    | Cria um servidor efetivo                  |
| PUT    | `/api/servidores/efetivos/{id}`               | Edita um servidor efetivo                 |
| DELETE | `/api/servidores/efetivos/excluir/{id}`       | Exclui um servidor efetivo                |

---

## Servidor Temporário

| Método | Endpoint                                     | Descrição                                 |
|--------|-----------------------------------------------|-------------------------------------------|
| GET    | `/api/servidores/temporarios`                | Lista todos os servidores temporários     |
| POST   | `/api/servidores/temporarios/criar`          | Cria um servidor temporário               |
| PUT    | `/api/servidores/temporarios/editar/{id}`    | Edita um servidor temporário              |

---

## Endereços

| Método | Endpoint                                | Descrição                                     |
|--------|------------------------------------------|-----------------------------------------------|
| GET    | `/api/pessoa-endereco`                  | Lista pessoas com seus endereços              |
| POST   | `/api/enderecos`                        | Cria endereço associado à cidade              |
| PUT    | `/api/enderecos/editar/{id}`            | Edita um endereço                             |
| DELETE | `/api/enderecos/excluir/{id}`           | Remove um endereço                            |

---

## Associações

| Ação                                  | Endpoint                                         |
|--------------------------------------|--------------------------------------------------|
| Associar Endereço a Pessoa           | `POST /api/pessoa-endereco`                     |
| Associar Endereço a Unidade          | `POST /api/unidade-endereco/associar`           |
| Associar Endereço a Lotação          | `POST /api/lotacoes-enderecos`                  |

---

## Fotos

| Método | Endpoint                                | Descrição                                             |
|--------|------------------------------------------|-------------------------------------------------------|
| POST   | `/api/fotos/upload`                     | Upload de uma ou mais imagens para uma pessoa        |
| GET    | `/api/fotos/pessoa/{id}/links`          | Recupera links temporários das fotos da pessoa       |
| PUT    | `/api/fotos/editar/{id}`                | Atualiza uma foto                                    |
| DELETE | `/api/fotos/excluir/{id}`               | Exclui uma foto                                      |

---

## Endpoints Específicos

| Endpoint                                             | Descrição                                                |
|------------------------------------------------------|----------------------------------------------------------|
| `GET /api/servidores/enderecos-funcionais?nome=XXX` | Busca endereço funcional da unidade por nome do servidor |

---

## CORS e Segurança

- Sem JWT → **bloqueado**
- Origem maliciosa (`http://evil.com`) → **bloqueado**
- Frontend Angular (`http://localhost:4200`) com token válido → **permitido**
