INSERT INTO users (email, full_name, password)
VALUES ('test1@email.by', 'Test.T.T', '123'),
       ('test2@email.by', 'Test.T.R', '321'),
       ('test3@email.by', 'Test.T.G', '12321');

SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

INSERT INTO certificates(name, description, price, duration, create_date, last_update_date)
VALUES ('test cert1', 'in cinema', 10.3, 25, '20220424T000000.000 GMT', '20220424T000000.000 GMT'),
       ('test cert2', 'in park', 20.3, 35, NOW(), NOW()),
       ('test cert3', 'in smth', 30.3, 45, NOW(), NOW());

INSERT INTO tags (name)
VALUES ('test1'),
       ('test2'),
       ('test3');

INSERT INTO certificate_tags(certificate_id, tag_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);


INSERT INTO orders (timestamp_of_a_purchase, price, customer_id, certificate_id)
VALUES ('20220424T000000.000 GMT', 10.3, 1, 1),
       ('20220425T000000.000 GMT', 20.3, 2, 2),
       ('20220426T000000.000 GMT', 30.3, 3, 3),
       ('20220427T000000.000 GMT', 10.3, 3, 1);
