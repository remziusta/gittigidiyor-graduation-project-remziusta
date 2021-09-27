# CREDIT APPLICATION MANAGER API

This API was made as a graduation project of Gittigidiyor Java Spring Bootcamp.

API consists of 4 main modules.These modules are designed with the microservice architecture in mind.
##### These modules :
- Discovery server
- Customer service
- Score service
- Feign client

##### Used technologies :
- Spring Boot Web
- Spring Boot Data MongoDB
- Spring Boot Cloud
    - Eureka Server
    - Eureka Discovery Client
    - OpenFeign
- Spring Boot Actuator
- Spring Boot Validation
- SpringFox Swagger API
- Project Lombok
- Maven Jib Plugin
- Maven Resources Plugin
- Maven Frontend Plugin
- Docker
- React

## Features

- You can add, delete, update customers and claim all customers. You can also query according to the customer identification number.
- You can apply for a loan with the customer identification number of the customers registered in the system.
- Or you can apply for a loan with a new client.
- You can query all loan applications made by customers with their ID number.

##### NOTE
&nbsp;
> Consider that the customer ID number is unique.

&nbsp;

# INSTALLATION
##### First clone the project repository to your local.
&nbsp;
```sh
$ git clone https://github.com/113-GittiGidiyor-Java-Spring-Bootcamp/gittigidiyor-graduation-project-remziusta.git
```
&nbsp;

Run the following command in the project folder you cloned to your local:
```sh
$ docker-compose up
```

You can use the project shortly after running this command.


# Purposes of the services

### Discover-Server
This module is of great importance in the project. It provides server service that forwards the distributed system from a single point. It records for incoming services and tells the url and port information of the requested service when any computer requests it.

### Customer-Service
This module provides customer management and handles customer-only requests.

You can do endpoint tests of this service from this address:

```sh
http://localhost:9782/swagger-ui.html
```

The endpoints of this module are as follows.

# Customer Service Path
| Request 	|     Route     	            |                                 Body                                 	                            |   Description   	|
|:-------:	|:-----------------------------:|:-------------------------------------------------------------------------------------------------:|:---------------:	|
|   GET   	|:9782/customer    	            |                                 EMPTY                                	                            | List all customer |
|   GET   	|:9782/customer/{:nationalId} 	|                                 EMPTY                                	                            |   Get a customer  |
|   POST  	|:9782/customer            	    | { firstName:firstName, lastName:lastName, monthlyIncome:monthlyIncome, nationalId:nationalId }    |   Add a customer  |
|   PUT   	|:9782/customer/{:nationalId}   | { firstName:firstName, lastName:lastName, monthlyIncome:monthlyIncome} 	                        | Update a customer |
|  DELETE 	|:9782/customer/{:nationalId} 	|                                 EMPTY                                	                            | Delete a customer |

### Score-Service
This module manages loan applications with an national id or with a new customer.

You can do endpoint tests of this service from this address:

```sh
http://localhost:9783/swagger-ui.html
```

The endpoints of this module are as follows.

# Score Service Path
| Request 	|     Route     	    |                                 Body                                 	                            |   Description   	                         |
|:-------:	|:---------------------:|:-------------------------------------------------------------------------------------------------:|:------------------------------------------:|
|   POST  	|:9783/request            	| { firstName:firstName, lastName:lastName, monthlyIncome:monthlyIncome, nationalId:nationalId }|Add a new request with new customer         |
|   POST   	|:9783/request/{:nationalId} |  	                              EMPTY                                                     |Lists the applications made by the customer |
|   POST  	|:9783/sms            	    | { status:status, limit:limit, nationalId:nationalId }                                         |Credit request send sms                     |

### Feign-Client
This module is a client that acts as a bridge between the front-end and other services.

You can do endpoint tests of this service from this address:

```sh
http://localhost:9781/swagger-ui.html
```

The endpoints of this module are as follows.

# Feign Client Path
| Request 	|     Route     	                                 |                                 Body                                 	                             |   Description   	                         |
|:-------:	|:--------------------------------------------------:|:-----------------------------------------------------------------------------------------------------:|:-----------------------------------------:|
|   POST  	|:9781/score/requestCreditNewCustomer            	 | { firstName:firstName, lastName:lastName, monthlyIncome:monthlyIncome, nationalId:nationalId }        |Add a new request with new customer        |
|   POST   	|:9781/score/requestCreditByNationalId/{nationalId}  |  	                          EMPTY                                                                  |Add a new request with nationalId          |
|   POST  	|:9781/score/sms            	                     | { status:status, limit:limit, nationalId:nationalId }                                                 |Credit request send sms                    |
|   POST  	|:9781/score/getRequestByNationalId/{nationalId}     | 	                              EMPTY                                                                  |Lists the applications made by the customer|
|   GET   	|:9781/customer    	                                 |                                 EMPTY                                	                             |List all customer                          |
|   GET   	|:9781/customer/{:nationalId} 	                     |                                 EMPTY                                	                             |Get a customer                             |
|   POST  	|:9781/customer            	                         | { firstName:firstName, lastName:lastName, monthlyIncome:monthlyIncome, nationalId:nationalId }        |Add a customer                             |
|   PUT   	|:9781/customer/{:nationalId}                        | { firstName:firstName, lastName:lastName, monthlyIncome:monthlyIncome} 	                             |Update a customer                          |
|  DELETE 	|:9781/customer/{:nationalId} 	                     |                                 EMPTY                                	                             |Delete a customer                          |
