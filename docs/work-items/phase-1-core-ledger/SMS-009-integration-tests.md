# SMS-009: Integration Tests

## Metadata
| Field       | Value                                        |
|-------------|----------------------------------------------|
| Ticket      | SMS-009                                      |
| Epic        | Phase 1 — Core Ledger                        |
| Status      | TO DO                                        |
| Tags        | testing, integration-tests, testcontainers   |
| Priority    | Medium                                       |
| Depends On  | SMS-007, SMS-008                             |
| Blocks      | —                                            |

---

## Description

Write integration tests that exercise the full stack — HTTP request through controller, service, repository, and real PostgreSQL via Testcontainers. These tests verify the system works end-to-end against real infrastructure.

### `AccountIntegrationTest`

| Test case                          | Expected behaviour                          |
|------------------------------------|---------------------------------------------|
| `POST /api/v1/accounts`            | Returns 201, account persisted in DB        |
| `GET /api/v1/accounts/{id}`        | Returns 200 with correct account data       |
| `GET /api/v1/accounts/{id}` (missing) | Returns 404 with error body              |

### `TransactionIntegrationTest`

| Test case                          | Expected behaviour                                      |
|------------------------------------|---------------------------------------------------------|
| Valid `POST /transfer`             | Returns 201, both balances updated in DB, two ledger entries created |
| `GET /accounts/{id}/ledger`        | Returns paginated ledger entries for account            |

### `IdempotencyIntegrationTest`

| Test case                          | Expected behaviour                                      |
|------------------------------------|---------------------------------------------------------|
| Same `correlationId` posted twice  | Second request returns 200, only one transaction in DB  |

All integration tests use `@SpringBootTest` and `@Import(TestcontainersConfig.class)`. Tests are isolated — each test starts with a clean database state using `@Transactional` or explicit teardown.

---

## Definition of Done

- [ ] `AccountIntegrationTest.java` created with all 3 test cases passing
- [ ] `TransactionIntegrationTest.java` created with all 2 test cases passing
- [ ] `IdempotencyIntegrationTest.java` created with idempotency test passing
- [ ] Tests use real PostgreSQL via Testcontainers (no H2, no mocks for DB)
- [ ] DB state is clean between tests
- [ ] `make test` passes cleanly with Docker running
- [ ] Google Java Format applied
