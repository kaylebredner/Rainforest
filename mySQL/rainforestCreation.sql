DROP DATABASE rainforest;

CREATE DATABASE rainforest;

USE rainforest;

CREATE TABLE customer_t
(CustomerID integer NOT NULL,
CustomerName VarChar(50) NOT NULL,
CustomerAddress varchar(25) NOT NULL,
CustomerPremium boolean NOT NULL, /*0 is false, anything else is true*/
PRIMARY KEY(customerID)
);
ALTER TABLE customer_t auto_increment = 1;

CREATE TABLE employee_t
(EmployeeID integer NOT NULL,
EmployeeName varchar(50) NOT NULL,
EmployeeAddress varchar(25) NOT NULL,
EmployeeStartDate date NOT NULL,
IsRetired Boolean NOT NULL, /*0 is default value (not retired), anything else indicates a retiree.*/
PRIMARY KEY(EmployeeID)
);
ALTER TABLE employee_t auto_increment = 1;

CREATE TABLE vendor_t
(VendorID integer NOT NULL,
VendorName varchar(50) NOT NULL,
VendorAddress varchar(50) NOT NULL,
PRIMARY KEY(VendorID)
);
ALTER TABLE vendor_t auto_increment = 1;

CREATE TABLE category_t
(CategoryID integer NOT NULL,
CategoryName varchar(50) NOT NULL,
CategoryDescription text NOT NULL,
PRIMARY KEY(CategoryID)
);
ALTER TABLE category_t auto_increment = 1;

CREATE TABLE inventory_t
(InventoryID integer NOT NULL,
Quantity integer NOT NULL,
PRIMARY KEY(InventoryID)
);
ALTER TABLE inventory_t auto_increment = 1;

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
(OrderID integer NOT NULL,
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
ALTER TABLE order_t auto_increment = 1;

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
(ProductID integer NOT NULL,
VendorID integer NOT NULL,
InventoryID integer NOT NULL,
OrderID integer NOT NULL,
CategoryID integer NOT NULL,
ProductName varchar(25) NOT NULL,
ProductPrice decimal NOT NULL,
ProductWeight decimal NOT NULL,
ProductImage blob,
PRIMARY KEY(ProductID),
FOREIGN KEY(VendorID) REFERENCES vendor_t(VendorID),
FOREIGN KEY(InventoryID) REFERENCES inventory_t(InventoryID),
FOREIGN KEY(OrderID) REFERENCES order_t(OrderID),
FOREIGN KEY(CategoryID) REFERENCES category_t(CategoryID)
);
ALTER TABLE product_t auto_increment = 1;