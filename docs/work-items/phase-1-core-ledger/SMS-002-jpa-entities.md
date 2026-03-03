# SMS-002: JPA Entities — Transaction & LedgerEntry

## Metadata
| Field       | Value                              |
|-------------|------------------------------------|
| Ticket      | SMS-002                            |
| Epic        | Phase 1 — Core Ledger              |
| Status      | TO DO                              |
| Tags        | backend, jpa, entities, java       |
| Priority    | High                               |
| Depends On  | SMS-001                            |
| Blocks      | SMS-005, SMS-006                   |

---

## Description

Create JPA entity classes for `Transaction` and `LedgerEntry`, and update the existing `Account` entity to include the `currency` field.

All entities must follow existing project conventions: no Lombok, no wildcard imports, Google Java Format, `BigDecimal` for financial amounts.

### Account — update
Add `currency` field (`String`, not null) with column annotation matching the migration.

### Transaction — new entity
Fields: `id`, `correlationId` (unique), `type` (enum `TransactionType`), `status` (enum `TransactionStatus`), `description`, `createdAt`.

Define enums:
- `TransactionType`: `TRANSFER`, `FEE`, `REVERSAL`
- `TransactionStatus`: `PENDING`, `SETTLED`, `FAILED`

### LedgerEntry — new entity
Fields: `id`, `transaction` (ManyToOne), `account` (ManyToOne), `type` (enum `EntryType`), `amount` (BigDecimal), `createdAt`.

Define enum:
- `EntryType`: `DEBIT`, `CREDIT`

---

## Definition of Done

- [ ] `Account.java` updated with `currency` field
- [ ] `Transaction.java` created with all fields and enums
- [ ] `LedgerEntry.java` created with all fields and enums
- [ ] Enums `TransactionType`, `TransactionStatus`, `EntryType` defined
- [ ] App starts and Flyway `validate` passes
- [ ] No Lombok, no wildcard imports, Google Java Format applied
