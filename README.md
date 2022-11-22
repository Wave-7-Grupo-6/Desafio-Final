<img src="https://github.com/thays-gama/desafio_spring/blob/main/src/main/resources/images/dh.png" alt="logotipo Digital House" width="140px" align="right">
<img src="https://github.com/thays-gama/desafio_spring/blob/main/src/main/resources/images/meli.png" alt="logotipo Mercado Livre" width="100px" align="right">

# 🍃 Desafio Final - Requisito 06 - Thays Gama - DigitalHouse

Projeto feito no bootcamp [Java BackEnd - MercadoLivre](https://www.mercadolibre.com.ar/itacademy) com objetivo de criar sistema completo com diversos [CRUDs](https://developer.mozilla.org/pt-BR/docs/Glossary/CRUD) a partir da criação de uma API RESTful utilizando a estrutura MSC e a implementação de Testes Unitários e de Integração, para podermos solicionar a problemática apresentada.

## ⭐ Habilidades

- Utilizar as habilidades ministradas em aula pela DigitalHouse.
    - POO
    - Java
    - Spring
    - REST e RESTful
    - JSON
    - Arquitetura MVC
    - Testes Unitários
    - Testes de Integração
- Criar uma API utilizando o Spring
- Criar os Testes de Unidade e de Integração.

## 📝 Problemática

O administrador quer implementar segurança com token JWT nos recursos para garantir acesso somente a pessoas autorizadas, por esta razão, iremos:

- [x] 6.1 - Criar um endpoint para criar um novo role de usuário.
- [x] 6.2 - Criar um endpoint para listar todos os roles de usuário.
- [x] 6.3 - Criar um endpoint para recuperar um role de usuário por id.
- [x] 6.4 - Criar um endpoint para atualizar um role de usuário.
- [x] 6.5 - Criar um endpoint para deletar um role de usuário por id.
- [x] 6.6 - Modificar endpoints de criação de usuário para que seus roles sejam atribuidos nesse momento.
- [x] 6.7 - Criar um endpoint para login retornando um access token e um refresh token.
- [x] 6.8 - Criar um endpoint para atualizar o access token utilizando o refresh token.
- [x] 6.9 - Determinar as configurações de acesso dos endpoints.
- [x] 6.10 - Criar filtro nas requisições para ler e validar o token JWT.

## 💻 Como iniciar na IDE

1. Faça o clone do projeto:
```shell
git clone git@github.com:Wave-7-Grupo-6/Desafio-Final.git
```
2. Abra o projeto na sua IDE de escolha:
```shell
mvn spring-boot:run
```

## 🧑🏻‍🚀 Como testar no Postman

1. Importar o arquivo no Postman: [POSTMAN](https://github.com/Wave-7-Grupo-6/Desafio-Final/blob/discount-coupon/src/main/resources/Discount%20Coupon%20Postman.json)

# 💡 Documentação da API

### Cadastro de um novo role

```http
POST /api/v1/role
```

| Corpo da requisição     | Tipo             | Descrição                                               |
|:------------------------|:-----------------|:--------------------------------------------------------|
| `name` | `string` | **Obrigatório**. Todos os campos no corpo da requisição |

**Formato do corpo da requisição**
```json
{
    "name": "ROLE_ADMIN"
}
```

**Retorno em caso de sucesso**
```json
{
    "id": 1,
    "name" : "ROLE_ADMIN"
}
```

### Listar todos os roles

```http
GET /api/v1/role
```

| Descrição                                                           |
|:--------------------------------------------------------------------|
| Será retornado um array com todos os roles cadastrados |

**Retorno em caso de sucesso**
```json
[
    {
        "id": 1,
        "name": "ROLE_ADMIN"
    },
    {
        "id": 2,
        "name": "ROLE_CLIENT"
    }
]
```

### Listar um role pelo id

```http
GET /api/v1/role/{id}
```

| Descrição                                                                                  |
|:-------------------------------------------------------------------------------------------|
| Será retornado um role cujo id tenha alguma correspondência no banco de dados |

| Path Variable  | Tipo   | Descrição                                             |
| :------------------- |:-------| :---------------------------------------------------- |
| `id`                 | `long` | **Obrigatório**. Parâmetros devem ser passados na URL |

**Retorno em caso de sucesso**
```json
{
    "id": 1,
    "name" : "ROLE_ADMIN"
}
```

**Retorno em caso de erro**
```json
{
    "title": "Object not found",
    "message": "Role not found.",
    "status": 404,
    "fieldErrors": null,
    "timestamp": "2022-11-21T10:17:57.351544"
}
```

### Atualizar um role de usuário

```http
PUT /api/v1/role
```

| Descrição                                                                                                   |
|:------------------------------------------------------------------------------------------------------------|
| Será retornado role atualizado cujo id tenha alguma correspondência no banco de dados |

| Corpo da requisição | Tipo     | Descrição                                             |
|:--------------------|:---------|:------------------------------------------------------|
| `id, name`          | `long, string` | **Obrigatório**. Parâmetros devem ser passados na URL |

**Formato do corpo da requisição**
```json
    {
       "id": 1,
       "name" : "ROLE_SELLER"
    }
```
**Retorno em caso de sucesso**
```json

    {
       "id": 1,
       "name" : "ROLE_SELLER"
    }

```
### Deletar um role pelo id

```http
DELETE /api/v1/role/{id}
```

| Path Variable | Tipo   | Descrição                                             |
|:--------------------|:-------|:------------------------------------------------------|
| `id`                | `long` | **Obrigatório**. Parâmetros devem ser passados na URL |

**Retorno em caso de sucesso**
```http
204 No Content
```

**Retorno em caso de nenhuma correspondência encontrada**
```json
{
  "title": "Object not found",
  "message": "Role not found",
  "status": 404,
  "fieldErrors": null,
  "timestamp": "2022-11-21T10:36:11.55644"
}
```
### Login usuário

```http
POST /api/v1/user/login
```

| Corpo da requisição     | Tipo             | Descrição                                               |
|:------------------------|:-----------------|:--------------------------------------------------------|
| `username, password` | `string, string` | **Obrigatório**. Todos os campos no corpo da requisição |

**Formato do corpo da requisição**
```json
{
    "username":"micha@micha.com",
    "password":"aaaaaA1@"
}
```

**Retorno em caso de sucesso**
```json
{
    "access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aGF5c0BnbWFpbC5jb20iLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXBpL3YxL3VzZXIvbG9naW4iLCJpYXQiOjE2NjkxNDEwNzYsImV4cCI6MTY2OTE0MTY3Nn0.ySCEq_WcKGoVTC41jJbTj2bs1ipq3MWizqXSbFKMK8w",
    "refresh_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aGF5c0BnbWFpbC5jb20iLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXBpL3YxL3VzZXIvbG9naW4iLCJpYXQiOjE2NjkxNDEwNzYsImV4cCI6MTY2OTc0NTg3Nn0.wgXPphBMjCeSiPNcoEqAdYKbJBCZ5igYBTCI7e8Cu2c"
}
```
### Renovar token de acesso

```http
GET /api/v1/user/refresh/token
```

| Descrição                                                                                                    |
|:-------------------------------------------------------------------------------------------------------------|
| Será retornado um token de acesso válido, junto com o mesmo refresh token recebido |

| Header | Tipo   | Descrição                                             |
|:--------------------|:-------|:------------------------------------------------------|
| `Authorization`          | `Bearer` | **Obrigatório**. Parâmetros devem ser passados na URL |

**Retorno em caso de sucesso**
```json
{
    "access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aGF5c0BnbWFpbC5jb20iLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXBpL3YxL3VzZXIvbG9naW4iLCJpYXQiOjE2NjkxNDEwNzYsImV4cCI6MTY2OTE0MTY3Nn0.ySCEq_WcKGoVTC41jJbTj2bs1ipq3MWizqXSbFKMK8w",
    "refresh_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aGF5c0BnbWFpbC5jb20iLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXBpL3YxL3VzZXIvbG9naW4iLCJpYXQiOjE2NjkxNDEwNzYsImV4cCI6MTY2OTc0NTg3Nn0.wgXPphBMjCeSiPNcoEqAdYKbJBCZ5igYBTCI7e8Cu2c"
}
```

## 🖊 UML

![UML-req06](https://user-images.githubusercontent.com/114093532/203404618-0bf91106-6330-45a0-a182-5ab86215038d.svg)


## 📖 User Storie

[![User Storie](https://github.com/Wave-7-Grupo-6/Desafio-Final/blob/feature/thays/Requisito%206%20-%20Thays.pdf)]

## 📖 Swagger

http://localhost:8080/swagger-ui.html#/

## 👩🏽‍💻 Desenvolvedora

Thays Gama