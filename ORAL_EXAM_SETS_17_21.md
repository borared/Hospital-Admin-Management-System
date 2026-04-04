# Oral Exam Preparation - Sets 17-21

## Set 17

### Q1 (60): What is an abstract method, and why does it have no body?

An abstract method is a method declaration without implementation (no body/code). It has no body because it's meant to be a placeholder that forces subclasses to provide their own specific implementation. The parent class says "you must have this method" but doesn't dictate how it should work.

### Q2 (80): Show one behavior in your project that different child classes could implement differently.

```java
public abstract class Staff {
    // Abstract method - no body
    public abstract String getResponsibilities();
    public abstract String getDepartment();
    public abstract void performDuty();
}

// Different implementations:
public class Cardiologist extends Staff {
    @Override
    public String getResponsibilities() {
        return "Diagnose and treat heart conditions, perform catheterizations, prescribe medications.";
    }
    
    @Override
    public String getDepartment() {
        return "Cardiology";
    }
    
    @Override
    public void performDuty() {
        System.out.println(name + " is consulting a patient with heart issues.");
    }
}

public class Surgeon extends Staff {
    @Override
    public String getResponsibilities() {
        return "Perform surgical operations, pre-op consultations, post-op care.";
    }
    
    @Override
    public String getDepartment() {
        return "Surgery";
    }
    
    @Override
    public void performDuty() {
        System.out.println(name + " is performing surgery.");
    }
}
```

Each staff type has completely different responsibilities and duties, but the abstract method ensures they all provide this information.

### Q3 (100): Explain how abstract methods help a team keep class design consistent.

Abstract methods create a contract that the compiler enforces. If a team member creates a new staff type (like Nurse or Technician), they MUST implement `getResponsibilities()`, `getDepartment()`, and `performDuty()` - the code won't compile otherwise. This prevents:
- Forgetting to implement critical methods
- Inconsistent method names (someone calling it `getDept()` instead of `getDepartment()`)
- Missing functionality that other parts of the system expect

The abstract parent acts as a checklist that ensures all subclasses have the same structure, making the codebase predictable and maintainable.

---

## Set 18

### Q1 (60): What are two important differences between an abstract class and an interface?

1. **Implementation**: Abstract classes can have concrete methods with implementations (like `checkIn()` in Staff), while interfaces traditionally only have method signatures (though Java 8+ allows default methods).

2. **State/Fields**: Abstract classes can have instance variables and constructors (like `id`, `name`, `salary` in Staff), while interfaces cannot have instance state - only constants.

### Q2 (80): Show where your project uses, or should use, an interface or an abstract class.

**Interface usage (IStaff):**
```java
public interface IStaff {
    boolean can(String action);  // Pure contract, no implementation
}
```

**Abstract class usage (Staff):**
```java
public abstract class Staff {
    // Has state
    protected String id;
    protected String name;
    protected double salary;
    
    // Has concrete methods
    public void checkIn() {
        this.isActive = true;
        this.lastCheckIn = LocalDateTime.now();
    }
    
    // Has abstract methods
    public abstract String getResponsibilities();
}
```

### Q3 (100): For that case, justify why one is a better design choice than the other.

**IStaff should be an interface** because:
- It's a pure capability/behavior (permission checking)
- No shared state needed
- Could be implemented by non-User classes in the future
- Represents "can do" rather than "is a"

**Staff should be an abstract class** because:
- All staff share common data (id, name, salary, check-in times)
- Has shared behavior (checkIn, checkOut, display) that shouldn't be duplicated
- Represents a true "is-a" relationship (Cardiologist IS-A Staff)
- Needs a constructor to initialize common fields

Using an interface for Staff would force every subclass to duplicate the same fields and checkIn/checkOut logic. Using an abstract class for IStaff would be overkill since there's no shared state or behavior.

---

## Set 19

### Q1 (60): What is a has-a relationship, and how is it different from an is-a relationship?

**has-a (composition)**: One class contains another class as a field. Example: "Appointment HAS-A Patient" - the appointment holds a reference to a patient object.

**is-a (inheritance)**: One class extends another. Example: "Manager IS-A User" - Manager inherits from User.

The difference: has-a means "uses" or "contains", is-a means "is a type of".

### Q2 (80): Show one has-a relationship and one is-a relationship from your project, if possible.

**has-a relationship:**
```java
public class Appointment {
    private String id;
    private Patient patient;  // HAS-A: Appointment contains a Patient
    private Doctor doctor;    // HAS-A: Appointment contains a Doctor
    private String date;
    private String time;
}
```

**is-a relationship:**
```java
public class Manager extends User {  // IS-A: Manager is a type of User
    // Inherits username, password, login()
}

public class Cardiologist extends Staff {  // IS-A: Cardiologist is a type of Staff
    // Inherits id, name, salary, checkIn()
}
```

### Q3 (100): Explain why choosing the wrong relationship type would make the design weaker or more confusing.

**If we used inheritance wrong (Appointment extends Patient):**
- Nonsensical: An appointment is not a type of patient
- Appointment would inherit patient's medical history, which doesn't make sense
- Can't have multiple patients per appointment
- Violates the Liskov Substitution Principle

**If we used composition wrong (Manager contains User instead of extending):**
- Would need to manually delegate every User method
- Couldn't use Manager where User is expected (breaks polymorphism)
- More code, less clear relationship
- Manager wouldn't truly "be" a user

The rule: Use is-a when the subclass is genuinely a specialized version of the parent. Use has-a when one object uses or contains another as a component.

---

## Set 20

### Q1 (60): What is composition, and how is it different from inheritance?

**Composition** is when a class contains instances of other classes as fields (has-a relationship). **Inheritance** is when a class extends another class (is-a relationship).

Composition means "built from parts", inheritance means "is a specialized version of".

### Q2 (80): Show one place in your project where one class contains or uses another class.

```java
public class Appointment {
    private String id;
    private Patient patient;    // Composition: contains Patient
    private Doctor doctor;      // Composition: contains Doctor
    private String date;
    private String time;
    private String status;
    
    public Appointment(String id, Patient patient, Doctor doctor, 
                      String date, String time) {
        this.patient = patient;
        this.doctor = doctor;
        // ...
    }
}
```

Appointment is composed of Patient and Doctor objects - it doesn't inherit from them, it contains references to them.

### Q3 (100): Explain why composition is a better choice than inheritance for that case.

Using composition for Appointment is correct because:

1. **Semantic correctness**: An appointment is NOT a type of patient or doctor - it's a separate concept that involves both

2. **Flexibility**: An appointment can reference any patient and any doctor. With inheritance, you'd be locked into one type

3. **Multiple relationships**: Appointment needs both a patient AND a doctor. Java doesn't support multiple inheritance, but composition allows multiple relationships

4. **Loose coupling**: If Patient or Doctor classes change, Appointment is less affected. With inheritance, changes to the parent directly impact the child

5. **Reusability**: The same Patient and Doctor objects can be used in multiple appointments

If Appointment extended Patient, it would inherit medical history, which makes no sense. Composition correctly models that an appointment "uses" a patient and doctor, not that it "is" one.

---

## Set 21

### Q1 (60): What does it mean for two objects to collaborate without one inheriting from the other?

Collaboration means objects work together by calling each other's methods or passing data between them, without having an inheritance relationship. They communicate through composition, method parameters, or return values - not through parent-child hierarchy.

### Q2 (80): Show two classes in your project that work together and explain their relationship.

```java
public class AppointmentController {
    private AppointmentService appointmentService;
    private Scanner scanner;
    
    public void handleAppointmentMenu() {
        // Controller collaborates with Service
        appointmentService.displayAllAppointments();
    }
}

public class AppointmentService implements IAppointmentService {
    private List<Appointment> appointments;
    
    public void displayAllAppointments() {
        // Service works with Appointment objects
        for (Appointment apt : appointments) {
            apt.display();
        }
    }
}
```

**Relationship**: AppointmentController collaborates with AppointmentService through composition (has-a). The controller handles user input and delegates business logic to the service. Neither inherits from the other - they work together as separate components with distinct responsibilities.

### Q3 (100): Explain why distributing responsibility across collaborating classes makes the system easier to maintain.

**Separation of Concerns:**
- Controller handles UI/user interaction
- Service handles business logic
- Model handles data

**Benefits:**

1. **Easier to change**: If we switch from console to GUI, only Controller changes - Service and Model stay the same

2. **Easier to test**: Can test AppointmentService logic without needing user input or UI code

3. **Easier to understand**: Each class has one clear job. New developers can understand AppointmentService without knowing how the menu works

4. **Easier to reuse**: AppointmentService could be used by different controllers (web, mobile, console) without modification

5. **Easier to debug**: If appointment logic fails, you know to check AppointmentService. If menu display fails, check Controller

6. **Team collaboration**: Different developers can work on Controller and Service simultaneously without conflicts

This is the Single Responsibility Principle in action - each class has one reason to change, making the system more maintainable and flexible.
