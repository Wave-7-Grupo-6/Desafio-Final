INSERT INTO user VALUES
                          (null, 1, 1, 1, 1, '123456','paula@gmail.com'),
                          (null, 1, 1, 1, 1, '123456','williams@gmail.com'),
                          (null, 1, 1, 1, 1, '123456','thays@gmail.com');

INSERT INTO seller VALUES ('Paula', 1);

INSERT INTO category VALUES
                         (null, 'FRESCO', 20),
                         (null, 'REFRIGERADO', 10),
                         (null, 'CONGELADO', 0);

INSERT INTO product_type VALUES (1, "laranja"), (2, "pera"), (3, "banana");

INSERT INTO announcement (id, description, category_id, seller_id, product_type_id) VALUES
                             (null, 'tilápia', 1, 1, 1),
                             (null, 'presunto', 2, 1, 1),
                             (null, 'lasanha', 3, 1, 3);

INSERT INTO warehouse VALUES (null, 'Armazem Meli SP');

INSERT INTO section VALUES
                        (null, 'FRESCO_01', 20, 50, 1, 1, 1),
                        (null, 'REFRIGERADO_01', 10, 50, 2, 1, 1),
                        (null, 'CONGELADO_01', 0, 50, 3, 1, 1);

INSERT INTO client VALUES
                       ('Williamns', 2);

INSERT INTO inbound_order VALUES (1,'2022-11-09', 1234, 1);
INSERT INTO inbound_order VALUES (2,'2022-11-09', 1235, 2);
INSERT INTO inbound_order VALUES (3,'2022-11-09', 1239, 3);

INSERT INTO batch (batch_number, current_temperature, due_date, manufacturing_date, manufacturing_time, product_quantity, volume, annoucement_id, inbound_order, section_id, price) VALUES
                      (1, 10, '2022-12-01', '2022-11-09', '00:00:00', 10, 0.5, 1, 1, 1, 44),
                      (2, 5, '2022-12-09', '2022-11-09', '00:00:00', 10, 0.5, 2, 2, 2, 10.0),
                      (3, -5, '2023-01-10', '2022-11-09', '00:00:00', 10, 0.5, 3, 3, 3, 12.9);

INSERT INTO cart VALUES (1, 1);

INSERT INTO cart_item VALUES
    (null, 3, 44.0, 1, 1);

INSERT INTO purchase_order VALUES (1, '2022-11-09', 'DELIVERED', 1);

INSERT INTO purchase_item VALUES
                        (null, 44.0, 1, 1, 1),
                        (null, 10.0, 2, 2, 1),
                        (null, 12.9, 1, 3, 1);


INSERT INTO role VALUES
                              (null, 'ROLE_ADMIN'),
                              (null, 'ROLE_CLIENT'),
                              (null, 'ROLE_SELLER');

INSERT INTO user_role VALUES
                     (1, 3),
                     (2, 2),
                     (3, 1);