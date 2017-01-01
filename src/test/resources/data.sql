INSERT INTO role(id, name, description)
    VALUES(1, 'ROLE_USER', 'Registered user'),
      (2, 'ROLE_ADMIN', 'Administrator'),
      (3, 'ROLE_SYS_ADMIN', 'Administrator with additional privileges');
INSERT INTO
  user(username, password, enabled, expired, credentials_expired, locked)
VALUES ('erobic123', 'erobicpassword', true, false, false, false);