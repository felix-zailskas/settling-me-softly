CREATE
    TABLE
        ledger_entries(
            id BIGSERIAL PRIMARY KEY,
            transaction_id BIGINT NOT NULL REFERENCES transactions(id),
            account_id BIGINT NOT NULL REFERENCES accounts(id),
            TYPE VARCHAR(10) NOT NULL,
            amount NUMERIC(
                19,
                4
            ) NOT NULL,
            created_at TIMESTAMP NOT NULL DEFAULT NOW(),
            updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
            deleted_at TIMESTAMP
        )
