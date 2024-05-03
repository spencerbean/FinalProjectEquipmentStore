# FinalProjectEquipmentStore
This project has four tables. An equipment_store, customers, employees, and an equipment_store_customer join table. I used AdvancedRestClient for CRUD operations. 

POST http://localhost:8080/equipment_store -> this adds a new store	
PUT http://localhost:8080/equipment_store/(enter store number here) -> this modifies a store
POST http://localhost:8080/equipment_store/(enter store number here)/employee -> adds new employee to a specific store
POST http://localhost:8080/equipment_store/(enter store number here)/customer -> adds new customer to a specific store -> also adds entry to 		the join table
GET http://localhost:8080/equipment_store -> give list of all equipment stores sans customers and employees
DELETE http://localhost:8080/equipment_store/(enter store number here) -> deletes a store and associated employees
