PGDMP     3                    {         	   AirTicket    15.2    15.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    17052 	   AirTicket    DATABASE     �   CREATE DATABASE "AirTicket" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Indonesian_Indonesia.1252';
    DROP DATABASE "AirTicket";
                postgres    false            �            1259    17053    users    TABLE     �   CREATE TABLE public.users (
    id integer NOT NULL,
    nama character varying(100),
    email character varying(50),
    nomor_telepon character varying(50),
    password character varying(100)
);
    DROP TABLE public.users;
       public         heap    postgres    false            �          0    17053    users 
   TABLE DATA           I   COPY public.users (id, nama, email, nomor_telepon, password) FROM stdin;
    public          postgres    false    214   �       e           2606    17057    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    214            �      x������ � �     