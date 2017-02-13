-- Companies
 insert into benevity_exercise.companies (company_name, created_timestamp) values ('Air Canada', CURDATE() );
 insert into benevity_exercise.companies (company_name, created_timestamp) values ('BlackBerry Limited', CURDATE() );
 insert into benevity_exercise.companies (company_name, created_timestamp) values ('Lululemon Athletica', CURDATE() );
 insert into benevity_exercise.companies (company_name, created_timestamp) values ('Canadian Pacific Railway', CURDATE() );
 insert into benevity_exercise.companies (company_name, created_timestamp) values ('Tommy Hilfiger Corporation', CURDATE() );

-- Items
INSERT INTO `benevity_exercise`.`items` (`product`,`description`,`price`,`size`,`weight`,`created_timestamp`,`updated_timestamp`)
VALUES ('CANDY','Jelly-bean candies',10, 'N/A','1 kg',CURDATE(),CURDATE() );

INSERT INTO `benevity_exercise`.`items` (`product`,`description`,`price`,`size`,`weight`,`created_timestamp`,`updated_timestamp`)
VALUES ('TIRE','MICHELIN X-ICE Xi3 tire for Honda Civic 2015',200, '215/55R16','N/A',CURDATE(),CURDATE() );

INSERT INTO `benevity_exercise`.`items` (`product`,`description`,`price`,`size`,`weight`,`created_timestamp`,`updated_timestamp`)
VALUES ('SOAPBAR','Dove gentle soap bar.',2, 'N/A','20 gr',CURDATE(),CURDATE() );

INSERT INTO `benevity_exercise`.`items` (`product`,`description`,`price`,`size`,`weight`,`created_timestamp`,`updated_timestamp`)
VALUES ('TONER','Canon MF9170C imageCLASS black toner',110, 'N/A','0.3 kg',CURDATE(),CURDATE() );

INSERT INTO `benevity_exercise`.`items` (`product`,`description`,`price`,`size`,`weight`,`created_timestamp`,`updated_timestamp`)
VALUES ('PEN','Cross technical multifunction pen',30, 'N/A','10 gr',CURDATE(),CURDATE() );

-- Users
INSERT INTO `benevity_exercise`.`users` (`user_name`,`first_name`,`last_name`,`email`,`phone_number`,`company_id`,`created_timestamp`)
VALUES ('jmancevska','Jasminka','Mancevska','jmancevska@aircanada.com','1-403-111-2222',1000,CURDATE());

INSERT INTO `benevity_exercise`.`users` (`user_name`,`first_name`,`last_name`,`email`,`phone_number`,`company_id`,`created_timestamp`)
VALUES ('jgrey','John','Grey','jgrey@aircanada.com','1-403-111-2222',1000,CURDATE());

INSERT INTO `benevity_exercise`.`users` (`user_name`,`first_name`,`last_name`,`email`,`phone_number`,`company_id`,`created_timestamp`)
VALUES ('lbell','Linda','Bell','lbell@blackberry.com','1-416-222-3333',1001,CURDATE());

INSERT INTO `benevity_exercise`.`users` (`user_name`,`first_name`,`last_name`,`email`,`phone_number`,`company_id`,`created_timestamp`)
VALUES ('sbrumpton','Stefanie','Brumpton','sbrumpton@lululemon.com','1-604-333-4444',1002,CURDATE());

-- Orders
INSERT INTO `benevity_exercise`.`orders` (`order_number`,`user_id`,`country`,`status`,`created_timestamp`,`updated_timestamp`)
VALUES ('A12345/17',1000,'Canada','SUBMITTED',CURDATE(),CURDATE());

INSERT INTO `benevity_exercise`.`orders` (`order_number`,`user_id`,`country`,`status`,`created_timestamp`,`updated_timestamp`)
VALUES ('A12366/17',1001,'Canada','SUBMITTED',CURDATE(),CURDATE());

INSERT INTO `benevity_exercise`.`orders` (`order_number`,`user_id`,`country`,`status`,`created_timestamp`,`updated_timestamp`)
VALUES ('B12345/16',1002,'Canada','PAID',CURDATE(),CURDATE());

INSERT INTO `benevity_exercise`.`orders` (`order_number`,`user_id`,`country`,`status`,`created_timestamp`,`updated_timestamp`)
VALUES ('L55123/16',1003,'Canada','SHIPPED',CURDATE(),CURDATE());

-- Order Details
INSERT INTO `benevity_exercise`.`order_details` (`order_id`,`item_id`,`quantity`,`created_timestamp`,`updated_timestamp`)
VALUES (1000,1001,8,CURDATE(),CURDATE());

INSERT INTO `benevity_exercise`.`order_details` (`order_id`,`item_id`,`quantity`,`created_timestamp`,`updated_timestamp`)
VALUES (1001,1002,100,CURDATE(),CURDATE());
INSERT INTO `benevity_exercise`.`order_details` (`order_id`,`item_id`,`quantity`,`created_timestamp`,`updated_timestamp`)
VALUES (1001,1003,5,CURDATE(),CURDATE());

INSERT INTO `benevity_exercise`.`order_details` (`order_id`,`item_id`,`quantity`,`created_timestamp`,`updated_timestamp`)
VALUES (1002,1001,100,CURDATE(),CURDATE());
INSERT INTO `benevity_exercise`.`order_details` (`order_id`,`item_id`,`quantity`,`created_timestamp`,`updated_timestamp`)
VALUES (1002,1003,2,CURDATE(),CURDATE());
INSERT INTO `benevity_exercise`.`order_details` (`order_id`,`item_id`,`quantity`,`created_timestamp`,`updated_timestamp`)
VALUES (1002,1004,200,CURDATE(),CURDATE());

INSERT INTO `benevity_exercise`.`order_details` (`order_id`,`item_id`,`quantity`,`created_timestamp`,`updated_timestamp`)
VALUES (1003,1000,10,CURDATE(),CURDATE());


-- Shipping information
INSERT INTO `benevity_exercise`.`shipping_information` (`order_id`,`shipping_contact`,`street_address`,`city`,`province`,`country`,`postcode`,`created_timestamp`,`updated_timestamp`)
VALUES (1000,'Pat Mat - 1.403.333.3333','12 St. SW','Calgary','AB','Canada','T2N 2B6',CURDATE(),CURDATE());

INSERT INTO `benevity_exercise`.`shipping_information` (`order_id`,`shipping_contact`,`street_address`,`city`,`province`,`country`,`postcode`,`created_timestamp`,`updated_timestamp`)
VALUES (1001,'Pat Mat - 1.403.333.3333','12 St. SW','Calgary','AB','Canada','T2N 2B6',CURDATE(),CURDATE());

INSERT INTO `benevity_exercise`.`shipping_information` (`order_id`,`shipping_contact`,`street_address`,`city`,`province`,`country`,`postcode`,`created_timestamp`,`updated_timestamp`)
VALUES (1002,'Clue Blue - 1.520.333.0303','55 Ave','Philadelphia','PA','USA','15001',CURDATE(),CURDATE());

INSERT INTO `benevity_exercise`.`shipping_information` (`order_id`,`shipping_contact`,`street_address`,`city`,`province`,`country`,`postcode`,`created_timestamp`,`updated_timestamp`)
VALUES (1003,'Pat Mat - 1.403.333.3333','325 White Street','Vancouver','BC','Canada','V6C 1Z7',CURDATE(),CURDATE());
