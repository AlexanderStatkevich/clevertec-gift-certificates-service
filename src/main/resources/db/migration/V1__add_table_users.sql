CREATE TABLE certificates
(
    id               bigserial PRIMARY KEY,
    name             varchar(255),
    description      varchar(255),
    price            numeric(19, 2),
    duration         bigint,
    create_date      timestamp(6) NOT NULL,
    last_update_date timestamp(6) NOT NULL
);

CREATE TABLE tags
(
    id   bigserial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE certificate_tags
(
    id             bigserial PRIMARY KEY,
    certificate_id serial NOT NULL REFERENCES certificates (id),
    tag_id         serial NOT NULL REFERENCES tags (id)
);

CREATE TABLE users
(
    id        bigserial PRIMARY KEY,
    email     varchar(255) UNIQUE NOT NULL,
    full_name varchar(255)        NOT NULL,
    password  varchar(255)
);

CREATE TABLE orders
(
    id                      bigserial PRIMARY KEY,
    timestamp_of_a_purchase timestamp(6)   NOT NULL,
    price                   numeric(19, 2) NOT NULL,
    customer_id             serial         NOT NULL REFERENCES users (id),
    certificate_id          serial         NOT NULL REFERENCES certificates (id)
);
