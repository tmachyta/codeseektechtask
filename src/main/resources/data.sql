INSERT INTO roles (id, role_name) VALUES (1, 'USER');
INSERT INTO roles (id, role_name) VALUES (2, 'ADMIN');

INSERT INTO teams (id, name, balance, transfer_commission, created_date, is_deleted) VALUES(1, 'KL', 100000, 15, '2007-11-07', 0);
INSERT INTO teams (id, name, balance, transfer_commission, created_date, is_deleted) VALUES(2, 'DK', 1000000, 12, '2002-12-07', 0);

INSERT INTO players (id, name, surname, age, experience, transfer_value, team_id, is_deleted) VALUES(1, 'test', 'test', 20, 50, 10000, 1, 0);
INSERT INTO players (id, name, surname, age, experience, transfer_value, team_id, is_deleted) VALUES(2, 'test2', 'test2', 20, 50, 2000, 2, 0);
