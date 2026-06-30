# Car Rental Management System

A desktop application for managing a car rental service, developed in Java.

The system provides a graphical user interface built with Java Swing and stores its data locally in CSV files. It supports employee login, vehicle and customer management, rentals, returns, searches, and rental-history tracking.

---

## Features

### Employee Management
- Employee login system
- Add new employee accounts
- Remove existing employee accounts
- Store employee data in `users.csv`

### Vehicle Management
- Add new vehicles
- Edit vehicle details
- Search vehicles using multiple criteria
- Track vehicle availability status
- Store vehicle data in `vehicles_with_plates.csv`

### Customer Management
- Add new customers
- Edit customer details
- Search customers by personal information
- Validate unique customer AFM values
- Store customer data in `customers.csv`

### Rental Management
- Create new rentals
- Select an available vehicle
- Search and select a customer using AFM
- Register rental start and end dates
- Assign the logged-in employee to the rental
- Automatically update the vehicle availability status
- Store rental records in `rentings.csv`

### Returns and History
- Return rented vehicles and make them available again
- View rental history by customer AFM
- View rental history by vehicle ID

---

## Technologies Used

- Java
- Java Swing
- Object-Oriented Programming
- CSV files for local data persistence
- IntelliJ IDEA or Visual Studio Code for development

---

## Project Structure

```text
car-rental-management-java/
│
├── api/                            # Application logic and data classes
│   ├── Main.java                   # Application entry point
│   ├── Car.java
│   ├── Customer.java
│   ├── Employee.java
│   ├── Renting.java
│   ├── Database.java
│   ├── CarDatabase.java
│   ├── CustomerDatabase.java
│   ├── EmployeeDatabase.java
│   ├── RentingDatabase.java
│   ├── CSVReader.java
│   └── CSVWriter.java
│
├── gui/                            # Java Swing graphical interface
│   ├── WindowFrame.java
│   ├── LoginPanel.java
│   ├── MenuPanel.java
│   ├── AddEditCarPanel.java
│   ├── AddEditCustomerPanel.java
│   ├── SearchCarPanel.java
│   ├── SearchCustomerPanel.java
│   ├── RentingPanel.java
│   ├── ReturnCarPanel.java
│   ├── CustomerHistoryRentingPanel.java
│   ├── CarHistoryRentingPanel.java
│   └── AddRemoveUserPanel.java
│
├── users.csv                       # Employee accounts
├── customers.csv                   # Customer records
├── vehicles_with_plates.csv        # Vehicle records
├── rentings.csv                    # Rental records
└──README.md
