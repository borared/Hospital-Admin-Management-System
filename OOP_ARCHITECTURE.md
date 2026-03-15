# Hospital Management System - OOP Architecture

## Overview
This project uses pure OOP concepts to simulate a database structure in-memory, inspired by the SQL schema but implemented with Java collections (no actual database).

## Architecture Layers

### 1. Entity Layer (`entity/`)
POJOs (Plain Old Java Objects) representing database tables:
- `Patient` - Patient information
- `Doctor` - Doctor details with department relationship
- `Staff` - Hospital staff members
- `Appointment` - Appointments between patients and doctors
- `Department` - Hospital departments
- `MedicalRecord` - Patient medical history
- `LabTest` - Laboratory test results
- `Billing` - Patient billing information

### 2. Repository Layer (`repository/`)
**HospitalRepository** - Singleton pattern acting as in-memory database
- Uses `HashMap<Integer, Entity>` to simulate database tables
- Auto-increment IDs simulating `AUTO_INCREMENT` in SQL
- CRUD operations for all entities
- Relationship queries (e.g., get all records for a patient)

### 3. Service Layer (`service/`)
Business logic layer that uses the repository:
- `PatientService` - Patient management operations
- `DoctorService` - Doctor management operations
- `StaffService` - Staff management operations
- `AppointmentService` - Appointment scheduling
- `DepartmentService` - Department operations
- `MedicalRecordService` - Medical history management

### 4. Controller Layer (`controller/`)
Handles user input and coordinates between views and services

### 5. View Layer (`view/`)
Console-based UI for user interaction

## Key OOP Concepts Used

### 1. Encapsulation
- Private fields with public getters/setters
- Data hiding in repository

### 2. Singleton Pattern
```java
HospitalRepository.getInstance() // Single source of truth
```

### 3. Separation of Concerns
- Entity: Data structure
- Repository: Data storage and retrieval
- Service: Business logic
- Controller: Request handling
- View: User interface

### 4. Relationships (Foreign Keys in OOP)
Instead of SQL foreign keys, we use:
```java
// Doctor has departmentId (foreign key concept)
doctor.setDepartmentId(1);

// To get department details:
Department dept = repository.getDepartment(doctor.getDepartmentId());
```

### 5. Collections as Tables
```java
// SQL: SELECT * FROM Patients
Map<Integer, Patient> patients = new HashMap<>();

// SQL: SELECT * FROM Patients WHERE patient_id = ?
Patient patient = patients.get(patientId);

// SQL: INSERT INTO Patients...
patients.put(nextId, newPatient);
```

## Data Flow Example

```
User Input → View → Controller → Service → Repository → HashMap
                                                            ↓
User Output ← View ← Controller ← Service ← Repository ← HashMap
```

## Running the Demo

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="adminmangementsystem.com.HospitalDemo"
```

## Advantages of This Approach

1. **No Database Setup** - Works immediately without MySQL/PostgreSQL
2. **Fast Development** - No SQL queries to write
3. **Easy Testing** - Data resets on each run
4. **Pure Java** - All OOP concepts, no external dependencies
5. **Learning Friendly** - Clear separation of concerns

## Simulated SQL Operations

| SQL Operation | OOP Equivalent |
|--------------|----------------|
| `INSERT INTO` | `repository.add()` |
| `SELECT * FROM` | `repository.getAll()` |
| `SELECT WHERE id = ?` | `repository.get(id)` |
| `UPDATE` | `repository.update()` |
| `DELETE` | `repository.delete()` |
| `AUTO_INCREMENT` | `nextId++` |
| `FOREIGN KEY` | Integer field referencing another entity's ID |
| `JOIN` | Manual lookup using foreign key IDs |

## Example: Simulating a JOIN

```java
// SQL: SELECT * FROM Appointments 
//      JOIN Patients ON Appointments.patient_id = Patients.patient_id
//      JOIN Doctors ON Appointments.doctor_id = Doctors.doctor_id

Appointment apt = repository.getAppointment(1);
Patient patient = repository.getPatient(apt.getPatientId());
Doctor doctor = repository.getDoctor(apt.getDoctorId());

System.out.println("Patient: " + patient.getFirstName());
System.out.println("Doctor: " + doctor.getFirstName());
System.out.println("Date: " + apt.getAppointmentDate());
```

## Future Enhancements

- Add validation in service layer
- Implement search/filter methods
- Add transaction-like operations
- Create more complex queries (GROUP BY, HAVING equivalents)
- Add data persistence (save to file)
