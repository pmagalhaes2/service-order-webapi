CREATE TABLE service_order (
    id SERIAL,
    opening_date DATE,
    ending_date DATE,
    notes VARCHAR(255),
    priority VARCHAR(20),
    status VARCHAR(20),
    client_id integer,
    technician_id integer,
    PRIMARY KEY (id)
)