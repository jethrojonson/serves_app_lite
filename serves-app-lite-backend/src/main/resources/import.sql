--PRODUCTS
insert into products (id, product_name, active) values(NEXTVAL('hibernate_sequence'), 'Café con leche', true);
insert into products (id, product_name, active) values(NEXTVAL('hibernate_sequence'), 'Entera Jamón', true);
insert into products (id, product_name, active) values(NEXTVAL('hibernate_sequence'), 'Estrella Galicia', true);
insert into products (id, product_name, active) values(NEXTVAL('hibernate_sequence'), 'Cruzcampo', true);
insert into products (id, product_name, active) values(NEXTVAL('hibernate_sequence'), 'Ensaladilla Rusa', true);
insert into products (id, product_name, active) values(NEXTVAL('hibernate_sequence'), 'Clara con limón', true);
insert into products (id, product_name, active) values(NEXTVAL('hibernate_sequence'), 'Solomillo al Wiskey', true);
insert into products (id, product_name, active) values(NEXTVAL('hibernate_sequence'), 'Tabernero', false);
insert into products (id, product_name, active) values(NEXTVAL('hibernate_sequence'), 'Tinto de verano', true);
insert into products (id, product_name, active) values(NEXTVAL('hibernate_sequence'), 'Montadito de Lomo', false);
insert into products (id, product_name, active) values(NEXTVAL('hibernate_sequence'), 'Café solo', true);
insert into products (id, product_name, active) values(NEXTVAL('hibernate_sequence'), 'Coca-Cola', true);


--ADMINS
insert into users (id, username, password, avatar, account_non_expired, account_non_locked, credentials_non_expired, enabled, roles, created_at, last_password_change_at) values ('596c8ae6-ae26-11ed-afa1-0242ac120002', 'admin000', '{bcrypt}$2a$12$ixWyIf5hysX32D/kJtehpeJlQoBWPp9lTKsXgbm8R.QN8KHw9Jqmm', 'https://robohash.org/admin000', true, true, true, true, 'ADMIN', current_timestamp, current_timestamp);

--COMPANIES

--1ª
insert into users (id, username, password, avatar, account_non_expired, account_non_locked, credentials_non_expired, enabled, roles, created_at, last_password_change_at) values ('2cd0dc79-9195-4e96-a399-a46b97c98e70', 'company000', '{bcrypt}$2a$12$ueCfmCg1.IAOItnYMuC6Vu.7dO9MdZ9Y8fy.MsoOjcQug21Zv5LbG', 'https://robohash.org/company000', true, true, true, true, 'COMPANY', current_timestamp, current_timestamp);
insert into companies (cif, company_name, id) values ('A-12345678', 'Company', '2cd0dc79-9195-4e96-a399-a46b97c98e70');
    --ITEMS_MENU
    insert into menu_items (id, category, price, company_id, product_id) values (NEXTVAL('hibernate_sequence'), 'Café', 1.4, '2cd0dc79-9195-4e96-a399-a46b97c98e70', 1);
    insert into menu_items (id, category, price, company_id, product_id) values (NEXTVAL('hibernate_sequence'), 'Tostada', 2.5, '2cd0dc79-9195-4e96-a399-a46b97c98e70', 2);
    insert into menu_items (id, category, price, company_id, product_id) values (NEXTVAL('hibernate_sequence'), 'Cerveza', 1.8, '2cd0dc79-9195-4e96-a399-a46b97c98e70', 3);
    insert into menu_items (id, category, price, company_id, product_id) values (NEXTVAL('hibernate_sequence'), 'Cerveza', 1.4, '2cd0dc79-9195-4e96-a399-a46b97c98e70', 4);
    insert into menu_items (id, category, price, company_id, product_id) values (NEXTVAL('hibernate_sequence'), 'Comida Fría', 2.8, '2cd0dc79-9195-4e96-a399-a46b97c98e70', 5);
    insert into menu_items (id, category, price, company_id, product_id) values (NEXTVAL('hibernate_sequence'), 'Cerveza', 1.4, '2cd0dc79-9195-4e96-a399-a46b97c98e70', 6);
    insert into menu_items (id, category, price, company_id, product_id) values (NEXTVAL('hibernate_sequence'), 'Comida Caliente', 3.5, '2cd0dc79-9195-4e96-a399-a46b97c98e70', 7);

--CUSTOMERS

--1ª
insert into users (id, username, password, avatar, account_non_expired, account_non_locked, credentials_non_expired, enabled, roles, created_at, last_password_change_at) values ('1b171e13-403b-4861-b6b7-9a95e4fd18f7', 'customer000', '{bcrypt}$2a$12$ESMvf2iDNENO0vbG8RsDJOCNhU7ppz/ioOTt0Eag447.KiBHmZ/hC', 'https://robohash.org/customer000', true, true, true, true, 'CUSTOMER', current_timestamp, current_timestamp);
insert into customers (name, surname, email, id) values ('Name', 'Surname', 'customer@email.com', '1b171e13-403b-4861-b6b7-9a95e4fd18f7');



