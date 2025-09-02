# API Simples

Projeto de exemplo usando **Spring Boot**, **JPA**, **H2**, com documentação automática via Swagger.

## Pré-requisitos

- Java 21
- Maven 3.9+
- Git

## Rodando a aplicação

Clone o repositório e execute:

```bash
mvn spring-boot:run
```

Ou empacote e rode:

```bash
mvn clean package
java -jar target/api-0.0.1-SNAPSHOT.jar
```

## Documentação da API

Após rodar a aplicação, acesse:

- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- OpenAPI JSON: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## Endpoints

### Criar cliente
```http
POST /clientes
Content-Type: application/json

{
  "nome": "Ana Paula",
  "email": "ana@exemplo.com"
}
```

### Listar clientes
```http
GET /clientes
```

Resposta:
```json
[
  { "id": 1, "nome": "Ana Paula", "email": "ana@exemplo.com" },
  { "id": 2, "nome": "João", "email": "joao@exemplo.com" }
]
```

## Testes

Para rodar os testes unitários e de integração:

```bash
mvn test
```

## Postman

A coleção do Postman está disponível em:
`/postman/ApiSimples.postman_collection.json`

Importe no Postman e teste os endpoints.

## CI/CD e Qualidade de Código

- **Logs** configurados com Slf4j
- **GitHub Actions**: build e testes automáticos

