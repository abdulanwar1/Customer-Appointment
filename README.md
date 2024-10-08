# Customer-Appointment
# Appointment Management System

## Overview

The Appointment Management System is a Java-based application designed to simplify the process of managing appointments, customers, and reports within an organization. With a structured and user-friendly interface, the system allows users to seamlessly add, view, update, and organize various entities, providing an efficient way to maintain essential records and keep operations running smoothly.

## Key Features

- **English and French Translation**: The system provides functionality to translate between English and French, enhancing accessibility for multilingual users.
- **Attentive Error Handling**: Robust error handling mechanisms ensure smooth operation and prevent unexpected issues.
- **Add, Update, and View Records**: Create, modify, or view appointments, customers, and reports to ensure the database stays up to date.
- **Comprehensive Database Management**: Handle appointments, customers, contacts, countries, divisions, and users, with well-defined classes for CRUD operations and observable lists.
- **Detailed Login and Main Dashboard**: Secure login screen with a main dashboard that directs users to various forms within the project.

## Class Descriptions

The project is divided into several packages, each with dedicated classes that help maintain modularity and scalability.

### Controller Classes

These classes handle the user interface and interactions within different parts of the system.

#### 1. **addAppointment**

Controller for the Add Appointment form, allowing users to schedule new appointments.

#### 2. **addCustomer**

Controller for the Add Customer form, enabling users to add new customers to the system.

#### 3. **AppointmentView**

Controller for the Appointment View screen, displaying all appointments in the system.

#### 4. **Combos**

Controller for handling the dropdown combinations used throughout the project.

#### 5. **CustomerView**

Controller for the Customer View screen, allowing users to view customer details.

#### 6. **login**

Controller for the Login screen, used for user authentication to ensure secure access.

#### 7. **Main**

Controller for the Main screen, allowing users to navigate between different sections of the project, such as adding or updating customers and appointments.

#### 8. **report**

Controller for generating and displaying various reports to provide insights into appointments and customers.

#### 9. **updateAppointment**

Controller for the Update Appointment form, allowing users to modify details of an existing appointment.

#### 10. **updateCustomer**

Controller for the Update Customer form, enabling users to modify customer information.

### Database Classes

These classes manage the application's interaction with the database, including CRUD operations and data retrieval.

#### 1. **AppointmentsDB**

Handles all CRUD operations and observable lists related to appointments, providing essential methods for managing appointment data.

#### 2. **ContactsDB**

Manages observable lists related to contacts, providing easy retrieval of contact data.

#### 3. **CountriesDB**

Handles observable lists for countries, supporting easy selection and management of country information.

#### 4. **CustomersDB**

Responsible for all CRUD operations and observable lists for customers, managing customer records efficiently.

#### 5. **DBPS**

Utility class used throughout the project for database operations.

#### 6. **DivisionDB**

Manages CRUD operations and observable lists for divisions, ensuring efficient division data management.

#### 7. **UsersDB**

Handles observable lists and validation for users, managing user-related information securely.

### Model Classes

These classes represent the core entities in the system, making it easier to manage and maintain the data.

#### 1. **Appointments**

Represents an appointment, containing all attributes related to an individual appointment.

#### 2. **contacts**

Represents a contact, containing details about a particular contact used in the appointment system.

#### 3. **Countries**

Represents a country, containing attributes used for defining geographical data within the system.

#### 4. **Customers**

Represents a customer, with attributes needed to store customer-related information.

#### 5. **Division**

Represents a division, providing attributes for managing various divisions within a country.

#### 6. **MonthAppointment**

Represents a monthly appointment, used for organizing and reporting appointments on a monthly basis.

#### 7. **Users**

Represents a user of the system, with attributes required for authentication and role management.

## Getting Started

### Prerequisites

- Java 8 or higher
- IDE such as IntelliJ IDEA or Eclipse

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/appointment-management-system.git
   ```
2. Open the project in your preferred IDE.
3. Build and run the `Main` class to start the application.

### Usage

- **Login**: Enter your credentials to access the system.
- **Add a Customer or Appointment**: Navigate to the respective forms to add new customers or appointments.
- **Update Information**: Select an entry from the Appointment or Customer View screens and modify it using the respective forms.
- **Generate Reports**: Use the reports section to generate insights into the current state of appointments and customer records.

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/new-feature`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature/new-feature`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.

##
