
-- PRODUCTOS EXISTENTES
INSERT INTO product_master (product_id, unit_of_measure, product_name, sku, product_code, unit, cost, price, category, location, active) VALUES
                                                                                                                                             ('P001', 'Unidad', 'Notebook Lenovo', 'SKU-001', 'NB-LEN-01', 'Unidad', 350000.00, 420000.00, 'Electrónica', 'A1', TRUE),
                                                                                                                                             ('P002', 'Unidad', 'Mouse Logitech', 'SKU-002', 'MO-LOG-01', 'Unidad', 8000.00, 12000.00, 'Accesorios', 'A2', TRUE),
                                                                                                                                             ('P003', 'Unidad', 'Monitor Samsung 24', 'SKU-003', 'MN-SAM-24', 'Unidad', 70000.00, 95000.00, 'Electrónica', 'A3', TRUE);
-- MOVIMIENTOS EXISTENTES
INSERT INTO inventory_movement (movement_id, movement_date, product_id, movement_type, quantity, order_id, notes) VALUES
                                                                                                                      ('M001', '2025-05-01 09:00:00', 'P001', 'INBOUND', 10, 'PO-1001', 'Recepción de proveedor'),
                                                                                                                      ('M002', '2025-05-02 10:30:00', 'P002', 'INBOUND', 50, 'PO-1002', 'Reposición bodega'),
                                                                                                                      ('M003', '2025-05-03 14:00:00', 'P001', 'OUTBOUND', 2, 'SO-1001', 'Venta a cliente final'),
                                                                                                                      ('M004', '2025-05-04 15:00:00', 'P003', 'INBOUND', 15, 'PO-1003', 'Compra por promoción'),
                                                                                                                      ('M005', '2025-05-05 16:00:00', 'P002', 'OUTBOUND', 5, 'SO-1002', 'Salida para entrega a cliente'),
                                                                                                                      ('M006', '2025-05-06 09:30:00', 'P001', 'OUTBOUND', 1, 'SO-1003', 'Venta directa'),
                                                                                                                      ('M007', '2025-05-07 10:00:00', 'P001', 'INBOUND', 5, 'PO-1004', 'Nueva reposición'),
                                                                                                                      ('M008', '2025-05-08 14:00:00', 'P001', 'OUTBOUND', 3, 'SO-1004', 'Venta con descuento'),
                                                                                                                      ('M009', '2025-05-09 11:30:00', 'P001', 'INBOUND', 4, 'PO-1005', 'Stock técnico'),
                                                                                                                      ('M010', '2025-05-10 15:30:00', 'P001', 'OUTBOUND', 2, 'SO-1005', 'Venta minorista'),
                                                                                                                      ('M011', '2025-05-11 09:00:00', 'P001', 'INBOUND', 5, 'PO-1006', 'Nueva compra por aumento de demanda'),
                                                                                                                      ('M012', '2025-05-12 10:00:00', 'P001', 'OUTBOUND', 1, 'SO-1006', 'Venta menorista'),
                                                                                                                      ('M013', '2025-05-13 15:00:00', 'P001', 'OUTBOUND', 1, 'SO-1007', 'Envío a cliente frecuente'),
                                                                                                                      ('M014', '2025-05-14 11:00:00', 'P001', 'INBOUND', 3, 'PO-1007', 'Refuerzo stock temporal'),
                                                                                                                      ('M015', '2025-05-15 14:00:00', 'P001', 'OUTBOUND', 6, 'SO-1008', 'Descuento especial por campaña'),
                                                                                                                      ('M016', '2025-05-16 09:00:00', 'P001', 'INBOUND', 8, 'PO-1009', 'Compra masiva'),
                                                                                                                      ('M017', '2025-05-17 09:00:00', 'P001', 'OUTBOUND', 1, 'SO-1009', 'Venta menorista');

-- STOCK EXISTENTE
INSERT INTO current_stock (product_id, quantity, last_updated, total_inventory_cost) VALUES
                                                                                         ('P001', 8, '2025-05-03 14:00:00', 2800000.00),
                                                                                         ('P002', 45, '2025-05-05 16:00:00', 360000.00),
                                                                                         ('P003', 15, '2025-05-04 15:00:00', 1050000.00);

-- PREDICCIÓN EXISTENTE
INSERT INTO predictor_stock (prediction_date, product_id, predicted_quantity, predicted_sales, is_holiday, note) VALUES
                                                                                                                     ('2025-06-01', 'P001', 6, 252000.00, FALSE, 'Proyección regular'),
                                                                                                                     ('2025-06-01', 'P002', 30, 360000.00, FALSE, 'Incremento esperado por campaña'),
                                                                                                                     ('2025-06-01', 'P003', 12, 1140000.00, TRUE, 'Aumento por fecha especial');

-- ROLES
INSERT INTO roles (name) VALUES
                             ('ROLE_ADMIN'),
                             ('ROLE_USER');

-- USUARIOS
INSERT INTO users (username, password, enabled) VALUES
                                                    ('admin', '$2a$10$NA0DMvtTurvh7BUiAlkCteLr9mRofiZIbC..72CJGDMoc9pekAS1q', TRUE),
                                                    ('usuario', '$2a$10$CSznGy2zkzE88V2Qlmy9Euqpq4RXoh1zVm.GXyyZLPuEdQLBKND7i', TRUE);

INSERT INTO users_roles (user_id, role_name) VALUES
                                                 (1, 'ROLE_ADMIN'),
                                                 (2, 'ROLE_USER');
