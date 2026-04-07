# 🏗️ Project Structure and Components Overview

## 📁 Project Organization

This Hospital Admin Management System follows a **layered architecture** with clear separation of concerns, implementing the **MVC (Model-View-Controller)** pattern and **SOLID principles**.

```
Hospital-Admin-Management-System/
├── src/main/java/adminmangementsystem/com/
│   ├── App.java                    # Main application entry point
│   ├── Menu.java                   # UI menu definitions
│   ├── Validator.java              # Input validation utilities
│   │
│   ├── controller/                 # Business Logic Layer
│   │   ├── DoctorController.java   # Doctor management operations
│   │   ├── PatientController.java  # Patient management operations
│   │   ├── AppointmentController.java # Appointment scheduling
│   │   └── MainController.java     # Main app controller & menu routing
│   │
│   ├── management/                 # Data Management Layer
│   │   ├── DoctorSystem.java       # Doctor CRUD operations
│   │   ├── PatientSystem.java      # Patient CRUD operations
│   │   ├── AppointmentService.java # Appointment management
│   │   ├── StaffSystem.java        # General staff operations
│   │   ├── IDoctorSystem.java      # Doctor system interface
│   │   ├── IPatientSystem.java     # Patient system interface
│   │   └── IAppointmentService.java # Appointment service interface
│   │
│   ├── model/                      # Data Model Layer
│   │   ├── Staff.java              # Abstract staff base class
│   │   ├── Doctor.java             # Doctor model (extends Staff)
│   │   ├── Patient.java            # Patient model
│   │   ├── Appointment.java        # Appointment model
│   │   ├── Cardiologist.java       # Cardiologist model (extends Doctor)
│   │   ├── Surgeon.java            # Surgeon model (extends Doctor)
│   │   └── Nurse.java              # Nurse model (extends Doctor)
│   │
│   ├── user/                       # User Management
│   │   ├── User.java               # Abstract user base class
│   │   ├── Manager.java            # Admin user type
│   │   ├── Receptionist.java       # Receptionist user type
│   │   └── IStaff.java             # User permission interface
│   │
│   └── view/                       # Presentation Layer
│       ├── DoctorView.java         # Doctor input/output handling
│       ├── PatientView.java        # Patient input/output handling
│       ├── AppointmentView.java    # Appointment input/output handling
│       ├── CardiologistView.java   # Cardiologist input/output handling
│       └── SurgeonView.java        # Surgeon input/output handling
│
├── src/test/java/adminmangementsystem/com/
│   └── AppTest.java                 # Unit tests
│
├── target/                          # Build output directory
├── pom.xml                          # Maven project configuration
└── README.md                        # Project documentation
```

## 🏛️ Architecture Components

### 1. **Entry Point (`App.java`)**

- **Function**: Main application launcher
- **Responsibilities**:
  - Initialize system components
  - Handle user authentication
  - Start main controller loop
- **Key Features**: User login system with Manager/Receptionist roles

### 2. **Controller Layer**

- **Function**: Business logic coordination and menu navigation
- **Responsibilities**:
  - Handle user input routing
  - Coordinate between View and Management layers
  - Implement role-based access control
  - Manage application flow and navigation

**Key Controllers:**

- `MainController`: Central hub, handles login/logout and main menu
- `DoctorController`: Doctor CRUD operations and specialized views
- `PatientController`: Patient management operations
- `AppointmentController`: Appointment scheduling and viewing

### 3. **Management Layer**

- **Function**: Data operations and business rules
- **Responsibilities**:
  - CRUD operations for entities
  - Data validation and business logic
  - File I/O operations
  - Search and filtering functionality

**Key Management Classes:**

- `DoctorSystem`: Doctor data management
- `PatientSystem`: Patient data management
- `AppointmentService`: Appointment scheduling logic

### 4. **Model Layer**

- **Function**: Data representation and business entities
- **Responsibilities**:
  - Define data structures
  - Implement business rules
  - Handle data validation
  - Provide display methods

**Inheritance Hierarchy:**

```
Staff (abstract)
├── Doctor
    ├── Cardiologist
    ├── Surgeon
    └── Nurse

Patient (separate hierarchy)
Appointment (composition with Patient)
```

### 5. **View Layer**

- **Function**: User interface and input/output handling
- **Responsibilities**:
  - Handle user input collection
  - Format and display data
  - Input validation
  - Menu presentation

### 6. **User Management**

- **Function**: Authentication and authorization
- **Responsibilities**:
  - User authentication
  - Role-based permissions
  - Session management

## 🔄 Data Flow Architecture

```
User Input → Controller → Management → Model
     ↑             ↓           ↓
     ← View ← Display ← Data Access ←
```

## 🎯 Key Design Patterns Applied

### **MVC Pattern**

- **Model**: Data entities and business logic
- **View**: User interface components
- **Controller**: Business logic coordination

### **Layered Architecture**

- Clear separation between UI, business logic, and data access
- Each layer has specific responsibilities
- Easy to maintain and extend

### **Inheritance Hierarchy**

- `Staff` → `Doctor` → Specialized doctor types
- Enables code reuse and polymorphism
- Type-safe operations

### **Composition**

- Controllers compose Management classes
- Management classes compose Model objects
- Loose coupling between components

### **Interface Segregation**

- `IDoctorSystem`, `IPatientSystem`, `IAppointmentService`
- Clean contracts between layers
- Easy to mock for testing

## 🚀 System Features

### **Authentication System**

- Role-based login (Manager/Receptionist)
- Different access levels and menus

### **Doctor Management**

- CRUD operations for all doctor types
- Specialized views for Cardiologists, Surgeons, Nurses
- Search and filtering capabilities

### **Patient Management**

- Complete patient record management
- Appointment scheduling integration

### **Appointment System**

- Schedule appointments with existing patients
- View appointment history
- Integration with patient management

## 🛡️ OOP Principles Implementation

### **Encapsulation**

- Private fields with public getters/setters
- Data hiding and controlled access

### **Inheritance**

- `Staff` base class for all staff types
- `Doctor` base class for medical professionals
- Code reuse and polymorphism

### **Polymorphism**

- Runtime type checking in controllers
- Different behavior based on user roles
- Abstract methods in base classes

### **Abstraction**

- Abstract classes (`Staff`, `User`)
- Interfaces for contracts
- Hide implementation details

### **Composition**

- Controllers contain Management objects
- Management objects contain Model collections
- Flexible object relationships

## 📊 Benefits of This Architecture

1. **Maintainability**: Clear separation makes code easy to modify
2. **Extensibility**: New features can be added without affecting existing code
3. **Testability**: Each layer can be tested independently
4. **Reusability**: Components can be reused across different contexts
5. **Security**: Role-based access control prevents unauthorized operations

This architecture provides a solid foundation for a scalable hospital management system with proper separation of concerns and adherence to object-oriented design principles.
