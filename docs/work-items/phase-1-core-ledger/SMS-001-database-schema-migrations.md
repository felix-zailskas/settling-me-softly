# SMS-001: Database Schema Migrations

## Metadata
| Field       | Value                        |
|-------------|------------------------------|
| Ticket      | SMS-001                      |
| Epic        | Phase 1 — Core Ledger        |
| Status      | TO DO                        |
| Tags        | database, flyway, schema     |
| Priority    | High                         |
| Depends On  | —                            |
| Blocks      | SMS-002, SMS-003             |

---

## Description

Update the database schema to support double-entry bookkeeping. This involves adding a `currency` field to the existing `account` table and creating two new tables: `transaction` and `ledger_entry`.

All schema changes must be applied as Flyway versioned migrations. No `ddl-auto` changes.

### Migrations to create

**V2 — Add currency to account**
Add a `currency` column (`VARCHAR(10)`, default `is there `, not null) to the existing `account` table.

**V3 — Create transaction table**
```
id             BIGSERIAL PRIMARY KEY
correlation_id VARCHAR(255) UNIQUE NOT NULL
type           VARCHAR(50) NOT NULL
status         VARCHAR(50) NOT NULL
description    VARCHAR(500)
created_at     TIMESTAMP NOT NULL DEFAULT NOW()
```

**V4 — Create ledger_entry table**
```
id             BIGSERIAL PRIMARY KEY
transaction_id BIGINT NOT NULL → FK to transaction(id)
account_id     BIGINT NOT NULL → FK to account(id)
type           VARCHAR(10) NOT NULL
amount         NUMERIC(19,4) NOT NULL
created_at     TIMESTAMP NOT NULL DEFAULT NOW()
```

---

## Definition of Done

- [ ] `V2__add_currency_to_account.sql` created and applies cleanly
- [ ] `V3__create_transaction_table.sql` created and applies cleanly
- [ ] `V4__create_ledger_entry_table.sql` created and applies cleanly
- [ ] All three migrations run successfully on a fresh database via `make up`
- [ ] Flyway schema history shows all migrations as applied
