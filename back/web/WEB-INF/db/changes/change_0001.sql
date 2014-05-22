CREATE TABLE users(
  id SERIAL PRIMARY KEY,
  username VARCHAR(20) NOT NULL,
  email VARCHAR(100) NOT NULL,
  sys_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE semester(
  id SERIAL PRIMARY KEY,
  year SMALLINT NOT NULL,
  semester_number SMALLINT NOT NULL CHECK (semester_number = 1 OR semester_number = 2),
  text VARCHAR(30) NOT NULL,
  sys_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE subject(
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  code VARCHAR(100),
  semester_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  sys_deleted BOOLEAN NOT NULL DEFAULT FALSE,
  FOREIGN KEY(semester_id) REFERENCES semester(id),
  FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE publication(
  id SERIAL PRIMARY KEY,
  text_plain TEXT NOT NULL,
  text_html TEXT NOT NULL,
  anonymous BOOLEAN NOT NULL,
  user_id INTEGER NOT NULL,
  date_posted TIMESTAMP WITH TIME ZONE NOT NULL,
  sys_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE topic(
  publication_id INTEGER PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  subject_id INTEGER NOT NULL,
  FOREIGN KEY(publication_id) REFERENCES publication(id),
  FOREIGN KEY(subject_id) REFERENCES subject(id)
);

CREATE TABLE comment(
  publication_id INTEGER,
  parent_publication_id INTEGER NOT NULL,
  FOREIGN KEY(publication_id) REFERENCES publication(id),
  FOREIGN KEY(parent_publication_id) REFERENCES publication(id)
);

CREATE TABLE vote(
  id SERIAL PRIMARY KEY,
  value INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  publication_id INTEGER NOT NULL,
  sys_deleted BOOLEAN NOT NULL DEFAULT FALSE,
  FOREIGN KEY(publication_id) REFERENCES publication(id),
  FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE privilege(
  id SERIAL PRIMARY KEY,
  code VARCHAR(255) NOT NULL,
  sys_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE user_privilege(
  user_id INTEGER NOT NULL,
  privilege_id INTEGER NOT NULL,
  sys_deleted BOOLEAN NOT NULL DEFAULT FALSE,
  FOREIGN KEY(privilege_id) REFERENCES privilege(id)
);

INSERT INTO users (username, email) VALUES ('teoreteetik', 'foorumitele@gmail.com');
INSERT INTO privilege (code) VALUES ('ADD_TOPIC');
INSERT INTO privilege (code) VALUES ('ADD_COMMENT');
INSERT INTO privilege (code) VALUES ('ADD_SUBJECT');
INSERT INTO privilege (code) VALUES ('ADD_SEMESTER');
INSERT INTO privilege (code) VALUES ('DELETE_FOREIGN_TOPIC');
INSERT INTO privilege (code) VALUES ('DELETE_FOREIGN_COMMENT');
INSERT INTO privilege (code) VALUES ('DELETE_SUBJECT');
INSERT INTO user_privilege (user_id, privilege_id) VALUES (1, 1);
INSERT INTO user_privilege (user_id, privilege_id) VALUES (1, 2);
INSERT INTO user_privilege (user_id, privilege_id) VALUES (1, 3);
INSERT INTO user_privilege (user_id, privilege_id) VALUES (1, 4);
INSERT INTO user_privilege (user_id, privilege_id) VALUES (1, 5);
INSERT INTO user_privilege (user_id, privilege_id) VALUES (1, 6);
INSERT INTO user_privilege (user_id, privilege_id) VALUES (1, 7);