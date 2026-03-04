.PHONY: build run test clean up down format lint generate create-migration

DB_DIR=src/main/resources/db/migration

## Build the project
build:
	./gradlew build

## Start the Spring Boot application
run:
	set -a && . ./.env && set +a && ./gradlew bootRun

## Run all tests
test:
	./gradlew test

## Remove all build artifacts
clean:
	./gradlew clean

## Start PostgreSQL via Docker Compose
up:
	docker compose up --build -d

## Stop and remove Docker Compose containers
down:
	docker compose down

## Auto-format all source files
format:
	./gradlew spotlessApply

## Check formatting without modifying files
lint:
	./gradlew spotlessCheck

## Generate OApi
generate:
	./gradlew openApiGenerate

create-migration:
ifndef name
	$(error name is required. Usage: make migrate-create name=your_migration_name)
endif
	@LAST=$$(ls $(DB_DIR)/V*.sql 2>/dev/null | sort -V | tail -n 1 | sed 's/.*V\([0-9]*\)__.*/\1/'); \
	NEXT=$$(( $${LAST:-0} + 1 )); \
	FILE="$(DB_DIR)/V$${NEXT}__$(name).sql"; \
	touch $$FILE; \
	echo "Created $$FILE"