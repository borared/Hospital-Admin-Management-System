# Login Credentials

## Available User Roles

### 1. Manager (Full Access)
- **Username:** `admin`
- **Password:** `admin$$`
- **Permissions:** Full system access - can perform all operations
- **Menu Options:**
  - Doctor Management System
  - Patient Management System
  - Cardiologist Management
  - Surgeon Management
  - Nurse Management
  - Schedule Appointment
  - View Appointment
  - View Doctor List
  - View Patient List

### 2. Receptionist (Limited Access)
- **Username:** `receptionist`
- **Password:** `rec123`
- **Permissions:**
  - Register and update patients
  - Schedule and view appointments
  - View patient details and records
- **Menu Options:**
  - Patient Management System
  - Schedule Appointment
  - View Appointment
  - View Patient List

## OOP Concepts Implemented

### 1. Abstraction
- `User` is an abstract base class
- `IStaff` interface defines the contract for permission checking

### 2. Inheritance
- `Manager` and `Receptionist` extend `User`
- All inherit common login functionality

### 3. Polymorphism
- Each user type overrides `can()` method with specific permissions
- `authenticateUser()` method uses generics to authenticate specific user types
- `MainController` uses instanceof to route to appropriate menu handler
- User array holds different concrete types

### 4. Encapsulation
- Private fields (username, password) with public getters/setters
- Controllers encapsulate business logic
- Permission logic encapsulated in each user class
- Separate menu handlers for each role

### 5. Separation of Concerns
- Each role has its own dedicated menu and handler method
- Menu class provides role-specific menu displays
