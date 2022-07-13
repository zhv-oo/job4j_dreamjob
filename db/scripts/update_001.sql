CREATE TABLE post (
   id SERIAL PRIMARY KEY,
   name text,
   description text,
   visible boolean,
   created timestamp,
   city_id INT
);

CREATE TABLE candidate (
   id SERIAL PRIMARY KEY,
   name text,
   description text,
   photo bytea,
   created timestamp
);