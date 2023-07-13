# zipcode-api
API para buscar qualquer cep Brasileiro :)<br>

## Stack
-> Java 11+<br>
-> MySQL<br>
-> Spring Family<br>

## Por que essa stack?
Utilizei essa Stack apenas para demonstrar o meu conhecimento em spring data, mysql.
Uma stack com Lambda e DynamoDB cairia muito bem também :)

## Exemplos para testar
###### curl http://localhost:6868/zip/03358150
*200 OK*
```JSON
{
    "zipcode": "03358150",
    "street": "Rua Ituri",
    "district": "Vila Formosa",
    "state": "SP",
    "city": "São Paulo"
}
```

*503 Service Unavailable*
```JSON
{
    "timestamp": "2023-01-16T23:27:34.962+00:00",
    "status": 503,
    "error": "Service Unavailable",
    "message": "This service is being installed, please wait a few moments.",
    "path": "/zip/03358150"
}
```

###### curl http://localhost:6868/zip/9999999
*204 No-Content*
