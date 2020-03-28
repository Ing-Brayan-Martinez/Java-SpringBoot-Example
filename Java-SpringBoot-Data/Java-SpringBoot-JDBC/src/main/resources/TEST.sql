CREATE DATABASE "TEST"
    WITH OWNER = postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    CONNECTION LIMIT = -1;

CREATE TABLE public.Student (
  id serial PRIMARY KEY NOT NULL,
  "name" varchar(250) NOT NULL,
  age integer NOT NULL
);



INSERT INTO public.Student (id, "name", age) VALUES
(1, 'Zara', 11),
(2, 'Nuha', 200),
(3, 'Ayan', 15),
(4, 'Zara', 11),
(5, 'Nuha', 2),
(6, 'Ayan', 15),
(7, 'Zara', 11),
(8, 'Nuha', 2),
(9, 'Ayan', 15),
(10, 'Zara', 11),
(11, 'Nuha', 2),
(12, 'Ayan', 15),
(13, 'Zara', 11),
(14, 'Nuha', 2),
(15, 'Ayan', 15);


