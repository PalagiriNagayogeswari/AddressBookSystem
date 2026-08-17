# Address Book System

## Overview

Address Book System is a Java-based web application developed to manage employee and payroll information. The application provides REST APIs for performing employee management and salary-related operations.

The project is developed using Java, JDBC, PostgreSQL, and Apache Tomcat. Database connectivity is implemented using standard JDBC with manual database connection management without using `DataSource` or `JdbcTemplate`.

The application follows a layered architecture with separate Controller, Service, DAO, and Model components.

## Technologies Used

* Java
* JDBC
* PostgreSQL
* Spring MVC
* REST APIs
* Maven
* Apache Tomcat
* JSON
* Git and GitHub

## Architecture

The application follows a layered architecture:

```text
Client
   |
   v
Controller
   |
   v
Service
   |
   v
DAO
   |
   v
JDBC
   |
   v
PostgreSQL Database
```

### Controller Layer

The Controller layer handles HTTP requests and maps them to the appropriate application operations.

### Service Layer

The Service layer contains the business logic and acts as an intermediate layer between the Controller and DAO.

### DAO Layer

The DAO layer is responsible for database operations. Standard JDBC APIs are used to establish database connections, prepare SQL statements, execute queries, and process `ResultSet` objects.

### Model Layer

The Model layer contains Java classes representing the application's data, such as employees and payroll information.

## Database Connectivity

The application uses standard JDBC for database communication.

Database connections are established manually using `DriverManager` rather than using:

* `JdbcTemplate`
* `DataSource`
* Connection pooling

The typical JDBC flow is:

```text
Load JDBC Driver
      |
      v
DriverManager.getConnection()
      |
      v
Create PreparedStatement
      |
      v
Execute SQL Query
      |
      v
Process ResultSet
      |
      v
Close Resources
```

`PreparedStatement` is used for executing parameterized SQL queries and helps prevent SQL injection.

## Employee Use Cases

| ID    | Use Case          | Endpoint                        | Method |
| ----- | ----------------- | ------------------------------- | ------ |
| UC-01 | Add Employee      | `/employees`                    | POST   |
| UC-02 | Get Employee      | `/employees/{id}`               | GET    |
| UC-03 | Get All Employees | `/employees`                    | GET    |
| UC-04 | Update Employee   | `/employees/{id}`               | PUT    |
| UC-05 | Delete Employee   | `/employees/{id}`               | DELETE |
| UC-06 | Search Employee   | `/employees/search?name={name}` | GET    |

### Add Employee

```http
POST /employees
```

Creates a new employee in the database.

### Get Employee

```http
GET /employees/{id}
```

Retrieves an employee using the employee ID.

### Get All Employees

```http
GET /employees
```

Retrieves all employees.

### Update Employee

```http
PUT /employees/{id}
```

Updates the details of an existing employee.

### Delete Employee

```http
DELETE /employees/{id}
```

Deletes an employee using the employee ID.

### Search Employee

```http
GET /employees/search?name={name}
```

Searches for employees based on their name.

## Salary and Payroll Use Cases

| ID    | Use Case             | Endpoint                  | Method |
| ----- | -------------------- | ------------------------- | ------ |
| UC-07 | Calculate Salary     | `/employees/{id}/salary`  | GET    |
| UC-08 | Generate Payroll     | `/payroll`                | POST   |
| UC-09 | Get Employee Payroll | `/employees/{id}/payroll` | GET    |
| UC-10 | Get Payroll History  | `/payroll/history`        | GET    |
| UC-11 | Delete Payroll       | `/payroll/{id}`           | DELETE |

### Calculate Salary

```http
GET /employees/{id}/salary
```

Calculates the salary of a specific employee based on the application's salary calculation logic.

### Generate Payroll

```http
POST /payroll
```

Generates payroll information for employees.

### Get Employee Payroll

```http
GET /employees/{id}/payroll
```

Retrieves payroll information associated with a specific employee.

### Get Payroll History

```http
GET /payroll/history
```

Retrieves the payroll history maintained by the application.

### Delete Payroll

```http
DELETE /payroll/{id}
```

Deletes a payroll record using its ID.

## API Summary

### Employee APIs

```text
POST    /employees
GET     /employees/{id}
GET     /employees
PUT     /employees/{id}
DELETE  /employees/{id}
GET     /employees/search?name={name}
```

### Salary and Payroll APIs

```text
GET     /employees/{id}/salary
POST    /payroll
GET     /employees/{id}/payroll
GET     /payroll/history
DELETE  /payroll/{id}
```

## Key Concepts Demonstrated

* Java programming
* Spring MVC
* REST API development
* JDBC
* Manual database connection management
* `DriverManager`
* `Connection`
* `PreparedStatement`
* `ResultSet`
* SQL queries
* CRUD operations
* Employee management
* Salary calculation
* Payroll management
* Layered architecture
* Dependency Injection
* Exception handling
* JSON request and response handling
* Maven
* WAR deployment
* Apache Tomcat
* PostgreSQL

## Prerequisites

Before running the application, make sure the following are installed:

* JDK
* Maven
* PostgreSQL
* Apache Tomcat
* IntelliJ IDEA, Eclipse, or Spring Tool Suite
* Postman or another REST API testing tool

## Database Configuration

Configure the PostgreSQL database connection according to the local environment.

The JDBC connection follows the standard format:

```text
jdbc:postgresql://localhost:5432/<database-name>
```

The application uses JDBC to establish connections through `DriverManager`.

Example:

```java
Connection connection = DriverManager.getConnection(
        url,
        username,
        password
);
```

Update the database URL, username, password, and database name according to the local PostgreSQL configuration.

## Running the Application

### 1. Clone the Repository

```bash
git clone https://github.com/PalagiriNagayogeswari/AddressBookSystem.git
```

### 2. Navigate to the Project

```bash
cd AddressBookSystem
```

### 3. Configure PostgreSQL

Create the required PostgreSQL database and tables used by the application.

### 4. Configure Database Credentials

Update the JDBC database configuration with the PostgreSQL database URL, username, and password.

### 5. Build the Application

```bash
mvn clean install
```

### 6. Deploy to Tomcat

Deploy the generated WAR file to Apache Tomcat.

### 7. Start the Application

Start the Tomcat server and use Postman to test the REST APIs.

## API Testing

The APIs can be tested using Postman or any REST API client.

Example employee request:

```http
POST /employees
Content-Type: application/json
```

Example salary request:

```http
GET /employees/1/salary
```

Example payroll request:

```http
POST /payroll
Content-Type: application/json
```

## Learning Outcomes

This project provides practical experience in building a Java web application using Spring MVC and JDBC. It demonstrates how REST APIs communicate with service and DAO layers and how JDBC can be used to manually establish database connections and perform SQL operations without relying on `JdbcTemplate` or `DataSource`.

## Author

**Palagiri Nagayogeswari**

GitHub: https://github.com/PalagiriNagayogeswari

## License

This project is developed for learning and educational purposes.
