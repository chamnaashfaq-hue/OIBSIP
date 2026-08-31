# Digital Library Management System

## Overview
A web-based Library Management System built with Java Servlets, JSP, and SQL Server. Includes separate Admin and User functionality for managing books, members, issues/returns, and fines.

## Tech Stack
- Java (Servlets & JSP)
- SQL Server (JDBC)
- Apache Tomcat
- HTML/CSS

## Features
- User Registration & Login
- Add / Edit / Delete Books (Admin)
- Browse & Search Book Catalogue
- Issue & Return Books
- Automatic Late Fine Calculation (₹5/day)
- Fine Management (view unpaid fines, mark as paid)
- Manage Members

## How to Run
1. Set up the SQL Server database using the provided schema
2. Update the database connection details in DBConnection.java
3. Deploy the project to Apache Tomcat
4. Access via browser at http://localhost:8080/library/

## Database Tables
- users
- books
- issued_books
- reservations
- queries
