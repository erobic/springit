INSERT INTO role(id, code, label, created_at)
    VALUES(1, 'ROLE_USER', 'Registered user', CURRENT_TIMESTAMP ),
      (2, 'ROLE_ADMIN', 'Administrator', CURRENT_TIMESTAMP ),
      (3, 'ROLE_SYS_ADMIN', 'Administrator with additional privileges', CURRENT_TIMESTAMP );
INSERT INTO
  user(reference_id, version, username, password, enabled, expired, credentials_expired, locked, created_by, created_at)
VALUES ('37c3178d-3b49-47b6-99d1-277b1a3e8d', 0, 'erobic123', 'erobicpassword', true, false, false, false, 'erobic123', CURRENT_TIMESTAMP );