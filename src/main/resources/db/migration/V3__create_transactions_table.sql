CREATE
    TABLE
        transactions(
            id BIGSERIAL PRIMARY KEY,
            correlation_id VARCHAR(255) UNIQUE NOT NULL,
            TYPE VARCHAR(50) NOT NULL,
            status VARCHAR(50) NOT NULL,
            description VARCHAR(500),
            created_at TIMESTAMP NOT NULL DEFAULT NOW(),
            updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
            deleted_at TIMESTAMP
        )