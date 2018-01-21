DROP TABLE IF EXISTS `line_item`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `cart`;
DROP TABLE IF EXISTS `customer`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `category`;
DROP TABLE IF EXISTS `features`;
DROP TABLE IF EXISTS `attribute_value`;
DROP TABLE IF EXISTS `attribute_name`;

CREATE TABLE category (
  idcategory int(11) NOT NULL AUTO_INCREMENT,
  description varchar(20) NOT NULL,
  PRIMARY KEY (`idcategory`)
); 

CREATE TABLE product (
  idproduct bigint(20) NOT NULL AUTO_INCREMENT,
  productname varchar(100) NOT NULL,
  imageurl varchar(100) NOT NULL,
  shortdescription varchar(50) NOT NULL,
  description varchar(100) NOT NULL,
  price decimal(10,2) NOT NULL,
  idcategory int(11) DEFAULT NULL,
  PRIMARY KEY (`idproduct`),
  foreign key (`idcategory`) references category(`idcategory`)
); 


CREATE TABLE customer (
  idcustomer bigint(20) NOT NULL AUTO_INCREMENT,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  username varchar(50) NOT NULL,
  password varchar(256) NOT NULL,
  PRIMARY KEY (`idcustomer`)
); 

CREATE TABLE cart (
  idcart bigint(20) NOT NULL AUTO_INCREMENT,
  idcustomer bigint(20) NOT NULL,
  subtotal decimal(10,2) NOT NULL,
  PRIMARY KEY (`idcart`),
  foreign key (`idcustomer`) references customer(`idcustomer`) 
); 


CREATE TABLE line_item (
  idlines_item bigint(20) NOT NULL AUTO_INCREMENT,
  idproduct bigint(20),
  quantity int(11) NOT NULL,
  price decimal(10,2) NOT NULL,
  idcart bigint(20),
  PRIMARY KEY (`idlines_item`),
  foreign key (`idproduct`) references product(`idproduct`), 
  foreign key (`idcart`) references cart(`idcart`) 
); 

CREATE TABLE features (
  id int(20) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  feature_type VARCHAR(255),
  PRIMARY KEY(id)
);

CREATE TABLE attribute_name
(
	id int(20) NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	attribute_type VARCHAR(255),
	feature_id int (20),
	PRIMARY KEY(id),
	FOREIGN KEY(feature_id) REFERENCES features(id)

);

CREATE TABLE attribute_value (
	id int(20) NOT NULL AUTO_INCREMENT,
	attribute_values VARCHAR(255),
	product_id int(20),
	attribute_id int(20),
	PRIMARY KEY(id),
	foreign key(product_id) references product(idproduct),
	foreign key(attribute_id) references attribute_name(id)
);


INSERT INTO category (idcategory,description) VALUES (1,'Laptops');
INSERT INTO product (idproduct,productname,imageurl,shortdescription,description,price,idcategory) VALUES (1,'HP Stream 14" HD Laptop','https://images-na.ssl-images-amazon.com/images/I/719PXp91qkL._SY355_.jpg','This is short description','HP Stream 14" HD Laptop (Celeron N3060 Processor, 4GB RAM, 32GB Storage) with Windows 10 Home', 298.99,1);
INSERT INTO product (idproduct,productname,imageurl,shortdescription,description,price,idcategory) VALUES (2,'Acer Stream 14" HD Laptop','https://images-na.ssl-images-amazon.com/images/I/91LHJtpDwrL._SY355_.jpg','This is short description','HP Stream 14" HD Laptop (Celeron N3060 Processor, 4GB RAM, 32GB Storage) with Windows 10 Home', 398.99,1);
INSERT INTO product (idproduct,productname,imageurl,shortdescription,description,price,idcategory) VALUES (3,'Dell Stream 14" HD Laptop','https://images-na.ssl-images-amazon.com/images/I/81sarHO-saL._SL150_.jpg','This is short description','HP Stream 14" HD Laptop (Celeron N3060 Processor, 4GB RAM, 32GB Storage) with Windows 10 Home', 498.99,1);
INSERT INTO product (idproduct,productname,imageurl,shortdescription,description,price,idcategory) VALUES (4,'Apple Stream 14" HD Laptop','https://images-na.ssl-images-amazon.com/images/I/719PXp91qkL._SY355_.jpg','This is short description','HP Stream 14" HD Laptop (Celeron N3060 Processor, 4GB RAM, 32GB Storage) with Windows 10 Home',598.99,1);
INSERT INTO product (idproduct,productname,imageurl,shortdescription,description,price,idcategory) VALUES (5,'Toshiba Stream 14" HD Laptop','https://images-na.ssl-images-amazon.com/images/I/719PXp91qkL._SY355_.jpg','This is short description','HP Stream 14" HD Laptop (Celeron N3060 Processor, 4GB RAM, 32GB Storage) with Windows 10 Home',698.99,1);
INSERT INTO product (idproduct,productname,imageurl,shortdescription,description,price,idcategory) VALUES (6,'Tech Stream 14" HD Laptop','https://images-na.ssl-images-amazon.com/images/I/81sarHO-saL._SL150_.jpg','This is short description','HP Stream 14" HD Laptop (Celeron N3060 Processor, 4GB RAM, 32GB Storage) with Windows 10 Home', 798.99,1);
INSERT INTO product (idproduct,productname,imageurl,shortdescription,description,price,idcategory) VALUES (7,'Asus Stream 14" HD Laptop','https://images-na.ssl-images-amazon.com/images/I/719PXp91qkL._SY355_.jpg','This is short description','HP Stream 14" HD Laptop (Celeron N3060 Processor, 4GB RAM, 32GB Storage) with Windows 10 Home', 898.99,1);
INSERT INTO product (idproduct,productname,imageurl,shortdescription,description,price,idcategory) VALUES (8,'Google Pixel Stream 14" HD Laptop','https://images-na.ssl-images-amazon.com/images/I/91LHJtpDwrL._SY355_.jpg','This is short description','HP Stream 14" HD Laptop (Celeron N3060 Processor, 4GB RAM, 32GB Storage) with Windows 10 Home', 1000.99,1);

insert into features values(1,'Product Details', 'Details');

insert into attribute_name (id,name,attribute_type,feature_id) values(1,'Color', 'Color',1);
insert into attribute_name (id,name,attribute_type,feature_id) values(2,'RAM', 'RAM',1);
insert into attribute_name (id,name,attribute_type,feature_id) values(3,'Harddrive', 'Harddrive',1);
insert into attribute_value (attribute_values,product_id,attribute_id) values('Red',1 ,1);
insert into attribute_value (attribute_values,product_id,attribute_id) values('Black',1 ,1);
insert into attribute_value (attribute_values,product_id,attribute_id) values('Blue',1 ,1);
insert into attribute_value (attribute_values,product_id,attribute_id) values('500gb',1,2);
insert into attribute_value (attribute_values,product_id,attribute_id) values('750gb',1,3);




insert into features values(2,'Company Overview', 'Overview');

insert into attribute_name (id,name,attribute_type,feature_id) values(4,'Business Type:', 'Business',2);
insert into attribute_name (id,name,attribute_type,feature_id) values(5,'Main Products:', 'Main Products:',2);
insert into attribute_name (id,name,attribute_type,feature_id) values(6,'Total Employees:', 'Total Employees:',2);
insert into attribute_value (attribute_values,product_id,attribute_id) values('Manufacturer, Trading Company',1 ,4);
insert into attribute_value (attribute_values,product_id,attribute_id) values('Travel Organizers,Electronics Bags,Toiletry Bags,Cooler Bags,Laptop Bags',1,5);
insert into attribute_value (attribute_values,product_id,attribute_id) values('101 - 200 People',1,6);