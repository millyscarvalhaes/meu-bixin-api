INSERT INTO authorities (authority) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO users (email, password) VALUES ('admin@email.com', '$2a$12$RJFHidWO4NT1tuVVJbTPHeG9VOfj4r8nr/Wlm5upY4EoRfr3YaDWu');
INSERT INTO users (email, password) VALUES ('user@email.com', '$2a$12$pKYq2gggiEarCsXxN22uIO1FiRIQ5whP2hFVzS7pXEPIUkAxR.yIW');


INSERT INTO user_authority (user_id, authority_id) VALUES ( (SELECT ID FROM users WHERE email = 'admin@email.com'), (SELECT ID FROM authorities WHERE authority = 'ROLE_ADMIN') );
INSERT INTO user_authority (user_id, authority_id) VALUES ( (SELECT ID FROM users WHERE email = 'user@email.com'), (SELECT ID FROM authorities WHERE authority = 'ROLE_USER') );