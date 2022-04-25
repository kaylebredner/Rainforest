DROP DATABASE IF EXISTS rainforest;

CREATE DATABASE rainforest;

USE rainforest;

CREATE TABLE shipmentLocation_t
(LocationID integer	NOT NULL auto_increment,
LocationName varchar(25) NOT NULL,
Address1 varchar(25) NOT NULL,
Address2 varchar(25),
City varchar(20) NOT NULL,
State varchar(20) NOT NULL,
ZipCode varchar(5) NOT NULL,
PRIMARY KEY(LocationID)
);

CREATE TABLE customer_t
(CustomerID integer NOT NULL auto_increment,
LocationID integer,
CustomerName VarChar(50) NOT NULL,
CustomerPremium boolean NOT NULL, /*0 is false, anything else is true*/
PRIMARY KEY(CustomerID),
FOREIGN KEY(LocationID) REFERENCES shipmentLocation_t(LocationID)
);

CREATE TABLE employee_t
(EmployeeID integer NOT NULL AUTO_INCREMENT,
EmployeeName varchar(50) NOT NULL,
EmployeeAddress varchar(25) NOT NULL,
EmployeeStartDate date NOT NULL,
IsRetired Boolean NOT NULL, /*0 is default value (not retired), anything else indicates a retiree.*/
PRIMARY KEY(EmployeeID)
);

CREATE TABLE vendor_t
(VendorID integer NOT NULL AUTO_INCREMENT,
VendorName varchar(50) NOT NULL,
VendorAddress varchar(50) NOT NULL,
PRIMARY KEY(VendorID)
);

CREATE TABLE category_t
(CategoryID integer NOT NULL AUTO_INCREMENT,
CategoryName varchar(50) NOT NULL,
CategoryDescription text,
PRIMARY KEY(CategoryID)
);

CREATE TABLE inventory_t
(InventoryID integer NOT NULL AUTO_INCREMENT,
Quantity integer NOT NULL,
Position char(3) NOT NULL,
PRIMARY KEY(InventoryID)
);

CREATE TABLE paymentMethod_t
(PaymentID integer NOT NULL auto_increment,
PaymentName varchar(20) NOT NULL,
CardNumber varchar(16) NOT NULL,
CardExpiration date NOT NULL,
CardCCV varchar(3) NOT NULL,
PRIMARY KEY(PaymentID)
);


CREATE TABLE order_t
(OrderID integer NOT NULL AUTO_INCREMENT,
CustomerID integer NOT NULL,
PaymentID integer NOT NULL,
EmployeeID integer NOT NULL,
OrderStatus varchar(25) NOT NULL,
OrderDate date NOT NULL,
OrderCost integer NOT NULL,
TrackingNumber integer NOT NULL,
PRIMARY KEY(OrderID),
FOREIGN KEY(CustomerID) REFERENCES customer_t(CustomerID),
FOREIGN KEY(EmployeeID) REFERENCES employee_t(EmployeeID)
);

CREATE TABLE product_t
(ProductID integer NOT NULL AUTO_INCREMENT,
VendorID integer NOT NULL,
InventoryID integer NOT NULL,
CategoryID integer NOT NULL,
ProductName varchar(75) NOT NULL,
ProductPrice decimal(13,2) NOT NULL,
ProductWeight decimal(13,2) NOT NULL,
ImageURL varchar(500),
PRIMARY KEY(ProductID),
FOREIGN KEY(VendorID) REFERENCES vendor_t(VendorID),
FOREIGN KEY(InventoryID) REFERENCES inventory_t(InventoryID),
FOREIGN KEY(CategoryID) REFERENCES category_t(CategoryID)
);

use rainforest;

insert into shipmentLocation_t(LocationName, Address1, Address2, City, State, ZipCode) values
('Lester''s Home', '128 Ann Avenue', NULL, 'Charleston', 'WV', 26508),
('Sharmaine''s Home', '3 Fairway Court', NULL, 'Pittsburgh', 'PA', 18974),
('Sujata''s Home', '705 North Court Road', 'Apt 203', 'Dallas', 'TX', 78213),
('Yvette''s Home', '798 Carriage Street', NULL, 'Los Santas', 'CA', 90008),
('Eliza''s Home', '87 Lakewood Street', NULL, 'Lakeland', 'MI', 48185),
('John''s Home', '100 South Devon Street', '101', 'Pittsburgh', 'PA', 19468),
('Reese''s Home', '4 Saxton Drive', NULL, 'Mary', 'MA', 02184),
('Martin''s Home', '986 Tower Avenue', '408A', 'Phoenix', 'AZ', 85351),
('Ignat''s Home', '699 S. Princeton Drive', NULL, 'Greensburg', 'IL', 60099),
('Glinda''s Home', '4 South Court', NULL, 'Mainland', 'MI', 48076),
('Aurora''s Home', '60 Linden Street', '202B', 'Sudo', 'FL', 33470),
('Tiah''s Home', '3309 Vesta Drive', NULL, 'Vesta', 'ND', 58737),
('Asiya''s Home', '3099 Eden Drive', NULL, 'Fredricksonville', 'VA', 23069),
('Reegan''s Home', '4324 Armory Road', NULL, 'Towneville', 'NC', 28540),
('Tamia''s Home', '330 Mount Olive Road', NULL, 'Mexico', 'GA', 30030),
('Lilly''s Home', '2898 Wolf Pen Road', NULL, 'Sacramento', 'CA', 94107),
('Sammy''s Home', '4471 Eagle Street', NULL, 'Clevelands', 'IL', 62208),
('Bethany''s Home', '3951 Caynor Circle', NULL, 'Southland', 'NJ', 07477),
('Frederic''s Home', '2227 Virginia Street', NULL, 'Hethland', 'IL', 60607),
('Daniele''s Home', '1352 Gateway Avenue', NULL, 'Sacrtamento', 'CA', 93307),
('Shaan''s Home', '2729 Dovetail Estates', NULL, 'Topeka', 'OK', 73951),
('Eva''s Home', '1151 Colony Street', NULL, 'Connect', 'CT', 06103),
('Dawn''s Home', '304 Trouser Leg Road', NULL, 'Militatowne', 'MO', 65032),
('Ruby-May''s Home', '4244 Brooklyn Street', NULL, 'Salem', 'OR', 97401),
('Tiya''s Home', '3360 Melville Street', NULL, 'Lugarsville', 'PA', 18017);

insert into customer_t(CustomerName, LocationID, CustomerPremium /*has premium if value is 1, if 0 does not*/) values
	('Lester Domingo', 1, 0),
    ('Sharmaine Helle', 2, 1),
    ('Sujata Mithra', 3, 0),
    ('Yvette Embla', 4, 0),
    ('Eliza Yurena', 5, 0),
    ('John Adams', 6, 1),
    ('Reese Bidziil', 7, 0),
    ('Martin Uttara', 8, 1),
    ('Ignat Iddo', 9, 1),
    ('Glinda Herman', 10, 0),
    ('Aurora Emil', 11, 0),
    ('Tiah Howarth', 12, 1),
    ('Asiya Graves', 13, 0),
    ('Reegan Hackett', 14, 0),
    ('Tamia Dickens', 15, 0),
    ('Lilly Osborn', 16, 1),
    ('Sammy Parra', 17, 0),
    ('Bethany Bowden', 18, 0),
    ('Frederic Herring', 19, 0),
    ('Daniele Hubbard', 20, 1),
    ('Shaan Daugherty', 21, 0),
    ('Eva Patrick', 22, 1),
    ('Dawn Frame', 23, 0),
    ('Ruby-May Orozco', 24, 0),
    ('Tiya Howells', 25, 0);
    
    insert into employee_t(EmployeeName, EmployeeAddress, EmployeeStartDate, IsRetired/*0 is default value (not retired), 1 indicates a retiree.*/) values
    ('Kayleb Redner', '123 Wendy''s Lane', '2021-01-25', 0),
    ('Alex Bishop', '6030 Helpdesk Road', '2021-06-24', 0),
    ('AJ Sanfilippo', '2010 Comp Street', '2020-04-01', 0),
    ('Dylan Hasty', '5443 Potato Boulevard', '2022-02-14', 0),
    ('Colin Smith', '9080 Old Person Lane', '1985-06-10', 1);
    
    insert into vendor_t(VendorName, VendorAddress) values
    ('Anker', 'Changsha, China'),
    ('Apple', "1 Infinite Loop, CA 95014"),
    ('Rainforest Essentials', '410 Terry Ave N 98109, WA');
    
    insert into category_t(CategoryName, CategoryDescription) values
    ('Electronics', NULL),
    ('Home and Office', NULL);
    
    insert into inventory_t(Quantity, Position) values
    (100, 'A7'),
    (26, 'B14'),
    (500, 'F16'),
    (10000, 'J17'),
    (3, 'M2');
    
    insert into product_t(VendorID, InventoryID, CategoryID, ProductName, ProductPrice, ProductWeight, ImageURL) values
	(1, 2, 1, 'Anker 521 Portable Power Station 256Wh', 209.99, 10, NULL),
    (1, 5, 1, 'Anker 535 Portable Power Station 512Wh', 599.99, 12, NULL),
    (1, 3, 1, 'Anker Nano Pro USB-C Charging Block', 19.99, .75, NULL),
    (1, 1, 1, 'Anker Magnetic MagGo Wireless Charger', 119.99, 2, NULL),
    (1, 4, 1, 'Anker Nylon USB-C to Lightning Charging Cord (3.3 ft)', 209.99, 10, NULL),
    (1, 3, 1, 'Anker 521 Portable Power Station 256Wh', 17.99, .50, NULL),
    (1, 3, 1, 'Anker 521 Portable Power Station 256Wh', 209.99, 10, NULL),
    (3, 1, 2, 'Rainforest Essentials Low-Back Mesh Office Chair', 74.81, 15, NULL),
    (3, 3, 2, 'Rainforest Essentials 8-Sheet Micro-Cut Shredder', 65.97, 7.5, NULL),
    (3, 4, 2, 'Rainforest Essentials Small Digital Alarm Clock', 13.80, 1.5, 'https://alexb.tech/CS364/GroupProjectImages/alarmClock.png'),
    (3, 3, 2, 'Rainforest Essentials 8-Sheet Micro-Cut Shredder', 65.97, 7.5, 'https://alexb.tech/CS364/GroupProjectImages/shredder.png'),
    (3, 4, 1, 'Rainforest Essentials Ergonomic Wireless Mouse', 10.21, 1.5, NULL),
    (3, 4, 1, 'Rainforest Essentials High-Speed HDMI Cable (6 feet)', 8.28, .75, 'https://alexb.tech/CS364/GroupProjectImages/hdmicable.png'),
    (3, 3, 2, 'Rainforest Essentials Scissors - Pack of 3', 8.22, 1, NULL),
    (3, 3, 2, 'Rainforest Essentials 1/3-Cut File Folders - Pack Of 100', 12.03, 1.5, NULL),
    (3, 3, 2, 'Rainforest Essentials 12-Inch Laminator Machine', 34.22, 5, NULL),
    (2, 4, 1, 'Apple iPhone 13 Pro - Alpine Green 128GB', 999.00, 3, 'https://alexb.tech/CS364/GroupProjectImages/Apple13ProAlpine.png'),
    (2, 4, 1, 'Apple iPhone 13 - Green 128GB', 829.00, 2.75, 'https://alexb.tech/CS364/GroupProjectImages/Apple13Green.png'),
    (2, 4, 1, 'Apple iPhone SE(2022) - Black 64GB', 429.00, 2.25, 'https://alexb.tech/CS364/GroupProjectImages/AppleSE.png');
    
    insert into paymentMethod_t(PaymentName, CardNumber, CardExpiration, CardCCV) values
    ('Eliza''s Visa Credit', '0000111122223333', '2030-01-24', 113);
    
    insert into order_t(CustomerID, PaymentID, EmployeeID, OrderStatus, OrderDate, OrderCost, TrackingNumber) values
    (5, 1, 2, 'Processing', '2022-04-25', 906.55, 1234567);