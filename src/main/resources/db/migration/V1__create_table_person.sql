CREATE TABLE person (
    dtype varchar(31) not null,
    id SERIAL,
    cpf VARCHAR(255),
    name VARCHAR(255),
    phone_number VARCHAR(255),
    primary key (id)
)