ALTER TABLE service_order
    DROP client_id;

ALTER TABLE service_order
    ADD client_id INTEGER;

ALTER TABLE service_order
    ADD FOREIGN KEY(client_id) REFERENCES person(id);

ALTER TABLE service_order
    DROP technician_id;

ALTER TABLE service_order
    ADD technician_id INTEGER;

ALTER TABLE service_order
    ADD FOREIGN KEY(technician_id) REFERENCES person(id);

