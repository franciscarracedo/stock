-- Product Master Data
INSERT INTO product_master (product_id, product_name, product_code, unit, cost, price, category, location, active)
VALUES ('P001', 'Dell XPS 13', 'DELL-XPS13', 'Unit', 1200, 1500, 'Electronics', 'A1-01', TRUE);

INSERT INTO product_master (product_id, product_name, product_code, unit, cost, price, category, location, active)
VALUES ('P002', 'LG UltraWide', 'LG-UW34', 'Unit', 350, 450, 'Electronics', 'A1-02', TRUE);


-- Inventory Movement Data
INSERT INTO inventory_movement VALUES
    ('M001', '2024-05-20 09:00:00', 'P001', 'INBOUND', 10, 'PO-1001', 'Supplier reception');

-- Current Stock Data
INSERT INTO current_stock VALUES
    ('P001', 8, '2024-05-21 13:00:00', 9600);

-- Predictor Stock Data
INSERT INTO predictor_stock VALUES
    ('2024-01-01', 'P001', 5, 1500, false, 'New Year''s Day');

INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

-- Password: "user123" (hasheada con BCrypt)
INSERT INTO users (username, password, enabled)
VALUES ('usuario', '$2a$10$ZxJXqOqYpB9/3SguP3G9bOClFGZW/kKnbVBRKhDxvTh0mJxZEC3gy', true);

-- Password: "admin123" (hasheada con BCrypt)
INSERT INTO users (username, password, enabled)
VALUES ('admin', '$2a$10$G9USnxeAg5uEjMtuCOe6kugESI2UexZnYz3ROFg9kN7ZDUKFOEBeW', true);

-- Asignación de roles
INSERT INTO users_roles (user_id, role_name) VALUES (1, 'ROLE_USER');
INSERT INTO users_roles (user_id, role_name) VALUES (2, 'ROLE_ADMIN');

