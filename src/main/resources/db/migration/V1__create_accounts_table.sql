CREATE
    TABLE
        accounts(
            id BIGSERIAL PRIMARY KEY,
            name VARCHAR(255) NOT NULL,
            TYPE VARCHAR(255) NOT NULL,
            balance NUMERIC(
                19,
                4
            ) NOT NULL DEFAULT 0,
            version INTEGER NOT NULL DEFAULT 0,
            created_at TIMESTAMP NOT NULL DEFAULT NOW(),
            updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
            deleted_at TIMESTAMP
        )
