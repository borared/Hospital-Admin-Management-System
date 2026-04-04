# Oral Exam Preparation - Sets 22-26

## Set 22

### Q1 (60): Why is it useful for a class to have one clear responsibility?

A class with one clear responsibility is easier to understand, test, modify, and reuse. When a class does only one thing, you know exactly where to look when something breaks, and changes to that responsibility don't affect unrelated functionality. This is the Single Responsibility Principle (SRP).

### Q2 (80): Show one class in your project and explain its main responsibility.

```java
public class PatientSystem implements IPatientSystem {
    private List<Patient> patients = new ArrayList<>();
    
    public void addPatient(Patient patient) { /* ... */ }
    public void updatePatient(String id, Patient updatedPatient) { /* ... */ }
    public void deletePatient(String id) { /* ... */ }
    public Patient findPatientById(String id) { /* ... */ }
    public void displayAllPatients() { /* ... */ }
}
```

**Main responsibility**: Manage the collection of patients - adding, updating, deleting, finding, and displaying patient records. It's the single source of truth for all patient data operations.

### Q3 (100): Identify one class that may currently do too much and explain how you would redesign it.

**Problem class: AppointmentController**

Currently it might be doing:
- Handling user input (menu navigation)
- Validating appointment data
- Calling AppointmentService
- Displaying results

**Redesign approach:**

```java
// BEFORE: Controller does too much
public class AppointmentController {
    public void scheduleAppointment() {
        // Gets input
        // Validates data
        // Creates appointment
        // Displays result
    }
}

// AFTER: Split responsibilities

// 1. Controller - only handles flow
public class AppointmentController {
    private AppointmentService service;
    private AppointmentView view;
    
    public void scheduleAppointment() {
        Appointment apt = view.getAppointmentInput();
        service.scheduleAppointment(apt);
        view.displaySuccess();
    }
}

// 2. View - handles input/output
public class AppointmentView {
    public Appointment getAppointmentInput() { /* ... */ }
    public void displaySuccess() { /* ... */ }
}

// 3. Service - handles business logic
public class AppointmentService {
    public void scheduleAppointment(Appointment apt) { /* ... */ }
}
```

This separates concerns: Controller orchestrates, View handles UI, Service handles logic.

---

## Set 23

### Q1 (60): What is cohesion, and how is it related to good class design?

Cohesion measures how closely related the methods and data in a class are to each other. High cohesion means everything in the class works toward the same purpose. Good class design has high cohesion - all methods and fields are focused on one responsibility, making the class easier to understand and maintain.

### Q2 (80): Show one class in your project that has good cohesion.

```java
public class Patient {
    // All fields relate to patient information
    private String id;
    private String name;
    private String dob;
    private String address;
    private String email;
    private String medicalHistory;
    
    // All methods work with patient data
    public String getId() { return id; }
    public void setName(String name) { this.name = name; }
    public void display() { /* shows patient info */ }
}
```

**Why it has good cohesion**: Every field and method is about patient information. Nothing unrelated (like appointment scheduling or doctor management) is mixed in. The class is focused and cohesive.

### Q3 (100): Explain how weak cohesion can make a class harder to test, understand, or extend.

**Example of weak cohesion:**

```java
// BAD: Low cohesion - does unrelated things
public class PatientManager {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private EmailService emailService;
    
    public void addPatient(Patient p) { /* ... */ }
    public void sendEmailToDoctor(Doctor d) { /* ... */ }
    public void generateReport() { /* ... */ }
    public void validateInsurance() { /* ... */ }
}
```

**Problems:**

1. **Harder to test**: To test patient operations, you need to mock email service and insurance validation - unrelated dependencies

2. **Harder to understand**: New developers can't tell what this class is for - patient management? doctor communication? reporting?

3. **Harder to extend**: If you want to change email logic, you risk breaking patient operations. Changes have unpredictable side effects

4. **Harder to reuse**: Can't reuse just the patient management part without dragging in email and insurance code

5. **Multiple reasons to change**: Email format changes, insurance rules change, patient fields change - all require modifying this one class

**Better design**: Split into PatientSystem, EmailService, ReportGenerator, InsuranceValidator - each with high cohesion.

---

## Set 24

### Q1 (60): What does loose coupling mean, and why is it valuable in object-oriented design?

Loose coupling means classes have minimal dependencies on each other's internal details. They interact through well-defined interfaces rather than knowing about each other's implementation. This is valuable because changes to one class don't ripple through the entire system - you can modify or replace components independently.

### Q2 (80): Identify two parts of your project that should not depend too tightly on each other.

**Example 1: Controller and Service**

```java
// GOOD: Loose coupling through interface
public class AppointmentController {
    private IAppointmentService service;  // Depends on interface, not concrete class
    
    public AppointmentController(IAppointmentService service) {
        this.service = service;
    }
}
```

Controller doesn't know if it's using AppointmentService, DatabaseAppointmentService, or MockAppointmentService - it only knows the interface.

**Example 2: User and IStaff**

```java
// User depends on IStaff interface, not concrete Manager/Receptionist
public abstract class User implements IStaff {
    // Can work with any IStaff implementation
}
```

This allows adding new staff types without changing User.

### Q3 (100): Explain how interfaces, better method design, or clearer responsibilities could reduce coupling there.

**Strategy 1: Use interfaces**

```java
// BEFORE: Tight coupling
public class MainController {
    private AppointmentService appointmentService = new AppointmentService();
    // Directly depends on concrete class
}

// AFTER: Loose coupling
public class MainController {
    private IAppointmentService appointmentService;
    
    public MainController(IAppointmentService service) {
        this.appointmentService = service;  // Depends on interface
    }
}
```

Now MainController doesn't care about AppointmentService implementation details.

**Strategy 2: Dependency injection**

```java
// Pass dependencies through constructor instead of creating them internally
public class DoctorController {
    private IDoctorSystem doctorSystem;
    
    public DoctorController(IDoctorSystem system) {
        this.doctorSystem = system;  // Injected, not created
    }
}
```

**Strategy 3: Clear responsibilities**

Instead of Controller directly accessing Patient fields:
```java
// BAD: Tight coupling
String name = patient.name;  // Directly accessing field

// GOOD: Loose coupling
String name = patient.getName();  // Through method
```

**Benefits:**
- Can change internal implementation without breaking other classes
- Easier to test with mock objects
- Can swap implementations at runtime
- Changes are localized, not system-wide

---

## Set 25

### Q1 (60): What is the difference between an instance field and a static field?

**Instance field**: Each object has its own copy. If you create 5 Patient objects, each has its own `name` field.

**Static field**: Shared by all objects of the class. There's only one copy, no matter how many objects you create. It belongs to the class itself, not to individual objects.

### Q2 (80): Show one place in your project where an instance variable makes more sense than a static variable.

```java
public class Patient {
    private String id;        // Instance field - each patient has their own ID
    private String name;      // Instance field - each patient has their own name
    private String email;     // Instance field - each patient has their own email
    
    public Patient(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}

// Usage:
Patient p1 = new Patient("P001", "John", "john@email.com");
Patient p2 = new Patient("P002", "Jane", "jane@email.com");
// p1 and p2 have different names - instance fields are correct
```

Instance fields make sense here because each patient is a unique individual with their own information.

### Q3 (100): Explain a case where using static incorrectly would cause wrong shared behavior between objects.

**Bad example: Using static for patient data**

```java
// WRONG: Using static
public class Patient {
    private static String name;  // SHARED by all patients!
    
    public Patient(String name) {
        this.name = name;  // Overwrites for ALL patients
    }
    
    public String getName() {
        return name;
    }
}

// Problem:
Patient p1 = new Patient("John");
Patient p2 = new Patient("Jane");

System.out.println(p1.getName());  // Prints "Jane" - WRONG!
System.out.println(p2.getName());  // Prints "Jane" - correct
```

**What went wrong:**
- `name` is static, so there's only ONE name shared by all Patient objects
- When we create p2 with "Jane", it overwrites the shared name
- Now p1.getName() returns "Jane" instead of "John"
- All patients have the same name!

**When static IS appropriate:**

```java
public class Patient {
    private static int patientCount = 0;  // Shared counter - makes sense
    private String name;                   // Instance field - unique per patient
    
    public Patient(String name) {
        this.name = name;
        patientCount++;  // Increment shared counter
    }
    
    public static int getTotalPatients() {
        return patientCount;
    }
}
```

Here, `patientCount` should be static because we want ONE counter for ALL patients, not a separate counter for each patient.

---

## Set 26

### Q1 (60): What is the difference between an instance method and a static method?

**Instance method**: Operates on a specific object's data. You must have an object to call it: `patient.getName()`. It can access instance fields.

**Static method**: Belongs to the class itself, not to any specific object. You call it on the class: `Math.sqrt(25)`. It cannot access instance fields because there's no specific object.

### Q2 (80): Show one method in your project that should clearly be instance-based and explain why.

```java
public class Patient {
    private final String id;
    private String name;
    private String dob;
    private String address;
    private String disease;
    private String entryDate;
    
    // Instance method - operates on THIS patient's data
    public void displayPatientInfo() {
        System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-15s | %-12s |\n",
                id, name, dob, address, disease, entryDate);
    }
}

// Usage in PatientSystem:
public class PatientSystem {
    private List<Patient> records = new ArrayList<>();
    
    public void viewPatientList(Scanner sc) {
        System.out.println("------------------------------------------------------------");
        System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-15s | %-12s |\n",
                "ID", "Name", "DOB", "Address", "Disease", "Entry Date");
        System.out.println("------------------------------------------------------------");
        
        for (Patient p : records) {
            p.displayPatientInfo();  // Each patient displays its own data
        }
    }
}

// Result:
Patient p1 = new Patient("P001", "John", "01/01/1990", "Flu", "123 St", "01/01/2024");
Patient p2 = new Patient("P002", "Jane", "02/02/1985", "Cold", "456 Ave", "02/01/2024");

p1.displayPatientInfo();  // Shows John's information in table format
p2.displayPatientInfo();  // Shows Jane's information in table format
```

**Why instance-based**: The `displayPatientInfo()` method needs to show a specific patient's information. It accesses `this.id`, `this.name`, `this.dob`, etc., which are different for each patient object. Making it static would be impossible - which patient's data would it display? Each patient object knows its own data and can display itself.

### Q3 (100): Explain how a project becomes weaker if too many methods are made static.

**Problems with excessive static methods:**

**1. Loss of polymorphism:**
```java
// BAD: Static methods can't be overridden
public class Staff {
    public static void performDuty() {
        System.out.println("Generic duty");
    }
}

public class Cardiologist extends Staff {
    public static void performDuty() {  // This doesn't override!
        System.out.println("Heart consultation");
    }
}

Staff s = new Cardiologist();
s.performDuty();  // Prints "Generic duty" - polymorphism broken!
```

**2. Harder to test:**
```java
// BAD: Static methods are hard to mock
public class EmailService {
    public static void sendEmail(String to, String message) {
        // Actually sends email - can't mock in tests!
    }
}

// GOOD: Instance method can be mocked
public class EmailService {
    public void sendEmail(String to, String message) {
        // Can create mock EmailService for testing
    }
}
```

**3. Tight coupling:**
```java
// BAD: Direct dependency on static method
public class AppointmentService {
    public void scheduleAppointment() {
        EmailService.sendEmail(...);  // Tightly coupled to EmailService
    }
}

// GOOD: Dependency injection
public class AppointmentService {
    private EmailService emailService;
    
    public void scheduleAppointment() {
        emailService.sendEmail(...);  // Can swap implementations
    }
}
```

**4. Hidden dependencies:**
Static methods often access static state, creating hidden dependencies that make code unpredictable and hard to reason about.

**5. Breaks object-oriented design:**
OOP is about objects with state and behavior. Excessive static methods turn your code into procedural programming, losing the benefits of encapsulation, inheritance, and polymorphism.

**When static IS appropriate:**
- Utility methods with no state: `Math.sqrt()`, `String.valueOf()`
- Factory methods: `LocalDate.now()`
- Constants: `Math.PI`
