CREATE TABLE IF NOT EXISTS address
(
    id   UUID PRIMARY KEY NOT NULL,
    city TEXT             NOT NULL,
    zip  TEXT             NOT NULL
);

CREATE TABLE IF NOT EXISTS client
(
    id         UUID PRIMARY KEY        NOT NULL,
    first_name TEXT,
    last_name  TEXT,
    company    TEXT,
    email      TEXT                    NOT NULL,
    created_at TIMESTAMPTZ             NOT NULL,
    address_id UUID REFERENCES address NOT NULl
);

CREATE INDEX client_address_id_idx ON client (address_id);
