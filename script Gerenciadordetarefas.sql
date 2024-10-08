PGDMP                      |            GerenciadeTarefas    16.4    16.4     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16398    GerenciadeTarefas    DATABASE     �   CREATE DATABASE "GerenciadeTarefas" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
 #   DROP DATABASE "GerenciadeTarefas";
                postgres    false            �            1259    16400    tarefa    TABLE       CREATE TABLE public.tarefa (
    id integer NOT NULL,
    titulo character varying(255) NOT NULL,
    descricao text,
    responsavel character varying(100),
    situacao character varying(50),
    prioridade character varying(50),
    deadline timestamp without time zone
);
    DROP TABLE public.tarefa;
       public         heap    postgres    false            �            1259    16399    tarefa_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tarefa_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.tarefa_id_seq;
       public          postgres    false    216            �           0    0    tarefa_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.tarefa_id_seq OWNED BY public.tarefa.id;
          public          postgres    false    215                       2604    16403 	   tarefa id    DEFAULT     f   ALTER TABLE ONLY public.tarefa ALTER COLUMN id SET DEFAULT nextval('public.tarefa_id_seq'::regclass);
 8   ALTER TABLE public.tarefa ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215    216            �          0    16400    tarefa 
   TABLE DATA           d   COPY public.tarefa (id, titulo, descricao, responsavel, situacao, prioridade, deadline) FROM stdin;
    public          postgres    false    216   ?       �           0    0    tarefa_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.tarefa_id_seq', 1, true);
          public          postgres    false    215                       2606    16407    tarefa tarefa_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.tarefa
    ADD CONSTRAINT tarefa_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.tarefa DROP CONSTRAINT tarefa_pkey;
       public            postgres    false    216            �   K   x�3�I,JMKt-�L�,O).O�t�t��t��w�sq�u���tr�p�4202�54�50R02�20 "�=... �;     