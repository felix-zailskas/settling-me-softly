# SMS-003: Repository Layer

## Metadata
| Field       | Value                              |
|-------------|------------------------------------|
| Ticket      | SMS-003                            |
| Epic        | Phase 1 — Core Ledger              |
| Status      | TO DO                              |
| Tags        | backend, jpa, repository, java     |
| Priority    | High                               |
| Depends On  | SMS-002                            |
| Blocks      | SMS-005, SMS-006                   |

---

## Description

Create Spring Data JPA repositories for `Transaction` and `LedgerEntry`. Update `AccountRepository` if needed.

### Repositories to create

**`TransactionRepository`**
- Extends `JpaRepository<Transaction, Long>`
- Add method: `findByCorrelationId(String correlationId)` returning `Optional<Transaction>` — required for idempotency checks

**`LedgerEntryRepository`**
- Extends `JpaRepository<LedgerEntry, Long>`
- Add method: `findByAccountIdOrderByCreatedAtDesc(Long accountId, Pageable pageable)` — required for ledger history endpoint

**`AccountRepository`** — no changes expected, verify it is sufficient.

---

## Definition of Done

- [ ] `TransactionRepository.java` created with `findByCorrelationId` method
- [ ] `LedgerEntryRepository.java` created with paginated ledger query method
- [ ] Repositories are correctly wired and application starts without error
- [ ] No wildcard imports, Google Java Format applied
