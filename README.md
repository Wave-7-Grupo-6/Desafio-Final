[![Typing SVG](https://readme-typing-svg.demolab.com?font=Fira+Code&duration=1000&pause=1000&color=F7F40E&width=435&lines=Desafio+Final;Conversor+de+Cambio;por+Michael+Caxias)](https://git.io/typing-svg)

<img src="https://cdn.iconscout.com/icon/free/png-256/currency-exchange-1783333-1516830.png" align="right" width="100px" />

📝 Problemática

- Um cliente do exterior gostaria de visualizar todos os produtos frescos a moeda do país dele, para isso foi necessário:

 - [x] 6.1 - Criar um endpoint para listar todos os produtos frescos convertendo a moeda
 - [x] 6.2 - Criar um endpoint para mostrar apenas um produto com os valores convertidos para o cambio especificado
 - [x] 6.3 - Criar um endpoint para mostrar todos os produtos filtrados por categoria e retornados com a moeda convertida
 - [x] 6.4 - Criar um endpoint para mostrar todos os clientes, com seus determinados produtos e o preço total deles com a moeda convertida para a especificada.
 
 ## 💻 Como iniciar o projeto

1. Faça o clone do projeto:
```shell
git clone git@github.com:Wave-7-Grupo-6/Desafio-Final.git
```

2. Vá para a branch com o requisito concluído

```shell
git checkout convert-currency
```

3. Abra o projeto na sua IDE de escolha:
```shell
mvn spring-boot:run
```

## 📚 User Story e documentação
- [Requisito 6.pdf](https://github.com/Wave-7-Grupo-6/Desafio-Final/blob/convert-currency/src/main/resources/Requisito%206%20-%20Bootcamp.docx.pdf)

## 🧑🏻‍🚀 Como testar no Postman

- Importe o seguinte arquivo no Postman: [POSTMAN](https://github.com/Wave-7-Grupo-6/Desafio-Final/blob/convert-currency/src/main/resources/Req6-COLLECTION.json)


# 💡 Documentação da API

### 🌎 Recuperar todos os produtos

```http
GET /api/v1/fresh-products?currency=USD
```

| Parâmetros  | Tipo   | Descrição                                             |
| :------------------- |:-------| :---------------------------------------------------- |
| `currency`                 | `String` | O parâmetro passado deve estar dentro desta [lista de moedas](https://economia.awesomeapi.com.br/json/all) |


*Retorno em caso de sucesso*

```json
[
    {
        "id": 1,
        "description": "tilápia",
        "batchs": [
            {
                "batchNumber": 1,
                "currentTemperature": 10.0,
                "productQuantity": 10,
                "manufacturingDate": "2022-11-09",
                "manufacturingTime": "00:00:00",
                "volume": 0.5,
                "dueDate": "2022-12-01",
                "price": 8.276400,
                "currency": "USD"
            }
        ],
        "cartItems": [
            {
                "id": 1,
                "quantity": 3,
                "value": 8.276400,
                "currency": "USD"
            }
        ],
        "seller": {
            "id": 1,
            "name": "Paula",
            "sections": [
                {
                    "id": 1,
                    "name": "FRESCO_01",
                    "volumeMax": 50.0,
                    "volumeOccupied": null,
                    "temperature": 20.0,
                    "inboundOrders": [
                        {
                            "id": 1,
                            "orderDate": "2022-11-09",
                            "orderNumber": 1234,
                            "batchs": [
                                {
                                    "batchNumber": 1,
                                    "currentTemperature": 10.0,
                                    "productQuantity": 10,
                                    "manufacturingDate": "2022-11-09",
                                    "manufacturingTime": "00:00:00",
                                    "volume": 0.5,
                                    "dueDate": "2022-12-01",
                                    "price": 8.276400,
                                    "currency": "USD"
                                }
                            ]
                        }
                    ]
                },
                {
                    "id": 2,
                    "name": "REFRIGERADO_01",
                    "volumeMax": 50.0,
                    "volumeOccupied": null,
                    "temperature": 10.0,
                    "inboundOrders": [
                        {
                            "id": 2,
                            "orderDate": "2022-11-09",
                            "orderNumber": 1235,
                            "batchs": [
                                {
                                    "batchNumber": 2,
                                    "currentTemperature": 5.0,
                                    "productQuantity": 10,
                                    "manufacturingDate": "2022-11-09",
                                    "manufacturingTime": "00:00:00",
                                    "volume": 0.5,
                                    "dueDate": "2022-12-09",
                                    "price": 1.881000,
                                    "currency": "USD"
                                }
                            ]
                        }
                    ]
                },
                {
                    "id": 3,
                    "name": "CONGELADO_01",
                    "volumeMax": 50.0,
                    "volumeOccupied": null,
                    "temperature": 0.0,
                    "inboundOrders": [
                        {
                            "id": 3,
                            "orderDate": "2022-11-09",
                            "orderNumber": 1239,
                            "batchs": [
                                {
                                    "batchNumber": 3,
                                    "currentTemperature": -5.0,
                                    "productQuantity": 10,
                                    "manufacturingDate": "2022-11-09",
                                    "manufacturingTime": "00:00:00",
                                    "volume": 0.5,
                                    "dueDate": "2023-01-10",
                                    "price": 2.426490,
                                    "currency": "USD"
                                }
                            ]
                        }
                    ]
                }
            ]
        },
        "productType": {
            "id": 1,
            "type": "laranja"
        }
    },
]
```

### 🌎 Recuperar produtos por Id

```http
GET /api/v1/fresh-products/1?currency=ARS
```

| Parâmetros  | Tipo   | Descrição                                             |
| :------------------- |:-------| :---------------------------------------------------- |
| `currency`                 | `String` | O parâmetro passado deve estar dentro desta [lista de moedas](https://economia.awesomeapi.com.br/json/all) |


*Retorno em caso de sucesso*

```json
{
    "id": 1,
    "description": "tilápia",
    "batchs": [
        {
            "batchNumber": 1,
            "currentTemperature": 10.0,
            "productQuantity": 10,
            "manufacturingDate": "2022-11-09",
            "manufacturingTime": "00:00:00",
            "volume": 0.5,
            "dueDate": "2022-12-01",
            "price": 1346.791600,
            "currency": "ARS"
        }
    ],
    "cartItems": [
        {
            "id": 1,
            "quantity": 3,
            "value": 1346.791600,
            "currency": "ARS"
        }
    ],
    "seller": {
        "id": 1,
        "name": "Paula",
        "sections": [
            {
                "id": 1,
                "name": "FRESCO_01",
                "volumeMax": 50.0,
                "volumeOccupied": null,
                "temperature": 20.0,
                "inboundOrders": [
                    {
                        "id": 1,
                        "orderDate": "2022-11-09",
                        "orderNumber": 1234,
                        "batchs": [
                            {
                                "batchNumber": 1,
                                "currentTemperature": 10.0,
                                "productQuantity": 10,
                                "manufacturingDate": "2022-11-09",
                                "manufacturingTime": "00:00:00",
                                "volume": 0.5,
                                "dueDate": "2022-12-01",
                                "price": 1346.791600,
                                "currency": "ARS"
                            }
                        ]
                    }
                ]
            },
            {
                "id": 2,
                "name": "REFRIGERADO_01",
                "volumeMax": 50.0,
                "volumeOccupied": null,
                "temperature": 10.0,
                "inboundOrders": [
                    {
                        "id": 2,
                        "orderDate": "2022-11-09",
                        "orderNumber": 1235,
                        "batchs": [
                            {
                                "batchNumber": 2,
                                "currentTemperature": 5.0,
                                "productQuantity": 10,
                                "manufacturingDate": "2022-11-09",
                                "manufacturingTime": "00:00:00",
                                "volume": 0.5,
                                "dueDate": "2022-12-09",
                                "price": 306.089000,
                                "currency": "ARS"
                            }
                        ]
                    }
                ]
            },
            {
                "id": 3,
                "name": "CONGELADO_01",
                "volumeMax": 50.0,
                "volumeOccupied": null,
                "temperature": 0.0,
                "inboundOrders": [
                    {
                        "id": 3,
                        "orderDate": "2022-11-09",
                        "orderNumber": 1239,
                        "batchs": [
                            {
                                "batchNumber": 3,
                                "currentTemperature": -5.0,
                                "productQuantity": 10,
                                "manufacturingDate": "2022-11-09",
                                "manufacturingTime": "00:00:00",
                                "volume": 0.5,
                                "dueDate": "2023-01-10",
                                "price": 394.854810,
                                "currency": "ARS"
                            }
                        ]
                    }
                ]
            }
        ]
    },
    "productType": {
        "id": 1,
        "type": "laranja"
    }
}
```

### 🌎 Recuperar produtos por Categoria

```http
GET /api/v1/fresh-products/list?currency=USD&category=REFRIGERADO
```

| Parâmetros  | Tipo   | Descrição                                             |
| :------------------- |:-------| :---------------------------------------------------- |
| `currency`, `category`                 | `String` | O parâmetro passado deve estar dentro desta [lista de moedas](https://economia.awesomeapi.com.br/json/all) |


*Retorno em caso de sucesso*

```json
[
    {
        "id": 2,
        "description": "presunto",
        "batchs": [
            {
                "batchNumber": 2,
                "currentTemperature": 5.0,
                "productQuantity": 10,
                "manufacturingDate": "2022-11-09",
                "manufacturingTime": "00:00:00",
                "volume": 0.5,
                "dueDate": "2022-12-09",
                "price": 1.867000,
                "currency": "USD"
            }
        ],
        "cartItems": [],
        "seller": {
            "id": 1,
            "name": "Paula",
            "sections": [
                {
                    "id": 1,
                    "name": "FRESCO_01",
                    "volumeMax": 50.0,
                    "volumeOccupied": null,
                    "temperature": 20.0,
                    "inboundOrders": [
                        {
                            "id": 1,
                            "orderDate": "2022-11-09",
                            "orderNumber": 1234,
                            "batchs": [
                                {
                                    "batchNumber": 1,
                                    "currentTemperature": 10.0,
                                    "productQuantity": 10,
                                    "manufacturingDate": "2022-11-09",
                                    "manufacturingTime": "00:00:00",
                                    "volume": 0.5,
                                    "dueDate": "2022-12-01",
                                    "price": 8.214800,
                                    "currency": "USD"
                                }
                            ]
                        }
                    ]
                },
                {
                    "id": 2,
                    "name": "REFRIGERADO_01",
                    "volumeMax": 50.0,
                    "volumeOccupied": null,
                    "temperature": 10.0,
                    "inboundOrders": [
                        {
                            "id": 2,
                            "orderDate": "2022-11-09",
                            "orderNumber": 1235,
                            "batchs": [
                                {
                                    "batchNumber": 2,
                                    "currentTemperature": 5.0,
                                    "productQuantity": 10,
                                    "manufacturingDate": "2022-11-09",
                                    "manufacturingTime": "00:00:00",
                                    "volume": 0.5,
                                    "dueDate": "2022-12-09",
                                    "price": 1.867000,
                                    "currency": "USD"
                                }
                            ]
                        }
                    ]
                },
                {
                    "id": 3,
                    "name": "CONGELADO_01",
                    "volumeMax": 50.0,
                    "volumeOccupied": null,
                    "temperature": 0.0,
                    "inboundOrders": [
                        {
                            "id": 3,
                            "orderDate": "2022-11-09",
                            "orderNumber": 1239,
                            "batchs": [
                                {
                                    "batchNumber": 3,
                                    "currentTemperature": -5.0,
                                    "productQuantity": 10,
                                    "manufacturingDate": "2022-11-09",
                                    "manufacturingTime": "00:00:00",
                                    "volume": 0.5,
                                    "dueDate": "2023-01-10",
                                    "price": 2.408430,
                                    "currency": "USD"
                                }
                            ]
                        }
                    ]
                }
            ]
        },
        "productType": {
            "id": 1,
            "type": "laranja"
        }
    }
]
```

### 🌎 Recuperar todos os clientes

```http
GET /api/v1/client?currency=USD
```

| Parâmetros  | Tipo   | Descrição                                             |
| :------------------- |:-------| :---------------------------------------------------- |
| `currency`                 | `String` | O parâmetro passado deve estar dentro desta [lista de moedas](https://economia.awesomeapi.com.br/json/all) |


```json
[
    {
        "id": 1,
        "name": "Williamns",
        "carts": [
            {
                "id": 1,
                "totalPrice": 24.697200,
                "cartItems": [
                    {
                        "id": 1,
                        "quantity": 3,
                        "value": 8.232400,
                        "currency": "USD"
                    }
                ]
            }
        ]
    }
]
```

