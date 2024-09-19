-- Enum types for project_status and component_type
CREATE TYPE project_status AS ENUM (
    'PENDING',
    'COMPLETED',
    'CANCELLED'
);

CREATE TYPE component_type AS ENUM ('MATERIAL', 'WORKER');

-- Create clients table
CREATE TABLE IF NOT EXISTS clients (
    id UUID PRIMARY KEY,
    first_name VARCHAR (40) NOT NULL,
    last_name VARCHAR (40) NOT NULL,
    phone VARCHAR (255),
    address VARCHAR (255),
    is_professional BOOLEAN,
    created_at TIMESTAMP DEFAULT now (),
    updated_at TIMESTAMP DEFAULT now (),
    deleted_at TIMESTAMP
);

-- Create projects table
CREATE TABLE IF NOT EXISTS projects (
    id UUID PRIMARY KEY,
    client_id UUID NOT NULL,
    name VARCHAR (255) UNIQUE NOT NULL,
    profit_margin REAL,
    tva REAL,
    surface REAL NOT NULL,
    total_cost DECIMAL (18, 2),
    project_status project_status DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT now (),
    updated_at TIMESTAMP DEFAULT now (),
    deleted_at TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES clients (id) ON DELETE CASCADE
);

-- Create components table
CREATE TABLE IF NOT EXISTS components (
    id UUID PRIMARY KEY,
    project_id UUID NOT NULL,
    name VARCHAR (255) NOT NULL,
    tva REAL NOT NULL DEFAULT 0.0,
    component_type component_type NOT NULL,
    created_at TIMESTAMP DEFAULT now (),
    updated_at TIMESTAMP DEFAULT now (),
    deleted_at TIMESTAMP,
    FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE
);

-- Create materiels table, inheriting from components
CREATE TABLE IF NOT EXISTS materiels (
    unit_cost DECIMAL (18, 2) NOT NULL,
    quantity DECIMAL (10, 2) NOT NULL,
    transport_cost DECIMAL (18, 2) NOT NULL,
    quantity_coefficient REAL NOT NULL DEFAULT 1.0
) INHERITS (components);

-- Create workers table, inheriting from components
CREATE TABLE IF NOT EXISTS workers (
    hourly_rate DECIMAL (10, 2) NOT NULL,
    working_hours DECIMAL (10, 2) NOT NULL,
    productivity REAL NOT NULL DEFAULT 1.0
) INHERITS (components);

CREATE TABLE IF NOT EXISTS quotes (
    id UUID PRIMARY KEY,
    project_id UUID,
    issue_date DATE,
    validity_date DATE,
    accepted BOOLEAN,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    deleted_at TIMESTAMP,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);

CREATE
OR REPLACE FUNCTION update_timestamp_column() RETURNS TRIGGER AS $ $ BEGIN NEW.updated_at = NOW();

RETURN NEW;

END;

$ $ LANGUAGE plpgsql;

-- Trigger to update the updated_at column in the projects table
CREATE TRIGGER update_projects_timestamp BEFORE
UPDATE
    ON projects FOR EACH ROW EXECUTE FUNCTION update_timestamp_column();

-- Trigger to update the updated_at column in the components table
CREATE TRIGGER update_components_timestamp BEFORE
UPDATE
    ON components FOR EACH ROW EXECUTE FUNCTION update_timestamp_column();

CREATE TRIGGER update_quotes_timestamp BEFORE
    UPDATE
    ON quotes FOR EACH ROW EXECUTE FUNCTION update_timestamp_column();