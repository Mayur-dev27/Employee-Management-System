# Employee-Management-System

This project is an Employee Management System built using Hibernate ORM, which performs various CRUD operations and provides functionalities like aggregate functions and HQL/native queries.

## Features:
CRUD Operations: Create, Read, Update, Delete employee records.
HQL Queries: Execute HQL queries to fetch and manipulate employee data.
Native Queries: Execute native SQL queries for specific operations.
Aggregate Functions: Perform aggregate functions such as total, average, min, and max salaries.
## Technologies Used:
Java: Core language used for development.
Hibernate ORM: For ORM mapping and database interaction.
MySQL: Database used for storing employee information.
Maven: Dependency management and build automation tool.
## Project Structure:
beans: Contains entity classes representing the database tables.
Employee
EmployeeName
EmployeeResources
EmployeeSalary
curdoperations: Contains the class for performing CRUD operations.
EmployeeCURDOprtn
hqlclauses: Contains the class for executing HQL queries.
HQLQueries
aggregate: Contains the class for performing aggregate functions.
AggregateFunc
nativequeries: Contains the class for executing native SQL queries.
NativeQueries
resources: Contains utility classes and configurations.
Helper
services: Contains the main class to run the application.
Main





## Usage
The application provides a menu-driven interface for performing various operations:

# CRUD Operations:

Create a new employee.
Read an employee's details by ID.
Update an employee's details.
Delete an employee.
# HQL Queries:

Fetch employee name and salary by ID.
Fetch employees with salary greater than a specified amount.
Update salary by employee ID.
Delete employee resources by employee ID.
List employees ordered by salary in descending order.
# Native Queries:

Fetch employee details by ID using native SQL.
Update salary by employee ID using native SQL.
Delete employee resources by employee ID using native SQL.
# Aggregate Functions:

Get total salary of all employees.
Get average salary of all employees.
Get count of all employees.
Get minimum salary of all employees.
Get maximum salary of all employees.

## Contributing
Contributions are welcome! Please feel free to submit a Pull Request or open an Issue for any improvements or bug fixes.


