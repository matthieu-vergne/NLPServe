# NLPServe

NLP Agent service — exposes LLM features via a REST API.

## Modules

| Module | Description |
|--------|-------------|
| `nlpserve-core` | Core processing logic |
| `nlpserve-service` | REST service layer (Javalin) |

## Build

```sh
./mvnw package
```

## Run

```sh
java -jar nlpserve-service/target/nlpserve-service-1.0-SNAPSHOT.jar
```

Service listens on `http://127.0.0.1:5555`.

## Endpoints

| Method | Path | Content-Type | Request Body | Response Body |
|--------|------|-------------|--------------|---------------|
| POST   | `/agent/prompt` | `text/plain` | prompt text | response text |
| GET    | `/health` | — | — | `200 OK` |

Example:

```sh
curl -X POST http://127.0.0.1:5555/agent/prompt -d "foo"
# → bar
```

## Formatting

Google Java Format enforced via Spotless, automatically applied during the build (`process-sources` phase).

## Testing Strategy (Service Module)

We maintain two parallel test suites to compare their added value:

| Suite | Location | Framework | Approach |
|-------|----------|-----------|----------|
| Unit tests | `src/test/java` | JavalinTesttools | In-process |
| Integration tests | `src/it/java` | RestAssured + exec-maven-plugin | HTTP on the packaged JAR |

Both suites cover the same test cases. Until we decide which to retain, each new test must be replicated in both.
