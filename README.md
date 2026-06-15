# Spring Auth API

[![CI](https://github.com/Kauaioliveira/spring-auth-api/actions/workflows/ci.yml/badge.svg)](https://github.com/Kauaioliveira/spring-auth-api/actions/workflows/ci.yml)

API **REST** em **Java 17** e **Spring Boot 3** com validação de entrada, documentação **OpenAPI (Swagger UI)**, **Actuator** (health) e testes (slice + integração).

**Clone:** `git clone https://github.com/Kauaioliveira/spring-auth-api.git`

## Stack

- Spring Web MVC, Validation, Actuator  
- springdoc-openapi (Swagger UI)  
- JUnit 5, MockMvc (testes web e integração)
- Domínio de autenticação reutilizável (`LoginService`, repositório em memória, hash SHA-256 só para **demo**)

## Pré-requisitos

- **JDK 17+** (`java -version` deve mostrar 17 ou superior).  
  No Ubuntu, se ainda não tiver: `sudo apt install openjdk-17-jdk`

## Executar

**Recomendado (não precisa do comando `mvn` instalado no sistema):**

```bash
./mvnw spring-boot:run
```

Na primeira execução o wrapper baixa o Apache Maven para `~/.m2/wrapper` (precisa de internet).

**Alternativa**, se você instalou Maven globalmente (`sudo apt install maven`):

```bash
mvn spring-boot:run
```

- **Raiz:** `http://localhost:8080/` → redireciona para o Swagger  
- **Swagger UI:** `http://localhost:8080/swagger-ui.html`  
- OpenAPI JSON: `http://localhost:8080/api/v1/openapi`  
- Health: `http://localhost:8080/actuator/health`

> **405 Method Not Allowed** em `/api/v1/auth/login`: esse endpoint só aceita **POST** com JSON. Abrir o link na barra do navegador envia **GET**, por isso o erro. Use o Swagger (**Try it out**) ou `curl`/Postman com `POST`.

## Login (demo)

`POST /api/v1/auth/login`

```json
{
  "username": "admin",
  "password": "1234"
}
```

Resposta **200** em caso de sucesso; **401** se usuário ou senha estiverem incorretos; **400** se o JSON violar validação (campos vazios, tamanho, etc.).

### Credenciais de demonstração

| Usuário | Senha  |
|---------|--------|
| `admin` | `1234` |

> **Aviso:** fluxo sem JWT/sessão e sem Spring Security — adequado apenas para portfólio e estudo. Em produção use Spring Security, HTTPS, armazenamento seguro de senhas (Argon2/bcrypt) e política de credenciais real.

## CORS

Origens liberadas para front local: `http://localhost:3000`, `http://localhost:5173` (ajuste em `WebConfiguration` se precisar).

## Testes

```bash
./mvnw test
```

(ou `mvn test` se tiver Maven instalado.)

## Estrutura (resumo)

Pacotes Java em `com.github.kauaioliveira.coderjava` (legado do nome antigo do repo; o artefato Maven é `spring-auth-api`).

```
src/main/java/.../coderjava/
├── CoderJavaApplication.java
├── auth/              # domínio (serviço, repositório, resultado)
├── config/            # beans, OpenAPI, CORS
└── web/               # controllers, DTOs, tratamento de erros
```

## English (short)

**Spring Auth API** — Spring Boot 3 REST demo with OpenAPI UI, validation, actuator health, and tests. Demo login: `POST /api/v1/auth/login` with `admin` / `1234`.

## Licença

MIT — ver `LICENSE`.
