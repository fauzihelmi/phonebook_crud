# phonebook_crud


This project uses thymeleaf so that for testing it can be directly used on the website when the spring boot service has been run.
Why use thymeleaf to make it easier to run front-end applications.




-- Table: public.phonebook

-- DROP TABLE IF EXISTS public.phonebook;

CREATE TABLE IF NOT EXISTS public.phonebook
(
    id bigint NOT NULL DEFAULT nextval('phonebook_id_seq'::regclass),
    address character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    first_name character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    notes character varying(255) COLLATE pg_catalog."default",
    phone_no character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT phonebook_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.phonebook
    OWNER to root;
