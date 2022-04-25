/*TO RETURN ALL CUSTOMER NAMES WITH AN ORDER
SELECT CustomerName
	FROM customer_t INNER JOIN order_t
		ON customer_t.CustomerID = order_t.CustomerID
*/
        
/*TO SEARCH FOR A SPECIFIC NAME BASED ON CUSTOMER ID
SELECT CustomerName
	FROM customer_t INNER JOIN order_t
		ON customer_t.CustomerID = order_t.CustomerID
			WHERE customer_t.CustomerID = X (<--- X is the customerID you are searching for)


*/

/*TO SEARCH FOR A SPECIFIC NAME BASED ON ORDER NUMBER
SELECT CustomerName
	FROM customer_t INNER JOIN order_t
		ON customer_t.CustomerID = order_t.CustomerID
			WHERE order_t.OrderID = X; (<--- X is the orderID you are searching)
*/