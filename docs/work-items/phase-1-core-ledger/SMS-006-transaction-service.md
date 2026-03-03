# SMS-006: Transaction Service

## Metadata
| Field       | Value                                    |
|-------------|------------------------------------------|
| Ticket      | SMS-006                                  |
| Epic        | Phase 1 — Core Ledger                    |
| Status      | TO DO                                    |
| Tags        | backend, service, java, transactions     |
| Priority    | High                                     |
| Depends On  | SMS-002, SMS-003, SMS-004                |
| Blocks      | SMS-007                                  |

---

## Description

Implement `TransactionService` — the core business logic of the system. All methods are `@Transactional`. This service is responsible for executing transfers with ACID guarantees, enforcing double-entry bookkeeping invariants, and handling idempotency.

### Method: `executeTransfer(TransferRequest request)`

Define `TransferRequest` as a Java `record`:
```java
record TransferRequest(
    String correlationId,
    Long fromAccountId,
    Long toAccountId,
    BigDecimal amount,
    String currency,
    String description
) {}
```

**Transfer flow (must be atomic):**
1. Query `TransactionRepository.findByCorrelationId(correlationId)` — if found, map to response and return immediately (idempotency)
2. Load `fromAccount` and `toAccount` — throw `AccountNotFoundException` if either missing
3. Validate:
   - `amount` must be positive → `InvalidTransferException`
   - `fromAccountId` ≠ `toAccountId` → `InvalidTransferException`
   - `fromAccount.currency` matches `request.currency` → `CurrencyMismatchException`
   - `fromAccount.balance >= amount` → `InsufficientFundsException`
4. Create `Transaction` with status `PENDING`
5. Create `LedgerEntry` DEBIT for `fromAccount`
6. Create `LedgerEntry` CREDIT for `toAccount`
7. Deduct `amount` from `fromAccount.balance`
8. Add `amount` to `toAccount.balance`
9. Update `Transaction.status` to `SETTLED`
10. Return `TransferResponse`

Any exception in steps 3–9 triggers full rollback via `@Transactional`.

### Method: `getLedgerHistory(Long accountId, Pageable pageable)`
- Verifies account exists (throws `AccountNotFoundException` if not)
- Returns paginated `LedgerEntry` records for the account ordered by `createdAt DESC`

---

## Definition of Done

- [ ] `TransactionService.java` created with `@Transactional` on all mutating methods
- [ ] `TransferRequest` record defined
- [ ] All 8 transfer flow steps implemented in order
- [ ] Idempotency: duplicate `correlationId` returns existing response without a second write
- [ ] All validation rules enforced with correct exceptions
- [ ] Double-entry invariant holds: every transfer creates exactly one DEBIT and one CREDIT
- [ ] `getLedgerHistory` returns paginated results ordered by `createdAt DESC`
- [ ] No try/catch blocks — exceptions propagate to `GlobalExceptionHandler`
- [ ] Google Java Format applied, no wildcard imports
