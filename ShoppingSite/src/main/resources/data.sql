DROP TABLE IF EXISTS users, products, shopping_cart, cart_products;

CREATE TABLE users(
    id INT NOT NULL AUTO_INCREMENT,
    username varchar(255) NOT NULL DEFAULT '',
    firstName varchar(255) NOT NULL DEFAULT '',
    lastName varchar(255) NOT NULL DEFAULT '',
    email varchar(255) NOT NULL DEFAULT '',
    phone varchar(255) NOT NULL DEFAULT '',
    address varchar(255) NOT NULL DEFAULT '',
    password varchar(255) NOT NULL DEFAULT '',
    active INT NOT NULL DEFAULT '1',
    roles varchar(255) NOT NULL DEFAULT '',
    permissions varchar(255) NOT NULL DEFAULT '',
    PRIMARY KEY(id)
);

CREATE TABLE products(
    id INT NOT NULL AUTO_INCREMENT,
    productName varchar(255) NOT NULL DEFAULT '',
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    status INT NOT NULL DEFAULT '1',
    PRIMARY KEY(id)
);

CREATE TABLE shopping_cart (
    cart_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    user_id INT NOT NULL,
    amount DOUBLE NOT NULL,
    state INT NOT NULL DEFAULT '1'
);

CREATE TABLE cart_products (
    cart_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    status INT NOT NULL DEFAULT '1',
    PRIMARY KEY (cart_id, product_id),
    FOREIGN KEY (cart_id) REFERENCES shopping_cart(cart_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

INSERT INTO users (username, firstName, lastName, email, phone, address, password, active, roles , permissions)
VALUES ('zapbeeb','Zaphod','Beeblebrox','za@b.il','09987','Somewhere forSure','',1,'','');

INSERT INTO products (productName, quantity, price)
VALUES ('TableTop', 12, 300.0),('Velociraptor',50,200.0)
