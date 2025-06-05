CREATE TABLE address
(
    id   UUID PRIMARY KEY NOT NULL,
    city TEXT             NOT NULL,
    plz  TEXT             NOT NULL
);

CREATE TABLE client
(
    id         UUID PRIMARY KEY        NOT NULL,
    first_name TEXT,
    last_name  TEXT,
    company    TEXT,
    address_id UUID REFERENCES address NOT NULl
)
