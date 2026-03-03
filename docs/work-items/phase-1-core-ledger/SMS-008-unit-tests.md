# SMS-008: Unit Tests

## Metadata
| Field       | Value                              |
|-------------|------------------------------------|
| Ticket      | SMS-008                            |
| Epic        | Phase 1 — Core Ledger              |
| Status      | TO DO                              |
| Tags        | testing, unit-tests, java          |
| Priority    | Medium                             |
| Depends On  | SMS-005, SMS-006                   |
| Blocks      | —                                  |

---

## Description

Write unit tests for the service layer. Tests run without a Spring context and use mocked repositories. Fast, focused, and exhaustive on business logic branches.

### `AccountServiceTest`

| Test case                              | Expected behaviour              |
|----------------------------------------|---------------------------------|
| `createAccount` with valid inputs      | Returns saved account           |
| `getAccount` with existing ID          | Returns account                 |
| `getAccount` with non-existent ID      | Throws `AccountNotFoundException` |

### `TransactionServiceTest`

| Test case                              | Expected behaviour                         |
|----------------------------------------|--------------------------------------------|
| Valid transfer                         | Returns `SETTLED` response, balances updated |
| Duplicate `correlationId`              | Returns existing response, no second write |
| `fromAccountId` not found             | Throws `AccountNotFoundException`          |
| `toAccountId` not found               | Throws `AccountNotFoundException`          |
| `amount` is zero or negative           | Throws `InvalidTransferException`          |
| `fromAccountId` equals `toAccountId`  | Throws `InvalidTransferException`          |
| Currency mismatch                      | Throws `CurrencyMismatchException`         |
| Insufficient funds                     | Throws `InsufficientFundsException`        |

Use JUnit 5 and Mockito. No `@SpringBootTest` — instantiate service directly with mocked repositories.

---

## Definition of Done

- [ ] `AccountServiceTest.java` created with all 3 test cases passing
- [ ] `TransactionServiceTest.java` created with all 8 test cases passing
- [ ] Tests use Mockito mocks, no Spring context, no database
- [ ] `make test` passes cleanly
- [ ] Google Java Format applied
