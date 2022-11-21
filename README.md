<img src="https://github.com/thays-gama/desafio_spring/blob/main/src/main/resources/images/dh.png" alt="logotipo Digital House" width="140px" align="right">
<img src="https://github.com/thays-gama/desafio_spring/blob/main/src/main/resources/images/meli.png" alt="logotipo Mercado Livre" width="100px" align="right">

# 🍃 Desafio Final - Requisito 06 - Giovanna de Souza 🧪 - DigitalHouse

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

Um Cliente gostaria de poder aplicar um cupom de desconto para que sua compra saia um pouco mais parada, por esta razão, iremos:

- [x] 6.1 - Criar um endpoint para criar um novo cupom de desconto.
- [x] 6.2 - Criar um endpoint para listar todos os cupons de desconto.
- [x] 6.3 - Criar um endpoint para recuperar um cupom de desconto pelo seu código.
- [x] 6.4 - Criar um endpoint para recuperar todos os cupons que possuem determinado desconto.
- [x] 6.5 - Criar um endpoint para recuperar todos os cupons que estão atrelados a uma determinada categoria.
- [x] 6.6 - Criar um endpoint para atualizar um cupom de desconto.
- [x] 6.7 - Criar um endpoint para deletar um cupom de desconto.
- [x] 6.8 - Criar um endpoint para aplicar um cupom de desconto em uma compra.

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

### 🎫 Cadastro de um novo cupom de desconto

```http
POST /api/v1/discount-coupons
```

| Corpo da requisição     | Tipo             | Descrição                                               |
|:------------------------|:-----------------|:--------------------------------------------------------|
| `description, discount` | `string, double` | **Obrigatório**. Todos os campos no corpo da requisição |

**Formato do corpo da requisição**
```json
{
    "description": "Cupom de desconto para compras acima de R$ 100,00",
    "discount": 10.0
}
```

**Retorno em caso de sucesso**
```json
{
    "id": 1,
    "status" : 1,
    "discount": 10.0,
    "description": "Cupom de desconto para compras acima de R$ 100,00"
}
```

### 🎫 Listar todos os cupons de desconto

```http
GET /api/v1/discount-coupons
```

| Descrição                                                           |
|:--------------------------------------------------------------------|
| Será retornado um array com todos os cupons de desconto cadastrados |

**Retorno em caso de sucesso**
```json
[
    {
        "id": 1,
        "status" : 1,
        "discount": 10.0,
        "description": "Cupom de desconto para compras acima de R$ 100,00"
    },
    {
        "id": 2,
        "status" : 1,
        "discount": 20.0,
        "description": "Cupom de desconto para compras acima de R$ 200,00"
    }
]
```

### 🎫 Listar um cupom de desconto pelo id

```http
GET /api/v1/discount-coupons/{id}
```

| Descrição                                                                                  |
|:-------------------------------------------------------------------------------------------|
| Será retornado um cupom de desconto cujo id tenha alguma correspondência no banco de dados |

| Corpo da requisição  | Tipo   | Descrição                                             |
| :------------------- |:-------| :---------------------------------------------------- |
| `id`                 | `long` | **Obrigatório**. Parâmetros devem ser passados na URL |

**Retorno em caso de sucesso**
```json
{
    "id": 1,
    "status" : 1,
    "discount": 10.0,
    "description": "Cupom de desconto para compras acima de R$ 100,00"
}
```

**Retorno em caso de erro**
```json
{
    "title": "Object not found",
    "message": "Discount Coupon not found",
    "status": 404,
    "fieldErrors": null,
    "timestamp": "2022-11-21T10:17:57.351544"
}
```

### 🎫 Listar uma lista de cupons de desconto pelo desconto

```http
GET /api/v1/discount-coupons/discount/{discount}
```

| Descrição                                                                                                   |
|:------------------------------------------------------------------------------------------------------------|
| Será retornado uma lista de cupons de desconto cujo desconto tenha alguma correspondência no banco de dados |

| Corpo da requisição | Tipo     | Descrição                                             |
|:--------------------|:---------|:------------------------------------------------------|
| `discount`          | `double` | **Obrigatório**. Parâmetros devem ser passados na URL |

**Retorno em caso de sucesso**
```json
[
    {
        "id": 1,
        "status" : 1,
        "discount": 10.0,
        "description": "Cupom de desconto para compras acima de R$ 100,00"
    },
    {
        "id": 2,
        "status" : 1,
        "discount": 10.0,
        "description": "Cupom de desconto para compras acima de R$ 200,00"
    }
]
```

**Retorno em caso de nenhuma correspondência encontrada**
```json
[]
```

### 🎫 Listar uma lista de cupons de desconto pela categoria

```http
GET /api/v1/discount-coupons/category/{category}
```

| Descrição                                                                                                    |
|:-------------------------------------------------------------------------------------------------------------|
| Será retornado uma lista de cupons de desconto cuja categoria tenha alguma correspondência no banco de dados |

| Corpo da requisição | Tipo   | Descrição                                             |
|:--------------------|:-------|:------------------------------------------------------|
| `category`          | `long` | **Obrigatório**. Parâmetros devem ser passados na URL |

**Retorno em caso de sucesso**
```json
[
    {
        "id": 1,
        "status" : 1,
        "discount": 10.0,
        "description": "Cupom de desconto para compras acima de R$ 100,00"
    }
]
```

**Retorno em caso de nenhuma correspondência encontrada**
```json
[]
```

**Retorno em caso de categoria não encontrada**
```json
{
    "title": "Object not found",
    "message": "Category not found",
    "status": 404,
    "fieldErrors": null,
    "timestamp": "2022-11-21T10:17:57.351544"
}
```

### 🎫 Atualização de um cupom de desconto

```http
PUT /api/v1/discount-coupons/{id}
```

| Parâmetro   | Tipo   | Descrição                                             |
| :---------- |:-------| :---------------------------------------------------- |
| `id`        | `long` | **Obrigatório**. Parâmetros devem ser passados na URL |

| Corpo da requisição     | Tipo             | Descrição                                               |
|:------------------------|:-----------------|:--------------------------------------------------------|
| `description, discount` | `string, double` | **Obrigatório**. Todos os campos no corpo da requisição |

**Formato do corpo da requisição**
```json
{
    "description": "Cupom de desconto para compras acima de R$ 100,00",
    "discount": 15.0
}
```

**Retorno em caso de sucesso**
```json
{
    "id": 1,
    "status" : 1,
    "discount": 15.0,
    "description": "Cupom de desconto para compras acima de R$ 100,00"
}
```

**Retorno em caso de erro**
```json
{
    "title": "Object not found",
    "message": "Discount Coupon not found",
    "status": 404,
    "fieldErrors": null,
    "timestamp": "2022-11-21T10:17:57.351544"
}
```

### 🎫 Deletar um cupom de desconto pelo id

```http
DELETE /api/v1/discount-coupons/{id}
```

| Corpo da requisição | Tipo   | Descrição                                             |
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
  "message": "Discount Coupon not found",
  "status": 404,
  "fieldErrors": null,
  "timestamp": "2022-11-21T10:36:11.55644"
}
```

**Retorno em caso de operação inválida (Quando um cupom já foi utilizado pelo menos 1 vez)**
```json
{
	"title": "Data integrity violation",
	"message": "One or more params are invalids",
	"status": 502,
	"fieldErrors": null,
	"timestamp": "2022-11-21T10:36:44.603478"
}
```

### 🎫 Cadastro de um novo cupom de desconto

```http
POST /api/v1/purchase-order/
```

| Corpo da requisição                                      | Tipo                                | Descrição                                               |
|:---------------------------------------------------------|:------------------------------------|:--------------------------------------------------------|
| `buyerId, date, discountCouponId, orderStatus, products` | `long, date, long, String, Product` | **Obrigatório**. Todos os campos no corpo da requisição |

**Formato do corpo da requisição**
```json
{
  "buyerId": 1,
  "date": "2022-12-12",
  "discountCouponId": 1,
  "orderStatus": "PROCESSING",
  "products": [
    {
      "batchId": 1,
      "productId": 1,
      "quantity": 2
    }
  ]
}
```

**Retorno em caso de sucesso**
```json
73.00
```

**Retorno em caso de Cliente não encontrado**
```json
{
    "title": "Object not found",
    "message": "Client not found",
    "status": 404,
    "fieldErrors": null,
    "timestamp": "2022-11-21T10:53:26.747692"
}
```

**Retorno em caso de Cupom de desconto não encontrado**
```json
{
    "title": "Object not found",
    "message": "Discount Coupon not found",
    "status": 404,
    "fieldErrors": null,
    "timestamp": "2022-11-21T10:53:26.747692"
}
```

**Retorno em caso de Estoque não encontrado**
```json
{
    "title": "Object not found",
    "message": "BatchStock not found.",
    "status": 404,
    "fieldErrors": null,
    "timestamp": "2022-11-21T10:54:32.590391"
}
```

**Retorno em caso de Status de pedido inválido**
```json
{
    "title": "Params invalids",
    "message": "One or more params are invalids",
    "status": 400,
    "fieldErrors": {
        "orderStatus": "Must be any of enum OrderStatus [CANCELLED, DELIVERED, IN_TRANSIT, PAYMENT_DUE, PICK_UP_AVAILABLE, PROBLEM, PROCESSING, RETURNED]"
    },
    "timestamp": "2022-11-21T10:55:13.786739"
}
```

**Retorno em caso de Anúncio não encontrado**
```json
{
    "title": "Object not found",
    "message": "Announcement not found.",
    "status": 404,
    "fieldErrors": null,
    "timestamp": "2022-11-21T10:57:04.830174"
}
```

**Retorno em caso de Estoque insuficiente**
```json
{
    "title": "Object out of stock",
    "message": "There is not enough stock in this batch",
    "status": 400,
    "fieldErrors": null,
    "timestamp": "2022-11-21T10:57:57.220358"
}
```

## 🖊 Diagrama Entidade Relacionamento (DER)

[![DER](https://github.com/Wave-7-Grupo-6/Desafio-Final/blob/discount-coupon/src/main/resources/images/DER.jpg)](https://github.com/Wave-7-Grupo-6/Desafio-Final/blob/discount-coupon/src/main/resources/images/DER.jpg)

## 👩🏽‍💻 Desenvolvedora

Olá, meu nome é Giovanna de Souza, tenho 19 anos, sou paulistana de nascença e mineira de coração, meu início na programação começou aos meus 14 anos, durante o ensino médio que fiz em uma escola técnica aqui de São Paulo.

Desde então tenho procurado aprender mais e mais sobre programação, me sinto lisongeada por poder fazer parte dessa empresa maravilhosa que é o Mercado Livre.

<div>
    <img src="https://github.com/Wave-7-Grupo-6/Desafio-Final/blob/discount-coupon/src/main/resources/images/gi.png" alt="Imagem Gi" width="200" align="right" style="margin-top: 5%" />
    <img src="https://github.com/Wave-7-Grupo-6/Desafio-Final/blob/discount-coupon/src/main/resources/images/gi-foto.png" alt="Imagem Gi anime" width="200" align="left"/>
</div>

