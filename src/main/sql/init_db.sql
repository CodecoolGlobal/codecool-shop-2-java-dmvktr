ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS pk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product DROP CONSTRAINT IF EXISTS fk_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.category DROP CONSTRAINT IF EXISTS pk_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.supplier DROP CONSTRAINT IF EXISTS pk_supplier_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.user DROP CONSTRAINT IF EXISTS pk_user_id CASCADE;


DROP TABLE IF EXISTS public.product;
CREATE TABLE product (
                         id serial PRIMARY KEY NOT NULL,
                         name text NOT NULL,
                         default_price decimal,
                         default_currency text,
                         description text,
                         category_id integer,
                         supplier_id integer,
                         image_path text,
                         hover_image_path text
);


DROP TABLE IF EXISTS public.category;
CREATE TABLE category (
                              id serial PRIMARY KEY NOT NULL,
                              name text NOT NULL,
                              description text,
                              department text
);

DROP TABLE IF EXISTS public.supplier;
CREATE TABLE supplier (
                              id serial PRIMARY KEY NOT NULL,
                              name text NOT NULL,
                              description text
);

DROP TABLE IF EXISTS public.users;
CREATE TABLE users (
                          id serial PRIMARY KEY NOT NULL,
                          name text NOT NULL,
                          hashed_password text NOT NULL
);


ALTER TABLE ONLY product
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES category(id);

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES supplier(id);

INSERT INTO supplier VALUES (1, 'Hacker Tees', 'Hacker Tees for your Geeky Needs');
INSERT INTO supplier VALUES (2, 'Cool Stuff', 'Your T-shirts, customized');
SELECT pg_catalog.setval('supplier_id_seq', 2, true);

INSERT INTO category VALUES (1, 'T-Shirt', 'Apparel', 'A t-shirt commonly shortened to tee, is awesome.');
INSERT INTO category VALUES (2, 'Laptop Sticker', 'Stationery', 'A sticker to your laptop.');
SELECT pg_catalog.setval('category_id_seq', 2, true);

INSERT INTO users VALUES (1, 'Norbi', 'lol');
INSERT INTO users VALUES (2, 'Roky', 'asd');
SELECT pg_catalog.setval('users_id_seq', 2, true);

INSERT INTO product VALUES (1, 'GitHub Half Sleeve Unisex T-Shirt', 21.9, 'EUR', 'The cloth belt is mostly decorative and a sign of wealth.', 1, 2, 'CoolCode_GitHub.jpeg', 'CoolCode_GitHub_hover.jpeg');
INSERT INTO product VALUES (2, 'Bug Feature Half Sleeve Unisex T-Shirt', 19.9, 'EUR', 'His long sleeved, silky jacket covers him to just below his waist and is buttoned up completely at the top right side.', 1, 1, 'CoolCode_Bug-Feature.jpeg', 'CoolCode_Bug-Feature_hover.jpeg');
INSERT INTO product VALUES (3, '127.0.0.1 Half Sleeve Unisex T-Shirt', 26.9, 'EUR', 'Her sleeves are a little too long and a little narrow, their flow is broken up just above the elbow where they''re divided by long, ornamental bands.', 1, 2, 'CoolCode_Theres-no-place-like.jpg', 'CoolCode_Theres-no-place-like_hover.jpeg');
INSERT INTO product VALUES (4, 'Commit Half Sleeve Unisex T-Shirt', 23.9, 'EUR', 'The sleeves of his jacket are quite narrow and reach down to his hands, they''re decorated with a single thread lining and a decorative band.', 1, 2, 'CoolCode_Commit.jpeg', 'CoolCode_Commit_hover.jpeg');
INSERT INTO product VALUES (5, 'Do You Even Unit Test Half Sleeve Unisex T-Shirt',27.9, 'EUR', 'The jacket has a deep, round neckline which reveals part of the stylish shirt worn below it and is worn with a thick cloth belt.', 1, 1, 'CoolCode_Do-you-even-unit-test.jpeg', 'CoolCode_Do-you-even-unit-test_hover.jpeg');
INSERT INTO product VALUES (6, 'I Write Code Half Sleeve Unisex T-Shirt', 24.9, 'EUR', 'His pants are simple and a little wide and reach down to his soft leather boots.', 1, 1, 'CoolCode_I-Write-Code.jpeg', 'CoolCode_I-Write-Code_hover.jpeg');
INSERT INTO product VALUES (7, 'Breaking Builds Half Sleeve Unisex T-Shirt', 29.9, 'EUR', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 1, 2, 'CoolCode_Breaking-Builds.jpeg', 'CoolCode_Breaking-Builds_hover.jpeg');
INSERT INTO product VALUES (8, 'Debugging Half Sleeve Unisex T-Shirt', 25.9, 'EUR', 'The boots are made from a pretty uncommon leather, but are otherwise a common design.', 1, 2, 'CoolCode_Im-Sorry.jpeg', 'CoolCode_Im-Sorry_hover.jpeg');
INSERT INTO product VALUES (9, 'Laptop Sticker: Code Mode On', 14.9, 'EUR', 'A disordered field of grass is enclosed by a variety of bushes.', 2, 1, 'Sticker_Code-Mode.jpeg', 'Sticker_Code-Mode.jpeg');
INSERT INTO product VALUES (10, 'Laptop Sticker: Don''t Touch My Laptop', 14.9, 'EUR', 'A, pudgy boulder sits in the front left, and next to it is a message carved into the stone.', 2, 1, 'Sticker_Dont-Touch.jpeg', 'Sticker_Dont-Touch.jpeg');
INSERT INTO product VALUES (11, 'Laptop Sticker: Release is Coming', 13.9, 'EUR', 'The bushes reach 1.8m/6ft high, but this is unusual, and perhaps unique to this garden.', 2, 1, 'Sticker_Release-is-Coming.jpeg', 'Sticker_Release-is-Coming.jpeg');
INSERT INTO product VALUES (12, 'Laptop Sticker: Stack Overflow', 12.9, 'EUR', 'Vines and grass are seemingly content with their positions in the garden, none trying to reach beyond, at least not yet.', 2, 1, 'Sticker_Stack-overflow.jpeg', 'Sticker_Stack-overflow.jpeg');
SELECT pg_catalog.setval('product_id_seq', 12, true);