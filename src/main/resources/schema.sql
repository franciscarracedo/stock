DROP TABLE IF EXISTS product_master CASCADE;
DROP TABLE IF EXISTS predictor_stock CASCADE;
DROP TABLE IF EXISTS current_stock CASCADE;
DROP TABLE IF EXISTS inventory_movement CASCADE;
DROP TABLE IF EXISTS product_master CASCADE;

CREATE TABLE product_master (
                                product_id VARCHAR(10) PRIMARY KEY,
                                unit_of_measure VARCHAR(50),
                                product_name VARCHAR(100),
                                sku VARCHAR(255),
                                product_code VARCHAR(50),
                                unit VARCHAR(20),
                                cost DECIMAL,
                                price DECIMAL,
                                category VARCHAR(50),
                                location VARCHAR(10),
                                active BOOLEAN
);


CREATE TABLE inventory_movement (
                                    movement_id VARCHAR(10) PRIMARY KEY,
                                    movement_date TIMESTAMP,  -- <== CAMBIADO
                                    product_id VARCHAR(10),
                                    movement_type VARCHAR(20),
                                    quantity INT,
                                    order_id VARCHAR(20),
                                    notes TEXT,
                                    FOREIGN KEY (product_id) REFERENCES product_master(product_id)
);
CREATE TABLE current_stock (
                                             product_id VARCHAR(10) PRIMARY KEY,
    quantity INT NOT NULL,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_inventory_cost DECIMAL(10,2),
    FOREIGN KEY (product_id) REFERENCES product_master(product_id)
    );

CREATE TABLE predictor_stock (
                                               prediction_date DATE,
                                               product_id VARCHAR(10),
    predicted_quantity INT,
    predicted_sales DECIMAL(10,2),
    is_holiday BOOLEAN,
    note TEXT,
    FOREIGN KEY (product_id) REFERENCES product_master(product_id)
    );

CREATE TABLE roles (
                       name VARCHAR(50) PRIMARY KEY
);

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       enabled BOOLEAN NOT NULL
);

CREATE TABLE users_roles (
                             user_id BIGINT,
                             role_name VARCHAR(50),
                             PRIMARY KEY (user_id, role_name),
                             FOREIGN KEY (user_id) REFERENCES users(id),
                             FOREIGN KEY (role_name) REFERENCES roles(name)
);