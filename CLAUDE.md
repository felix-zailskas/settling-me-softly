# CLAUDE.md

## Project Overview

settling-me-softly — a high-throughput financial settlement and audit engine built with Spring Boot 4.0.3 and PostgreSQL.
Showcase project demonstrating production-grade Java 21 engineering.

## Tech Stack

- Java 21 (LTS), Spring Boot 4.0.3, Spring Data JPA, Flyway, PostgreSQL 17
- Gradle (Kotlin DSL) with Gradle Wrapper
- API-first design: OpenAPI spec generates controller interfaces and DTOs
- Testcontainers for integration tests
- Docker Compose for full-stack local/deployment setup

## Key Commands

- `make run` — start API locally (requires `.env`)
- `make build` — build the project
- `make test` — run tests
- `make format` — auto-format all source files
- `make lint` — check formatting
- `make generate` — generate API code from OpenAPI spec
- `make up` — build and start all services via Docker
- `make down` — stop all services

## Project Conventions

- API-first: define endpoints in `src/main/resources/openapi/api.yaml`, then generate. Controllers implement generated
  interfaces.
- Generated code lives in `build/generated/` — never committed, never hand-edited.
- No wildcard imports. Star threshold set to 999.
- Google Java Format enforced via Spotless.
- Financial amounts use `BigDecimal` / `NUMERIC(19,4)` — never float/double.
- No Lombok. Use Java records for immutable DTOs where possible.
- Secrets in `.env` (not committed). App fails fast if env vars are missing.
- Flyway for all schema changes — no `ddl-auto: update`.
- Database migrations in `src/main/resources/db/migration/V{n}__description.sql`.

## Interaction Mode

- The user drives implementation. Claude provides guidance, suggestions, and commands — not direct code changes unless
  explicitly asked.
- Keep suggestions professional/senior-level. This is a portfolio showcase project.
