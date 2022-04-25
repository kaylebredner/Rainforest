use rainforest;



/* RETURN ALL SAVED SHIPMENT LOCATIONS

SELECT * FROM shipmentLocation_t;

*/

/* SEARCH FOR SHIPPING LOCATION BY CUSTOMER NAME

SELECT shipmentLocation_t.LocationName, shipmentLocation_t.Address1, shipmentLocation_t.Address2, shipmentLocation_t.City, shipmentLocation_t.State, shipmentLocation_t.ZipCode
	FROM shipmentLocation_t INNER JOIN customer_t
		ON shipmentLocation_t.LocationID = customer_t.CustomerID
			WHERE shipmentLocation_t.LocationName LIKE '%x%'; (<--- X is the name of the customer you are searching for)

*/

/* RETURN SHIPPING LOCATION FROM GIVEN CUSTOMER ID

SELECT shipmentLocation_t.LocationName, shipmentLocation_t.Address1, shipmentLocation_t.Address2, shipmentLocation_t.City, shipmentLocation_t.State, shipmentLocation_t.ZipCode
	FROM shipmentLocation_t INNER JOIN customer_t
		ON shipmentLocation_t.LocationID = customer_t.CustomerID
			WHERE customer_t.customerID = X; (<-- X is customerID to search by)

*/