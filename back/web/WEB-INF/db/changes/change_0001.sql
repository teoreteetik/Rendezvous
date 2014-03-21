CREATE TABLE users(
  id SERIAL PRIMARY KEY,
  username VARCHAR(20) NOT NULL,
  email VARCHAR(100) NOT NULL
);

CREATE TABLE semester(
  id SERIAL PRIMARY KEY,
  year SMALLINT NOT NULL,
  semester_number SMALLINT NOT NULL CHECK (semester_number = 1 OR semester_number = 2),
  text VARCHAR(30) NOT NULL 
);

CREATE TABLE subject(
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  code VARCHAR(100),
  semester_id INTEGER NOT NULL,
  FOREIGN KEY(semester_id) REFERENCES semester(id)
);

CREATE TABLE publication(
  id SERIAL PRIMARY KEY,
  text_plain TEXT NOT NULL,
  text_html TEXT NOT NULL,
  anonymous BOOLEAN NOT NULL,
  user_id INTEGER NOT NULL,
  date_posted TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE topic(
  publication_id INTEGER PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  subject_id INTEGER NOT NULL,
  FOREIGN KEY(publication_id) REFERENCES publication(id),
  FOREIGN KEY(subject_id) REFERENCES subject(id)
);

CREATE TABLE comment(
  publication_id INTEGER PRIMARY KEY,
  parent_publication_id INTEGER NOT NULL,
  FOREIGN KEY(publication_id) REFERENCES publication(id),
  FOREIGN KEY(parent_publication_id) REFERENCES publication(id)
);

CREATE TABLE upvote(
  id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL,
  publication_id INTEGER NOT NULL,  
  FOREIGN KEY(publication_id) REFERENCES publication(id),
  FOREIGN KEY(user_id) REFERENCES users(id)
);