--
-- PostgreSQL database dump
--

-- Dumped from database version 11.18
-- Dumped by pg_dump version 15.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: azure_superuser
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO azure_superuser;

SET default_tablespace = '';

--
-- Name: alimentos; Type: TABLE; Schema: public; Owner: admindb
--

CREATE TABLE public.alimentos (
    usuario integer NOT NULL,
    nome character varying(30) NOT NULL,
    gramas integer,
    calorias integer,
    proteinas integer,
    carboidratos integer,
    gorduras integer
);


ALTER TABLE public.alimentos OWNER TO admindb;

--
-- Name: alimentos_usuario_seq; Type: SEQUENCE; Schema: public; Owner: admindb
--

CREATE SEQUENCE public.alimentos_usuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.alimentos_usuario_seq OWNER TO admindb;

--
-- Name: alimentos_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admindb
--

ALTER SEQUENCE public.alimentos_usuario_seq OWNED BY public.alimentos.usuario;


--
-- Name: usuarios; Type: TABLE; Schema: public; Owner: admindb
--

CREATE TABLE public.usuarios (
    id integer NOT NULL,
    nome character varying(30),
    categoria character varying(30),
    telefone character varying(30),
    email character varying(40),
    cidade character varying(40)
);


ALTER TABLE public.usuarios OWNER TO admindb;

--
-- Name: usuarios_id_seq; Type: SEQUENCE; Schema: public; Owner: admindb
--

CREATE SEQUENCE public.usuarios_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarios_id_seq OWNER TO admindb;

--
-- Name: usuarios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admindb
--

ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;


--
-- Name: alimentos usuario; Type: DEFAULT; Schema: public; Owner: admindb
--

ALTER TABLE ONLY public.alimentos ALTER COLUMN usuario SET DEFAULT nextval('public.alimentos_usuario_seq'::regclass);


--
-- Name: usuarios id; Type: DEFAULT; Schema: public; Owner: admindb
--

ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);


--
-- Data for Name: alimentos; Type: TABLE DATA; Schema: public; Owner: admindb
--

COPY public.alimentos (usuario, nome, gramas, calorias, proteinas, carboidratos, gorduras) FROM stdin;
\.


--
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: admindb
--

COPY public.usuarios (id, nome, categoria, telefone, email, cidade) FROM stdin;
1	Gabriel	Usuário	987654321	nome@email.com	Cuibá
2	Rafael	Usuário	12345678	teste@email.com	BH
\.


--
-- Name: alimentos_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: admindb
--

SELECT pg_catalog.setval('public.alimentos_usuario_seq', 1, false);


--
-- Name: usuarios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admindb
--

SELECT pg_catalog.setval('public.usuarios_id_seq', 1, false);


--
-- Name: alimentos alimentos_pkey; Type: CONSTRAINT; Schema: public; Owner: admindb
--

ALTER TABLE ONLY public.alimentos
    ADD CONSTRAINT alimentos_pkey PRIMARY KEY (nome);


--
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: admindb
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);


--
-- Name: alimentos alimentos_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admindb
--

ALTER TABLE ONLY public.alimentos
    ADD CONSTRAINT alimentos_usuario_fkey FOREIGN KEY (usuario) REFERENCES public.usuarios(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: azure_superuser
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

