# SHOPPING CART APPLICATION

## About

This is a project which produces a REST API (Spring-Boot/H2 in memory database/Maven) that allows users to shop for the products and cover the following scenarios:

1) Anyone can view a product catalog
2) Anyone can view the detail of a product
3) User can create his account (Bonus-- Logout is not created yet)
4) Optional : User can be authenticated for his account (Bonus-- Logout is not created yet)
5) User can add a product to the cart after login and this indeed creates his own shopping cart 
6) User can view the contents of his cart 
7) User can also remove a product from his cart


## How to run

1) Import the project in IDE (Eclipse/Intellij)
2) Launch Java Application using "ShoppingCartApplication" class
3) Go to the web browser and visit : http://localhost:8080 (Port is configurable in '/src/main/resources/ 
   application.properties') 
   
   Note : There are some DML/DDL statements which are include in 'import-h2.sql'. 
          DML statements helps adding dummy data to work on application.  

## Explaination

### 1) Anyone can view a product catalog: 

   Host: GET http://localhost:8080/catalog/products  

   Description : This Restful webservice provides product catalog. 
              All the products are imported through DML statements while starting  
              up the application  
	
### 2) Anyone can view the detail of a product: 

   Host: GET http://localhost:8080/detail/products/{idProduct} 
         Example : http://localhost:8080/detail/products/1         

   Description : This Restful webservice provides product detail (Features etc.)  
   
### 3) User can create his account (Bonus-- Logout is not created yet): 

   Host: POST http://localhost:8080/users
         Request:
	         {
	           	 "lastName":"malhans",
	   			 "firstName":"ameen",
	             "username":"ameen@gmail.com",
	             "password":"123456"
	          } 
	      
	    Note: username must be email.  
	    
   Description : This Restful webservice creates a new user.
       
### 4) Optional : User can be authenticated for his account

   Host: POST http://localhost:8080/users/login
         Request:
	         {
	           	 "username":"ameen@gmail.com",
	             "password":"123456"
	         } 
	    
   Description : This Restful webservice login user.
   
   
### 5) User can add a product to the cart after login and this indeed creates his own shopping cart    
    
   Host: POST http://localhost:8080/users/{{idUser}}/carts?idProduct={{idProduct}}&quantity={{quantity}}
         Example : http://localhost:8080/users/1/carts?idProduct=1&quantity=1
	    
   Description : The Restful webservice add a defined product and its quantity to the shopping cart

### 6) User can view the contents of his cart 

   Host: GET http://localhost:8080/users/{{idUser}}/carts
         Example : http://localhost:8080/users/1/carts
	    
   Description : The Restful webservice is useful in getting shopping cart based on the user
   
### 7) User can also remove a product from his cart 

   Host: DELETE http://localhost:8080/users/{{idUser}}/carts/{{productId}}
         Example : http://localhost:8080/users/1/carts/1
	    
   Description : The Restful webservice is use to remove product from shopping cart


## Helper Tools

### H2 Database web interface

Go to the web browser and visit `http://localhost:8080/h2-console`

Check 'application.properties' file to see datasource url 

## Improvements

1) We can create logout functionality
2) We can enhance by adding orders to this application
3) We can perform unit and integration testing
4) WE can add more validation constrains by using hibernate. (I have shown an example while creating 
   user with required email pattern )  
5) Improve exception handling