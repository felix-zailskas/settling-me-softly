# SMS-005: Account Service

## Metadata
| Field       | Value                              |
|-------------|------------------------------------|
| Ticket      | SMS-005                            |
| Epic        | Phase 1 — Core Ledger              |
| Status      | TO DO                              |
| Tags        | backend, service, java             |
| Priority    | Medium                             |
| Depends On  | SMS-002, SMS-003, SMS-004          |
| Blocks      | SMS-007                            |

---

## Description

Implement `AccountService` in the `service/` package. This service handles account creation and retrieval. It is intentionally thin — no business logic beyond persistence and lookup.

### Methods

**`createAccount(String name, AccountType type, String currency)`**
- Validates inputs are not null/blank
- Persists a new `Account` entity
- Returns the saved entity

**`getAccount(Long id)`**
- Fetches account by ID
- Throws `AccountNotFoundException` if not found
- Returns the entity

### Notes
- Annotate the class with `@Service`
- No `@Transactional` needed at this level — operations are single reads/writes
- `AccountType` should be an enum: `ASSET`, `LIABILITY`, `REVENUE`

---

## Definition of Done

- [ ] `AccountService.java` created in `service/` package
- [ ] `createAccount` persists and returns a new account
- [ ] `getAccount` throws `AccountNotFoundException` when account does not exist
- [ ] `AccountType` enum defined
- [ ] No business logic beyond persistence — transfer logic lives in `TransactionService`
- [ ] Google Java Format applied, no wildcard imports
