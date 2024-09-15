CREATE TABLE IF NOT EXISTS clients(
    id UUID,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    phone VARCHAR(60) UNIQUE,
    address VARCHAR (255),
    is_professional BOOLEAN,

    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,

    PRIMARY KEY(id)
);
