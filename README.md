# Car Rental Management System

A Java desktop application for managing a car rental service through a graphical user interface.

The application uses **Java Swing** for the interface and **CSV files** for local data storage.

## Features

- Employee login
- Add and edit vehicles
- Add and edit customers
- Search vehicles and customers
- Create new car rentals
- Return rented vehicles
- View rental history by customer
- View rental history by vehicle
- Add and remove employees
- Automatic saving of changes to CSV files

## Technologies

- Java
- Java Swing
- CSV files for data storage

## Project Structure

```text
car-rental-management-java/
│
├── api/                    # Application logic and data classes
│   ├── Main.java
│   ├── Car.java
│   ├── Customer.java
│   ├── Employee.java
│   ├── Renting.java
│   ├── CarDatabase.java
│   ├── CustomerDatabase.java
│   ├── EmployeeDatabase.java
│   ├── RentingDatabase.java
│   ├── CSVReader.java
│   └── CSVWriter.java
│
├── gui/                    # Java Swing user interface
│   ├── LoginPanel.java
│   ├── MenuPanel.java
│   ├── RentingPanel.java
│   ├── ReturnCarPanel.java
│   ├── SearchCarPanel.java
│   ├── SearchCustomerPanel.java
│   └── Other GUI panels
│
├── users.csv
├── customers.csv
├── vehicles_with_plates.csv
├── rentings.csv
└── README.md
