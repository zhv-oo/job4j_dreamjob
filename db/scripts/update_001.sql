CREATE TABLE IF NOT EXISTS post (
   id SERIAL PRIMARY KEY,
   name text,
   description text,
   visible boolean,
   created timestamp,
   city_id INT
);

CREATE TABLE IF NOT EXISTS candidate (
   id SERIAL PRIMARY KEY,
   name text,
   description text,
   photo bytea,
   created timestamp
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    email varchar,
    password text
);

ALTER TABLE users ADD CONSTRAINT email_unique UNIQUE (email);