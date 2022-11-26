CREATE TABLE IF NOT EXISTS accidents_rules(
id SERIAL PRIMARY KEY,
accidents_id int references accidents(id),
rules_id int references rules(id)
);