# Online Reservation System

## Overview
A desktop-based train reservation system built with Java Swing and SQL Server. Users can log in, book tickets with an auto-generated PNR, and cancel bookings.

## Tech Stack
- Java (Swing GUI)
- JDBC
- SQL Server

## Features
- Login Form (username & password)
- Reservation Form — passenger details, train number with auto-populated train name, class, journey date, source/destination
- Booking with auto-generated unique PNR
- Confirmation dialog showing booking details
- Cancellation Form — fetch booking by PNR
- Confirm cancellation dialog before deleting booking

## How to Run
1. Set up the SQL Server database using the provided schema
2. Update the database connection details in DBConnection.java
3. Run LoginFrame.java from your IDE to launch the application

## Database Tables
- users
- trains
- reservations

## Theme
Green and cream color scheme, inspired by Pakistan Railways.
