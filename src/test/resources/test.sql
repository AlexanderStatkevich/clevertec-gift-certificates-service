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
