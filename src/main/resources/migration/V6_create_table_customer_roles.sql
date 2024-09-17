CREATE TABLE customer_roles
(
    customer_id INT NOT NULL,
    role VARCHAR(255) NOT NULL UNIQUE

    FOREIGN KEY (customer_id) REFERENCES customer (id)
);