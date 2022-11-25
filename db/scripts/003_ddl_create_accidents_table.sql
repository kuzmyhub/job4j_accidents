CREATE TABLE IF NOT EXISTS accidents(
id SERIAL PRIMARY KEY,
name VARCHAR,
text VARCHAR,
adress VARCHAR,
assident_types_id INT REFERENCES assident_types(id);
);