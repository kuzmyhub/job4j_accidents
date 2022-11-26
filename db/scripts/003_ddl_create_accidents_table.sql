CREATE TABLE IF NOT EXISTS accidents(
id SERIAL PRIMARY KEY,
name VARCHAR,
text VARCHAR,
address VARCHAR,
accident_types_id INT REFERENCES accident_types(id)
);