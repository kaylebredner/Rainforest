DROP DATABASE IF EXISTS rainforest;

CREATE DATABASE rainforest;

USE rainforest;

CREATE TABLE customer_t
(CustomerID integer NOT NULL auto_increment,
CustomerName VarChar(50) NOT NULL,
CustomerAddress varchar(50) NOT NULL,
CustomerPremium boolean NOT NULL, /*0 is false, anything else is true*/
PRIMARY KEY(customerID)
);

CREATE TABLE employee_t
(EmployeeID integer NOT NULL AUTO_INCREMENT,
EmployeeName varchar(50) NOT NULL,
EmployeeAddress varchar(25) NOT NULL,
EmployeeStartDate date NOT NULL,
IsRetired Boolean NOT NULL, /*0 is default value (not retired), anything else indicates a retiree.*/
PRIMARY KEY(EmployeeID)
);
ALTER TABLE employee_t auto_increment = 1;

CREATE TABLE vendor_t
(VendorID integer NOT NULL AUTO_INCREMENT,
VendorName varchar(50) NOT NULL,
VendorAddress varchar(50) NOT NULL,
PRIMARY KEY(VendorID)
);
ALTER TABLE vendor_t auto_increment = 1;

CREATE TABLE category_t
(CategoryID integer NOT NULL AUTO_INCREMENT,
CategoryName varchar(50) NOT NULL,
CategoryDescription text,
PRIMARY KEY(CategoryID)
);

CREATE TABLE inventory_t
(InventoryID integer NOT NULL AUTO_INCREMENT,
Quantity integer NOT NULL,
PRIMARY KEY(InventoryID)
);

CREATE TABLE payment_t
(PaymentID integer NOT NULL,
CustomerID integer NOT NULL,
PaymentName varchar(20) NOT NULL,
CardNumber varchar(16) NOT NULL,
CardExpiration date NOT NULL,
CardCCV varchar(3) NOT NULL,
PRIMARY KEY(PaymentID),
FOREIGN KEY(CustomerID) REFERENCES customer_t(CustomerID)
);
ALTER TABLE payment_t auto_increment = 1;

CREATE TABLE order_t
(OrderID integer NOT NULL AUTO_INCREMENT,
CustomerID integer NOT NULL,
EmployeeID integer NOT NULL,
OrderStatus varchar(10) NOT NULL,
OrderDate date NOT NULL,
OrderCost integer NOT NULL,
TrackingNumber integer NOT NULL,
PRIMARY KEY(OrderID),
FOREIGN KEY(CustomerID) REFERENCES customer_t(CustomerID),
FOREIGN KEY(EmployeeID) REFERENCES employee_t(EmployeeID)
);

CREATE TABLE shipmentLocation_t
(LocationID integer	NOT NULL,
OrderID integer NOT NULL,
LocationName varchar(25) NOT NULL,
Address1 varchar(25) NOT NULL,
Address2 varchar(25),
City varchar(20) NOT NULL,
State varchar(20) NOT NULL,
ZipCode varchar(5) NOT NULL,
PRIMARY KEY(LocationID),
FOREIGN KEY(OrderID) REFERENCES order_t(OrderID)
);
ALTER TABLE shipmentLocation_t auto_increment = 1;

CREATE TABLE product_t
(ProductID integer NOT NULL AUTO_INCREMENT,
VendorID integer NOT NULL,
InventoryID integer NOT NULL,
OrderID integer,
CategoryID integer NOT NULL,
ProductName varchar(75) NOT NULL,
ProductPrice decimal(13,2) NOT NULL,
ProductWeight decimal(13,2) NOT NULL,
PRIMARY KEY(ProductID),
FOREIGN KEY(VendorID) REFERENCES vendor_t(VendorID),
FOREIGN KEY(InventoryID) REFERENCES inventory_t(InventoryID),
FOREIGN KEY(OrderID) REFERENCES order_t(OrderID),
FOREIGN KEY(CategoryID) REFERENCES category_t(CategoryID)
);
ALTER TABLE product_t auto_increment = 1;

insert into customer_t(CustomerName,CustomerAddress,CustomerPremium /*has premium if value is 1, if 0 does not*/) values
	('Lester Domingo', '128 Ann Avenue, WV 26508', 0),
    ('Sharmaine Helle', '3 Fairway Court, PA 18974', 1),
    ('Sujata Mithra', '705 North Court Road, TX 78213', 0),
    ('Yvette Embla', '798 Carriage Street, CA 90008', 0),
    ('Eliza Yurena', '87 Lakewood Street, MI 48185', 0),
    ('John Adams', '100 South Devon Street, PA 19468', 1),
    ('Reese Bidziil', '4 Saxton Drive, MA 02184', 0),
    ('Martin Uttara', '986 Tower Avenue, AZ 85351', 1),
    ('Ignat Iddo', '699 S. Princeton Drive, IL 60099', 1),
    ('Glinda Herman', '4 South Court, MI 48076', 0),
    ('Aurora Emil', '60 Linden Street, FL 33470', 0),
    ('Tiah Howarth', '3309 Vesta Drive, ND 58737', 1),
    ('Asiya Graves', '3099 Eden Drive, VA 23069', 0),
    ('Reegan Hackett', '4324 Armory Road, NC 28540', 0),
    ('Tamia Dickens', '330 Mount Olive Road, GA 30030', 0),
    ('Lilly Osborn', '2898 Wolf Pen Road, CA 94107', 1),
    ('Sammy Parra', '4471 Eagle Street, IL, 62208', 0),
    ('Bethany Bowden', '3951 Caynor Circle, NJ 07477', 0),
    ('Frederic Herring', '2227 Virginia Street, IL 60607', 0),
    ('Daniele Hubbard', '1352 Gateway Avenue, CA 93307', 1),
    ('Shaan Daugherty', '2729 Dovetail Estates, OK 73951', 0),
    ('Eva Patrick', '1151 Colony Street, CT 06103', 1),
    ('Dawn Frame', '304 Trouser Leg Road, MO 65032', 0),
    ('Ruby-May Orozco', '4244 Brooklyn Street, OR 97401', 0),
    ('Tiya Howells', '3360 Melville Street, PA 18017', 0);
    
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
    
    insert into inventory_t(Quantity) values
    (100),
    (26),
    (500),
    (10000),
    (3);
    
    insert into product_t(VendorID, InventoryID, CategoryID, ProductName, ProductPrice, ProductWeight) values
	(1, 2, 1, 'Anker 521 Portable Power Station 256Wh', 209.99, 10),
    (1, 5, 1, 'Anker 535 Portable Power Station 512Wh', 599.99, 12),
    (1, 3, 1, 'Anker Nano Pro USB-C Charging Block', 19.99, .75),
    (1, 1, 1, 'Anker Magnetic MagGo Wireless Charger', 119.99, 2),
    (1, 4, 1, 'Anker Nylon USB-C to Lightning Charging Cord (3.3 ft)', 209.99, 10),
    (1, 3, 1, 'Anker 521 Portable Power Station 256Wh', 17.99, .50),
    (1, 3, 1, 'Anker 521 Portable Power Station 256Wh', 209.99, 10),
    (3, 1, 2, 'Rainforest Essentials Low-Back Mesh Office Chair', 74.81, 15),
    (3, 3, 2, 'Rainforest Essentials 8-Sheet Micro-Cut Shredder', 65.97, 7.5),
    (3, 4, 2, 'Rainforest Essentials Small Digital Alarm Clock', 13.80, 1.5),
    (3, 3, 2, 'Rainforest Essentials 8-Sheet Micro-Cut Shredder', 65.97, 7.5),
    (3, 4, 1, 'Rainforest Essentials Ergonomic Wireless Mouse', 10.21, 1.5),
    (3, 4, 1, 'Rainforest Essentials High-Speed HDMI Cable (6 feet)', 8.28, .75),
    (3, 3, 2, 'Rainforest Essentials Scissors - Pack of 3', 8.22, 1),
    (3, 3, 2, 'Rainforest Essentials 1/3-Cut File Folders - Pack Of 100', 12.03, 1.5),
    (3, 3, 2, 'Rainforest Essentials 12-Inch Laminator Machine', 34.22, 5),
    (2, 4, 1, 'Apple iPhone 13 Pro - Alpine Green 128GB', 999.00, 3),
    (2, 4, 1, 'Apple iPhone 13 - Green 128GB', 829.00, 2.75),
    (2, 4, 1, 'Apple iPhone SE(2022) - Black 64GB', 429.00, 2.25);