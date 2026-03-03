# settling-me-softly

A high-throughput financial settlement and audit engine built with Spring Boot and PostgreSQL.

## Prerequisites

- Java 21
- Docker

## Getting Started

1. Copy the environment template and adjust if needed:

   ```bash
   cp .env.example .env
   ```

2. Start the full stack (DB + API) with one command:

   ```bash
   make up
   ```

3. Verify it's running:

   ```bash
   curl http://localhost:8080/actuator/health
   ```

## Development

| Command        | Description                          |
|----------------|--------------------------------------|
| `make run`     | Start the API locally (needs `.env`) |
| `make build`   | Build the project                    |
| `make test`    | Run tests                            |
| `make format`  | Auto-format all source files         |
| `make lint`    | Check formatting                     |
| `make up`      | Build and start all services         |
| `make down`    | Stop all services                    |
| `make clean`   | Remove build artifacts               |
