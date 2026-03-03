# SMS-007: OpenAPI Spec Update & Controllers

## Metadata
| Field       | Value                                    |
|-------------|------------------------------------------|
| Ticket      | SMS-007                                  |
| Epic        | Phase 1 — Core Ledger                    |
| Status      | TO DO                                    |
| Tags        | backend, api, openapi, controllers       |
| Priority    | Medium                                   |
| Depends On  | SMS-005, SMS-006                         |
| Blocks      | SMS-008                                  |

---

## Description

Update the OpenAPI specification (`src/main/resources/openapi/api.yaml`) to include all Phase 1 endpoints, regenerate the interfaces and models, then update controllers to implement the generated interfaces.

### OpenAPI spec changes

**Add to existing schemas:**
- `currency` field to `CreateAccountRequest` and `AccountResponse`

**Add new path: `POST /api/v1/transactions/transfer`**
- Request body: `TransferRequest` schema (correlationId, fromAccountId, toAccountId, amount, currency, description)
- Response 201: `TransferResponse` schema (transactionId, correlationId, status, entries[], createdAt)
- Response 200: same `TransferResponse` (returned on duplicate correlationId)
- Response 404: account not found
- Response 422: insufficient funds or currency mismatch
- Response 400: invalid transfer

**Add new path: `GET /api/v1/accounts/{id}/ledger`**
- Path param: `id`
- Query params: `page` (default 0), `size` (default 20)
- Response 200: paginated list of `LedgerEntryResponse` (id, accountId, type, amount, createdAt)
- Response 404: account not found

### Controller changes

**`AccountController`** — implement new generated interface (no logic change, just spec-driven update)

**`TransactionController`** — new controller implementing the generated `TransactionsApi` interface
- `POST /transfer` → delegates to `TransactionService.executeTransfer()`
- `GET /accounts/{id}/ledger` → delegates to `TransactionService.getLedgerHistory()`

Controllers must not contain business logic — pure delegation to services.

---

## Definition of Done

- [ ] `api.yaml` updated with all new paths and schemas
- [ ] `make generate` runs without errors
- [ ] `AccountController` updated to implement new generated interface
- [ ] `TransactionController` created implementing `TransactionsApi`
- [ ] All endpoints return correct HTTP status codes as per spec
- [ ] No business logic in controllers
- [ ] Google Java Format applied, no wildcard imports
