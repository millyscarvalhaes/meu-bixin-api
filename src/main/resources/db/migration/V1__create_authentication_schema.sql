CREATE SEQUENCE users_id_seq;
CREATE TABLE users(
    id INT NOT NULL DEFAULT nextval('users_id_seq'),
	email VARCHAR(50) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL,
	enabled BOOLEAN NOT NULL DEFAULT TRUE,
	created_at DATE NOT NULL DEFAULT CURRENT_DATE,
	updated_at DATE DEFAULT CURRENT_DATE,
    PRIMARY KEY (id)
);
ALTER SEQUENCE users_id_seq OWNED BY users.id;

CREATE SEQUENCE authorities_id_seq;
CREATE TABLE authorities (
	id INT NOT NULL DEFAULT nextval('authorities_id_seq'),
    authority VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);
ALTER SEQUENCE authorities_id_seq OWNED BY authorities.id;

CREATE TABLE user_authority(
    user_id INT NOT NULL,
    authority_id INT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_authority FOREIGN KEY (authority_id) REFERENCES authorities(id)
);
