CREATE TABLE something_test
(
    id BINARY(16) NOT NULL,
    CONSTRAINT pk_somethingtest PRIMARY KEY (id)
);

ALTER TABLE product
    ADD inventory_count INT NULL;