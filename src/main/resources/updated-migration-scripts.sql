-- V1__Create_Users_Table.sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- V2__Create_Roles_And_Permissions_Tables.sql
CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE permissions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE role_permissions (
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles (id),
    FOREIGN KEY (permission_id) REFERENCES permissions (id)
);

-- V3__Insert_Roles_And_Permissions.sql
INSERT INTO roles (name) VALUES 
('SUPER_ADMIN'),
('VIEWER_ADMIN'),
('PRODUCER_ADMIN'),
('CUSTOMER'),
('REGULAR_USER');

INSERT INTO permissions (name) VALUES 
('MANAGE_USERS'),
('VIEW_ALL_PRODUCERS'),
('MANAGE_PRODUCERS'),
('MANAGE_ORGANIZATIONS'),
('MANAGE_DIVISIONS'),
('VIEW_OWN_PRODUCER_DATA'),
('MANAGE_OWN_ACCOUNT'),
('VIEW_OWN_SUBSCRIPTIONS'),
('MANAGE_SUBSCRIPTIONS');

INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM roles r, permissions p
WHERE 
    (r.name = 'SUPER_ADMIN' AND p.name IN ('MANAGE_USERS', 'VIEW_ALL_PRODUCERS', 'MANAGE_PRODUCERS', 'MANAGE_ORGANIZATIONS', 'MANAGE_DIVISIONS', 'MANAGE_SUBSCRIPTIONS'))
    OR (r.name = 'VIEWER_ADMIN' AND p.name = 'VIEW_ALL_PRODUCERS')
    OR (r.name = 'PRODUCER_ADMIN' AND p.name IN ('MANAGE_ORGANIZATIONS', 'MANAGE_DIVISIONS', 'VIEW_OWN_PRODUCER_DATA'))
    OR (r.name = 'CUSTOMER' AND p.name IN ('MANAGE_OWN_ACCOUNT', 'VIEW_OWN_SUBSCRIPTIONS'))
    OR (r.name = 'REGULAR_USER' AND p.name = 'MANAGE_OWN_ACCOUNT');

-- V4__Create_Producers_Table.sql
CREATE TABLE producers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- V5__Create_Organizations_Table.sql
CREATE TABLE organizations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    producer_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (producer_id) REFERENCES producers (id)
);

-- V6__Create_Divisions_Table.sql
CREATE TABLE divisions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    organization_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (organization_id) REFERENCES organizations (id)
);

-- V7__Create_Customers_Table.sql
CREATE TABLE customers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    company_name VARCHAR(100),
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- V8__Create_Subscriptions_Table.sql
CREATE TABLE subscriptions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    status ENUM('ACTIVE', 'CANCELLED', 'EXPIRED') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers (id)
);

-- V9__Create_User_Producer_Table.sql
CREATE TABLE user_producer (
    user_id BIGINT NOT NULL,
    producer_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, producer_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (producer_id) REFERENCES producers (id)
);

-- V10__Insert_Initial_Data.sql
-- Insert a super admin user
INSERT INTO users (username, password, email) VALUES 
('superadmin', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'superadmin@example.com');

-- Assign SUPER_ADMIN role to the sample user
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r
WHERE u.username = 'superadmin' AND r.name = 'SUPER_ADMIN';

-- Insert other initial data as needed
