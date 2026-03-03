.PHONY: build run test clean up down format lint generate

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
