# FinalProjectEquipmentStore
This project has four tables. An equipment_store, customers, employees, and an equipment_store_customer join table. I used AdvancedRestClient for CRUD operations. 

Title:
Equipment Store Application
 
Executive Summary:
This is an application for a store that sells tractor equipment (John Deere, Case, New Holland, etc). This project will integrate equipment stores, employees, and customers into a Spring Boot application. This will have three entities and one join table.
•	EquipmentStore - Employee: An equipment store can have many employees, and each employee belongs to one equipment store. This is a one-to-many relationship. 
•	EquipmentStore - Customer: A customer can frequent multiple stores, and an equipment store can have multiple customers. This is a many-to-many relationship, which will be facilitated by a join table.
This application will track what customers frequent what stores. It will also track which employees work for which stores.

 Initial Features:
•	List of API features:
•	Browse all places to buy equipment (Get on equipment_store)
•	Add new equipment store location (POST on equipment_store)
•	Update equipment store information (PUT on equipment_store)
•	Delete equipment store location (DELETE on equipment_store)
•	Add new employee (POST on employee)
•	Add new customer (POST on customer)
•	Add to join table equipment_store_customer (POST on customer)



POST http://localhost:8080/equipment_store -> this adds a new store	
PUT http://localhost:8080/equipment_store/(enter store number here) -> this modifies a store
POST http://localhost:8080/equipment_store/(enter store number here)/employee -> adds new employee to a specific store
POST http://localhost:8080/equipment_store/(enter store number here)/customer -> adds new customer to a specific store -> also adds entry to 		the join table
GET http://localhost:8080/equipment_store -> give list of all equipment stores sans customers and employees
DELETE http://localhost:8080/equipment_store/(enter store number here) -> deletes a store and associated employees
