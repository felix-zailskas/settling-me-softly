# SMS-004: Exception Hierarchy & Global Error Handler

## Metadata
| Field       | Value                                        |
|-------------|----------------------------------------------|
| Ticket      | SMS-004                                      |
| Epic        | Phase 1 — Core Ledger                        |
| Status      | TO DO                                        |
| Tags        | backend, error-handling, java                |
| Priority    | High                                         |
| Depends On  | —                                            |
| Blocks      | SMS-005, SMS-006                             |

---

## Description

Create a centralised error handling system. All business exceptions bubble up from the service layer and are caught by a single `@RestControllerAdvice`. Controllers and services contain no try/catch blocks.

### Exceptions to create (in `exception/` package)

| Class                         | HTTP Status | Error Code           |
|-------------------------------|-------------|----------------------|
| `AccountNotFoundException`    | 404         | `ACCOUNT_NOT_FOUND`  |
| `InsufficientFundsException`  | 422         | `INSUFFICIENT_FUNDS` |
| `CurrencyMismatchException`   | 422         | `CURRENCY_MISMATCH`  |
| `InvalidTransferException`    | 400         | `INVALID_TRANSFER`   |

All exceptions extend `RuntimeException` and accept a descriptive message.

### `GlobalExceptionHandler`

Annotated with `@RestControllerAdvice`. Maps each exception to a consistent JSON error response:

```json
{
  "status": 422,
  "error": "INSUFFICIENT_FUNDS",
  "message": "Account 1 has insufficient funds for this transfer",
  "timestamp": "2026-03-03T01:22:00Z"
}
```

Duplicate `correlationId` is not an exception — it is handled in the service layer and returns HTTP `200` with the original response.

---

## Definition of Done

- [ ] All four exception classes created in `exception/` package
- [ ] `GlobalExceptionHandler.java` created with `@RestControllerAdvice`
- [ ] Each exception correctly maps to the right HTTP status and error code
- [ ] Error response shape is consistent across all exceptions
- [ ] No try/catch in any controller or service class
- [ ] Google Java Format applied
