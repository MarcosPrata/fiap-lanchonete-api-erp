CREATE TABLE product (
	id bigserial NOT NULL,
    name varchar(100) NOT NULL,
    description varchar(200) NOT NULL,
	category varchar(25) NOT NULL,
	price float NOT NULL,
	deleted bool NOT NULL default(false),

	CONSTRAINT product_pkey PRIMARY KEY (id)
);

CREATE TABLE product_image (
	id bigserial NOT NULL,
    product_id bigint NOT NULL,
    image_url varchar(500) NOT NULL,

	CONSTRAINT product_image_pkey PRIMARY KEY (id),
	CONSTRAINT fk_image_product FOREIGN KEY (product_id) REFERENCES product(id)
);

INSERT INTO product (id, name, description, category, price) VALUES (1, 'Sanduiche de Mortadela', 'Pão e mortadela', 0, 7.50);
INSERT INTO product (id, name, description, category, price) VALUES (2, 'Batatinha Frita', '1, 2, 3 ', 1, 5.90);
INSERT INTO product (id, name, description, category, price) VALUES (3, 'Caldo de cana', 'Geladinho e sem doença de chagas', 2, 2.20);
INSERT INTO product (id, name, description, category, price) VALUES (4, 'Petit Gateau do Jacquin', 'Não desliga freezer a nôtche', 3, 14.50);

ALTER SEQUENCE product_id_seq RESTART WITH 5;

INSERT INTO product_image (id, product_id, image_url) VALUES (1, 1, 'https://vejasp.abril.com.br/wp-content/uploads/2016/12/sanduiche-mortadela-071.jpeg');
INSERT INTO product_image (id, product_id, image_url) VALUES (2, 2, 'https://www.sabornamesa.com.br/media/k2/items/cache/377829b055e89e3afb894e7528a10996_XL.jpg');
INSERT INTO product_image (id, product_id, image_url) VALUES (3, 3, 'https://static1.minhavida.com.br/recipes/62/8e/70/16/caldo-de-cana-orig-1.jpg');
INSERT INTO product_image (id, product_id, image_url) VALUES (4, 4, 'https://ribeirao.emporiotartufo.com.br/image/cache/catalog/img/produtos_2021/126065-PETIT-GATEAU-CHOCOLATE-2UN-180-GRAMAS-SANTA-JULIA-a-756x567.jpg');

ALTER SEQUENCE product_image_id_seq RESTART WITH 5;