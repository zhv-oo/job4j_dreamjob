CREATE TABLE post (
   id SERIAL PRIMARY KEY,
   name text,
   description text,
   visible boolean,
   created timestamp,
   city_id INT
);