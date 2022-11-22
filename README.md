<img src="https://github.com/thays-gama/desafio_spring/blob/main/src/main/resources/images/dh.png" alt="logotipo Digital House" width="140px" align="right">
<img src="https://github.com/thays-gama/desafio_spring/blob/main/src/main/resources/images/meli.png" alt="logotipo Mercado Livre" width="100px" align="right">

# 🍃 Desafio Final ✨- Digital House

Projeto feito no bootcamp [Java BackEnd - MercadoLivre](https://www.mercadolibre.com.ar/itacademy) com objetivo de criar sistema completo com diversos [CRUDs](https://developer.mozilla.org/pt-BR/docs/Glossary/CRUD) a partir da criação de uma API RESTful utilizando a estrutura MSC e a implementação de Testes Unitários e de Integração, para podermos solicionar a problemática apresentada.

![UML](https://user-images.githubusercontent.com/114093532/202289686-7bcfeab0-845d-4c96-abac-f9eb72be8a94.svg)

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

1. Importar o arquivo no Postman: [POSTMAN](https://github.com/Wave-7-Grupo-6/Desafio-Final/blob/favorite-products/src/main/resources/postmanCollection.json)


# 💡 Documentação da API - Requisitos 06 - Paula Santos - Lista de Favoritos.

### Criar uma lista de favoritos adicionando um produto.

```http
 POST api/v1/client/{clienteId}/favorite-list/
```

| Parâmetro   | Tipo       | Descrição                                             |
| :---------- | :--------- | :---------------------------------------------------- |
| `id`        | `Long`     | **Obrigatório**. Parâmetros devem ser passados na URL |

e

| Corpo da requisição   | Tipo       | Descrição                                               |
| :-------------------- | :--------- | :------------------------------------------------------ |
| `productId`.          |   `json`   | **Obrigatório**. Todos os campos no corpo da requisição |

**Formato do corpo da requisição**
```json
[
    {
        "productId":"2"
    }
]
```

**Retorno em caso de sucesso**

```json
{
    "id": 1,
    "name": "Williamns",
    "carts": [
        {
            "id": 1,
            "totalPrice": 132.00,
            "cartItems": [
                {
                    "id": 1,
                    "quantity": 3,
                    "value": 44.00
                }
            ]
        }
    ],
    "favorites": [
        {
            "id": 1,
            "description": "tilápia",
            "productType": {
                "id": 1,
                "type": "laranja"
            }
        },
        {
            "id": 2,
            "description": "presunto",
            "productType": {
                "id": 1,
                "type": "laranja"
            }
        }
    ]
}
```
> O retorno acima é apenas fictício.

### Deletar um produto da lista de favoritos.

```http
 DELETE api/v1/client/{clienteId}/favorite-list/
```

| Parâmetro   | Tipo       | Descrição                                             |
| :---------- | :--------- | :---------------------------------------------------- |
| `id`        | `Long`     | **Obrigatório**. Parâmetros devem ser passados na URL |

e

| Corpo da requisição   | Tipo       | Descrição                                               |
| :-------------------- | :--------- | :------------------------------------------------------ |
| `productId`.          |   `json`   | **Obrigatório**. Todos os campos no corpo da requisição |

**Formato do corpo da requisição**
```json
[
    {
        "productId":"2"
    }
]
```

**Retorno em caso de sucesso**

```json
{
    "id": 1,
    "name": "Williamns",
    "carts": [
        {
            "id": 1,
            "totalPrice": 132.00,
            "cartItems": [
                {
                    "id": 1,
                    "quantity": 3,
                    "value": 44.00
                }
            ]
        }
    ],
    "favorites": [
        {
            "id": 1,
            "description": "tilápia",
            "productType": {
                "id": 1,
                "type": "laranja"
            }
        }
    ]
}
```
> O retorno acima é apenas fictício.

--------------------------------------------------------

## Feito Com:

[![IDE](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)](https://www.jetbrains.com/idea/) 
[![IDE](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/pt-BR/) 
[![IDE](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/) 

## Devs:

[![Giovanna Almeida](https://img.shields.io/badge/Giovanna_Almeida-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/giovanna-souza-70bbb41b4/) 
[![Mauro Correia](https://img.shields.io/badge/Mauro_Correia-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/mauro-correia/) 
[![Michael Caxias](https://img.shields.io/badge/Michael_Caxias-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/michaelcaxias/) 
[![Paula Santos](https://img.shields.io/badge/Paula_Santos-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/paula-libia-santos/)
[![Thays Gama ](https://img.shields.io/badge/Thays_Gama-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/thaysgama/) 
[![Williamns Belo](https://img.shields.io/badge/Williamns_Belo-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/williamns-belo/) 


<p align="center">Copyright © 2021 Michael Caxias</p>
