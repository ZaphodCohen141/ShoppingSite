DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id INT NOT NULL,
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

INSERT INTO users (id, username, firstName, lastName, email, phone, address, password, active, roles , permissions)
VALUES (1,'zapbeeb','Zaphod','Beeblebrox','za@b.il','09987','Somewhere forSure','',1,'','')