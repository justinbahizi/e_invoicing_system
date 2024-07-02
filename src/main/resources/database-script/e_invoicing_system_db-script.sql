--
-- PostgreSQL database scripts

CREATE DATABASE e_invoicing_system_db;

CREATE TABLE public.inv_customer (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    phone_number character varying(12) NOT NULL
);

CREATE SEQUENCE public.inv_customer_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE public.inv_invoice (
    id bigint NOT NULL,
    amount numeric(38,2) NOT NULL,
    invoice_date date NOT NULL,
    invoice_status character varying(255) NOT NULL,
    customer_id bigint NOT NULL,
    CONSTRAINT inv_invoice_invoice_status_check CHECK (((invoice_status)::text = ANY ((ARRAY['CREATED'::character varying, 'APPROVED'::character varying, 'PARTIALLY_PAID'::character varying, 'FULLY_PAID'::character varying])::text[])))
);

CREATE SEQUENCE public.inv_invoice_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--- Sample Insert SCRIPT

INSERT INTO public.inv_customer VALUES (2, 'john@example.com', 'John Doe', '250788006011');
INSERT INTO public.inv_customer VALUES (3, 'jane@example.com', 'Mary Jane', '250788296151');
INSERT INTO public.inv_customer VALUES (6, 'karim.johnson@gmail.com', 'Karim Johnson', '250799223355');
INSERT INTO public.inv_customer VALUES (7, 'milner@gov.usa.com', 'Eric Milner', '250780206000');
INSERT INTO public.inv_customer VALUES (5, 'justin.bahizi@gmail.com', 'Justin Bahizi', '250788286350');
INSERT INTO public.inv_customer VALUES (52, 'kenedy@usa.great', 'Kennedy John', '250788121212');


INSERT INTO public.inv_invoice VALUES (3, 1400000.00, '2024-07-02', 'CREATED', 6);
INSERT INTO public.inv_invoice VALUES (4, 460000000.00, '2024-04-12', 'CREATED', 7);
INSERT INTO public.inv_invoice VALUES (5, 465976000.00, '2024-06-10', 'CREATED', 3);
INSERT INTO public.inv_invoice VALUES (6, 60000000.00, '2024-06-20', 'CREATED', 3);
INSERT INTO public.inv_invoice VALUES (7, 1000000000.00, '2024-06-10', 'CREATED', 7);
INSERT INTO public.inv_invoice VALUES (8, 500000.00, '2024-06-10', 'CREATED', 5);
INSERT INTO public.inv_invoice VALUES (2, 99999000.00, '2024-07-01', 'CREATED', 2);


SELECT pg_catalog.setval('public.inv_invoice_seq', 51, true);


ALTER TABLE ONLY public.inv_customer
    ADD CONSTRAINT inv_customer_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.inv_customer
    ADD CONSTRAINT inv_customer_uk_01 UNIQUE (phone_number);

ALTER TABLE ONLY public.inv_invoice
    ADD CONSTRAINT inv_invoice_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.inv_invoice
    ADD CONSTRAINT inv_invoice_fk_01 FOREIGN KEY (customer_id) REFERENCES public.inv_customer(id);

