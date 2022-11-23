
# Desafio Final - Digital House [Mauro Correia]

Último projeto realizado na Digital House, durante o processo de bootcamp do MÉLI.
O projeto consiste na criação de uma API Rest, feito em Spring, usando a arquitetura MVC.
Conta também com testes unitários e de integração. Projeto foi realizado em grupo, aqui estarei
detalhando o último requisito do projeto, sendo esse feito individualmente.

## Como rodar o projeto.

-  Clone o projeto, e troque para minha branch.

```bash
  git clone git@github.com:Wave-7-Grupo-6/Desafio-Final.git
  git switch saleoff-batchs
```

- Abra o projeto em qualquer IDE, e execute o projeto.

```bash
  mvn spring-boot:run
```

## Arquivos úteis.

- [User Story](https://github.com/Wave-7-Grupo-6/Desafio-Final/blob/saleoff-batchs/Requisito%206%20-%20Saleoff%20Products.docx.pdf)

- [Postman Collection](https://github.com/Wave-7-Grupo-6/Desafio-Final/blob/saleoff-batchs/Saleoff%20Collection.postman_collection.json)



## Sobre o que foi desenvolvido

Na nossa aplicação, produtos que tem menos de três semanas de seu vencimento, não eram disponibilizados
para compra. Eu desenvolvi uma forma de que, quanto mais próximo do vencimento, menor é o preço do produto.
Assim, o cliente poderia optar por economizar na hora da compra. Além de disponibilizar uma forma de filtrar
os produtos de diversas formas.


## Documentação da API

### Retorna todos os produtos perto do vencimento.

```http
  GET /api/v1/saleoff
```
Retorno em caso de sucesso
```json
[
  {
    "productName": "tilápia",
    "daysToExpire": 9,
    "discountPercentage": 25,
    "original_price": 44.00,
    "sale_price": 33.0000,
    "price_difference": 11.0000
  },
  {...}
]

```

### Retorna todos os produtos perto do vencimento, filtrados por menor preço.

```http
  GET /api/v1/saleoff/smallest_price
```
Retorno em caso de sucesso
```json
[
  {
    "productName": "pão de forma",
    "daysToExpire": 9,
    "discountPercentage": 25,
    "original_price": 10.00,
    "sale_price": 7.5000,
    "price_difference": 2.5000
  },
  {...}
]

```

### Retorna todos os produtos perto do vencimento, filtrados por maior porcentagem de desconto.

```http
  GET /api/v1/saleoff/bigger_discount
```
Retorno em caso de sucesso
```json
[
  {
    "productName": "picanha",
    "daysToExpire": 3,
    "discountPercentage": 50,
    "original_price": 100.00,
    "sale_price": 50.000,
    "price_difference": 50.000
  },
  {...}
]

```

### Retorna todos os produtos perto do vencimento, filtrados por mais próximo do vencimento.

```http
  GET /api/v1/saleoff/closer_to_expire
```
Retorno em caso de sucesso
```json
[
  {
    "productName": "doritos",
    "daysToExpire": 1,
    "discountPercentage": 25,
    "original_price": 20.00,
    "sale_price": 15.000,
    "price_difference": 5.000
  },
  {...}
]

```

### Retorna todos os produtos perto do vencimento, filtrados por maior diferença de preço.

```http
  GET /api/v1/saleoff/closer_to_expire
```
Retorno em caso de sucesso
```json

{
  "productName": "picanha",
  "daysToExpire": 3,
  "discountPercentage": 50,
  "original_price": 100.00,
  "sale_price": 50.000,
  "price_difference": 50.000
},
{...}
]

```

