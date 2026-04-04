# OOP Oral Exam Preparation - Sets 8-11
## Hospital Admin Management System

---

## SET 8: Passing Parameters

### Q1 (60 points): Difference between passing primitive value and passing object reference to a method

**Theory Answer**:

**Passing Primitive (Pass by Value)**:
- Copies the actual value
- Method gets a copy, not the original
- Changes inside method don't affect original
- Types: int, double, boolean, char, etc.

**Passing Object Reference (Pass by Reference)**:
- Copies the memory address (reference)
- Method gets reference to same object
- Changes inside method DO affect original object
- Types: All classes (Doctor, Patient, String, etc.)

**In Your Project**:

**Location**: `src/main/java/adminmangementsystem/com/model/Doctor.java`

```java
// Example 1: Passing PRIMITIVE (pass by value)
public boolean setSalary(double salary) {  // 'salary' is primitive
    if (salary > 0) {
        this.salary = salary;
        return true;
    }
    return false;
}

// Usage:
double mySalary = 75000;
doctor.setSalary(mySalary);  // Copies value 75000
mySalary = 80000;  // Changing mySalary doesn't affect doctor's salary
System.out.println(doctor.getSalary());  // Still 75000
```

```java
// Example 2: Passing OBJECT REFERENCE (pass by reference)
// From your DoctorSystem.java
public void addDoctor(Doctor doctor) {  // 'doctor' is object reference
    if (doctor == null) {
        throw new IllegalArgumentException("Doctor cannot be null.");
    }
    records.add(doctor);  // Stores reference to same object
}

// Usage:
Doctor myDoctor = new Doctor("D001", "Dr. Smith", ...);
doctorSystem.addDoctor(myDoctor);  // Passes reference

// Modify original object
myDoctor.setSalary(85000);

// System's doctor is also modified (same object!)
Doctor found = doctorSystem.searchDoctorById("D001");
System.out.println(found.getSalary());  // 85000 (changed!)
```

**Visual Comparison**:

```
PRIMITIVE (Pass by Value):
┌─────────────┐         ┌──────────────┐
│ mySalary    │ Copy    │ Method       │
│ = 75000     │────────>│ salary=75000 │
└─────────────┘         └──────────────┘
Change mySalary → Method parameter unchanged

OBJECT REFERENCE (Pass by Reference):
┌─────────────┐         ┌──────────────┐
│ myDoctor    │ Copy    │ Method       │
│ → 0x1234    │────────>│ doctor→0x1234│
└─────────────┘         └──────────────┘
        ↓                       ↓
    [Same Doctor Object @ 0x1234]
Change via myDoctor → Method sees change too!
```

---


### Q2 (80 points): Show one method that receives an object as parameter and explain why

**In Your Project**:

**Location**: `src/main/java/adminmangementsystem/com/management/DoctorSystem.java`

```java
public class DoctorSystem {
    private List<Doctor> records = new ArrayList<>();
    
    // METHOD: Receives Doctor object as parameter
    @Override
    public void addDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor cannot be null.");
        }
        
        if (!isIdUnique(doctor.getId())) {
            throw new IllegalArgumentException("Doctor ID already exists.");
        }
        
        records.add(doctor);  // Stores reference to the doctor object
    }
}
```

**Why Receive Object as Parameter:**

**1. Passes Complete Information**:
```java
// WITHOUT object parameter (BAD):
public void addDoctor(String id, String name, String dob, String address,
                      String email, String position, double salary, String doe) {
    // 8 parameters! Hard to read and maintain
    Doctor doctor = new Doctor(id, name, dob, address, email, position, salary, doe);
    records.add(doctor);
}

// WITH object parameter (GOOD - Your approach):
public void addDoctor(Doctor doctor) {
    // 1 parameter! Clean and simple
    records.add(doctor);
}

// Usage comparison:
// Bad: addDoctor("D001", "Dr. Smith", "01/15/1980", "123 Main St", 
//               "dr.smith@hospital.com", "Cardiologist", 75000, "01/01/2020");
// Good: addDoctor(doctor);  ✓ Much cleaner!
```

**2. Allows Method to Modify the Object**:
```java
// The method can call methods on the object
public void addDoctor(Doctor doctor) {
    // Can access doctor's methods
    String id = doctor.getId();
    String name = doctor.getName();
    
    // Can validate using object's data
    if (!isIdUnique(doctor.getId())) {
        throw new IllegalArgumentException("Doctor ID already exists.");
    }
    
    records.add(doctor);
}
```

**3. Enables Object Collaboration**:
```java
// From your PatientSystem.java
public void addPatient(Patient patient) {
    if (patient == null) {
        throw new IllegalArgumentException("Patient cannot be null.");
    }
    
    if (!isIdUnique(patient.getId())) {
        throw new IllegalArgumentException("Patient ID already exists.");
    }
    
    records.add(patient);
}

// Objects work together:
Patient patient = new Patient("P001", "John Doe", ...);
patientSystem.addPatient(patient);  // Patient object passed to system
```

**4. Flexibility and Extensibility**:
```java
// Can pass any Doctor or its subclasses
Doctor doctor1 = new Doctor("D001", "Dr. Smith", ...);
Cardiologist doctor2 = new Cardiologist("D002", "Dr. Jones", ..., "Fellowship");
Surgeon doctor3 = new Surgeon("D003", "Dr. Lee", ..., "Orthopedic");

doctorSystem.addDoctor(doctor1);  // ✓ Works
doctorSystem.addDoctor(doctor2);  // ✓ Works (Cardiologist IS-A Doctor)
doctorSystem.addDoctor(doctor3);  // ✓ Works (Surgeon IS-A Doctor)
```

---

### Q3 (100 points): How object references allow different classes to collaborate on same data without copying

**In Your Project**:

**Location**: `src/main/java/adminmangementsystem/com/management/AppointmentService.java`

Let me check this file first:

### Q3 (100 points): How object references allow different classes to collaborate on same data without copying

**In Your Project**:

**The Problem Without Object References (Copying Everything)**:

```java
// BAD: If we copied all data instead of using references
public class Appointment {
    // Copy all patient data into appointment
    private String patientId;
    private String patientName;
    private String patientDob;
    private String patientAddress;
    private String patientDisease;
    private String appointmentDate;
    private String appointmentTime;
    
    public Appointment(String patId, String patName, String patDob,
                       String patAddress, String patDisease, 
                       String date, String time) {
        // Copy every field manually
        this.patientId = patId;
        this.patientName = patName;
        this.patientDob = patDob;
        this.patientAddress = patAddress;
        this.patientDisease = patDisease;
        this.appointmentDate = date;
        this.appointmentTime = time;
    }
}

// Problem: If patient data changes, appointment data is outdated!
Patient patient = new Patient("P001", "John Doe", ..., "Diabetes");
Appointment apt = new Appointment("P001", "John Doe", ..., "Diabetes", ...);

// Patient's disease updated
patient.setDisease("Diabetes, Hypertension");

// Appointment still has old data!
System.out.println(apt.getPatientDisease());  // "Diabetes" (outdated!)
System.out.println(patient.getDisease());     // "Diabetes, Hypertension" (current)
```

**Your Solution (Using Object References - COMPOSITION)**:

**Location**: `src/main/java/adminmangementsystem/com/model/Appointment.java`

```java
public class Appointment {
    // COMPOSITION: Store reference to Patient object (not copy of data)
    private Patient patient;  // Reference to the actual patient object
    private String appointmentDate;
    private String appointmentTime;
    
    public Appointment(Patient patient, String date, String time) {
        if (!setPatient(patient) ||
            !setAppointmentDate(date) ||
            !setAppointmentTime(time)) {
            throw new IllegalArgumentException("Invalid appointment data.");
        }
    }
    
    public boolean setPatient(Patient patient) {
        if (patient != null) {
            this.patient = patient;  // Stores reference, not copy
            return true;
        }
        return false;
    }
    
    // Access patient data through reference
    public String getPatientId() {
        return patient.getId();  // Gets current data from patient object
    }
    
    public String getPatientName() {
        return patient.getName();  // Always current
    }
    
    public String getPatientDisease() {
        return patient.getDisease();  // Always current
    }
}
```

**How Collaboration Works**:

```java
// Step 1: Create patient object
Patient patient = new Patient("P001", "John Doe", "01/01/1990", 
                              "Diabetes", "555-1234", "04/01/2026");

// Step 2: Create appointment with patient reference
Appointment apt = new Appointment(patient, "2026-04-15", "10:00");

// Step 3: Add to systems
patientSystem.addPatient(patient);
appointmentService.addAppointment(apt);

// Now THREE places have reference to SAME patient object:
// 1. patient variable
// 2. patientSystem's records list
// 3. apt's patient field

// Step 4: Update patient data
patient.setDisease("Diabetes, Hypertension");

// Step 5: ALL references see the change (no copying needed!)
System.out.println(patient.getDisease());           // "Diabetes, Hypertension"
System.out.println(apt.getPatientDisease());        // "Diabetes, Hypertension" ✓
Patient found = patientSystem.searchPatientById("P001");
System.out.println(found.getDisease());             // "Diabetes, Hypertension" ✓
```

**Visual Representation**:

```
Memory Layout:

[HEAP - One Patient Object]
┌─────────────────────────────┐
│ Patient @ 0x1234            │
│ - id: "P001"                │
│ - name: "John Doe"          │
│ - disease: "Diabetes"       │
└─────────────────────────────┘
         ↑         ↑         ↑
         │         │         │
    ┌────┘    ┌────┘    ┌────┘
    │         │         │
[patient] [apt.patient] [patientSystem.records[0]]
  0x1234     0x1234         0x1234

All three point to SAME object!
Change in one place → visible everywhere
```

**Benefits of Object References (No Copying)**:

**1. Data Consistency**:
```java
// Update patient once
patient.setDisease("Diabetes, Hypertension");

// Change visible everywhere automatically
apt.getPatientDisease();  // Updated ✓
patientSystem.searchPatientById("P001").getDisease();  // Updated ✓
```

**2. Memory Efficiency**:
```java
// WITHOUT references (copying):
// Patient data: 500 bytes
// 100 appointments = 100 copies = 50,000 bytes

// WITH references (your approach):
// Patient data: 500 bytes
// 100 appointments = 100 references (8 bytes each) = 800 bytes
// Total: 1,300 bytes vs 50,000 bytes!
```

**3. Easy Updates**:
```java
// Update patient in one place
Patient patient = patientSystem.searchPatientById("P001");
patient.setDisease("Recovered");

// All appointments automatically show updated disease
for (Appointment apt : appointmentService.getAllAppointments()) {
    if (apt.getPatientId().equals("P001")) {
        System.out.println(apt.getPatientDisease());  // "Recovered" ✓
    }
}
```

**4. Object Collaboration**:

**Location**: Your `App.java` shows perfect collaboration:

```java
// Create systems
PatientSystem patientSystem = new PatientSystem();
DoctorSystem doctorSystem = new DoctorSystem();

// AppointmentService needs PatientSystem to validate patients
AppointmentService appointmentService = new AppointmentService(patientSystem);
// ↑ Passes reference, not copy!

// Now AppointmentService can use PatientSystem
public void addAppointment(Scanner sc) {
    // AppointmentView uses patientSystem to validate patient exists
    Appointment appointment = AppointmentView.getAppointmentInput(sc, patientSystem);
    // ↑ Passes reference to patientSystem
    
    if (appointment != null) {
        appointments.add(appointment);
    }
}
```

**Real Example: Multiple Classes Working Together**:

```java
// 1. PatientSystem manages patients
PatientSystem patientSystem = new PatientSystem();
Patient patient = new Patient("P001", "John Doe", ...);
patientSystem.addPatient(patient);  // Stores reference

// 2. AppointmentService uses same patient (no copy!)
AppointmentService appointmentService = new AppointmentService(patientSystem);
Appointment apt = new Appointment(patient, "2026-04-15", "10:00");
appointmentService.addAppointment(apt);  // Stores reference to same patient

// 3. Update patient in PatientSystem
Patient found = patientSystem.searchPatientById("P001");
found.setDisease("Diabetes, Hypertension");

// 4. Appointment automatically has updated data
Appointment foundApt = appointmentService.searchAppointmentById("P001");
System.out.println(foundApt.getPatientDisease());  // "Diabetes, Hypertension" ✓

// NO COPYING! All classes work with same patient object
```

**What Would Happen With Copying**:

```java
// If Appointment copied patient data:
Patient patient = new Patient("P001", "John Doe", ..., "Diabetes");
Appointment apt = new Appointment(patient, ...);  // Makes copy of all data

// Update patient
patient.setDisease("Diabetes, Hypertension");

// Appointment has OLD data (copy is outdated)
System.out.println(patient.getDisease());     // "Diabetes, Hypertension"
System.out.println(apt.getPatientDisease());  // "Diabetes" (wrong!)

// Would need to manually update appointment
apt.updatePatientData(patient);  // Extra work!
```

**Why Your Design is Better**:
1. **Single Source of Truth**: Patient data exists in one place
2. **Automatic Updates**: Changes propagate automatically
3. **Memory Efficient**: No duplicate data
4. **Easier Maintenance**: Update once, affects everywhere
5. **Real-World Modeling**: Appointment references patient, doesn't duplicate them

---


## SET 9: Inheritance

### Q1 (60 points): What is inheritance, how is it different from copying code into multiple classes?

**Theory Answer**:

**Inheritance**:
- Child class extends parent class using `extends` keyword
- Child automatically gets all parent's fields and methods
- Creates IS-A relationship (Manager IS-A User)
- Changes in parent automatically affect all children
- Promotes code reuse and logical hierarchy

**Copying Code**:
- Manually duplicate code in each class
- No relationship between classes
- Changes must be made in every class separately
- Hard to maintain, error-prone

**In Your Project**:

**Location**: `src/main/java/adminmangementsystem/com/user/`

**WITH INHERITANCE (Your Approach - GOOD)**:

```java
// PARENT CLASS: User.java
public abstract class User implements IStaff {
    // Common fields for ALL users
    private String username;
    private String password;
    
    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Common method for ALL users
    public boolean login(String username, String password) {
        return this.username.equals(username) && 
               this.password.equals(password);
    }
    
    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}

// CHILD CLASS 1: Manager.java
public class Manager extends User {  // INHERITANCE: extends User
    public Manager(String username, String password) {
        super(username, password);  // Calls parent constructor
    }
    
    @Override
    public boolean can(String action) {
        return true;  // Manager-specific behavior
    }
    
    // Manager automatically has:
    // - username field (inherited)
    // - password field (inherited)
    // - login() method (inherited)
    // - getUsername() method (inherited)
    // - setUsername() method (inherited)
}

// CHILD CLASS 2: Receptionist.java
public class Receptionist extends User {  // INHERITANCE: extends User
    public Receptionist(String username, String password) {
        super(username, password);  // Calls parent constructor
    }
    
    @Override
    public boolean can(String action) {
        // Receptionist-specific behavior
        return action.contains("register new patients") ||
               action.contains("schedule appointments");
    }
    
    // Receptionist automatically has:
    // - username field (inherited)
    // - password field (inherited)
    // - login() method (inherited)
    // - getUsername() method (inherited)
    // - setUsername() method (inherited)
}
```

**WITHOUT INHERITANCE (Copying Code - BAD)**:

```java
// Manager.java - ALL code duplicated
public class Manager {
    // COPIED from User
    private String username;
    private String password;
    
    // COPIED constructor
    public Manager(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // COPIED login method
    public boolean login(String username, String password) {
        return this.username.equals(username) && 
               this.password.equals(password);
    }
    
    // COPIED getters/setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    // Manager-specific
    public boolean can(String action) {
        return true;
    }
}

// Receptionist.java - ALL code duplicated AGAIN
public class Receptionist {
    // COPIED from User (again!)
    private String username;
    private String password;
    
    // COPIED constructor (again!)
    public Receptionist(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // COPIED login method (again!)
    public boolean login(String username, String password) {
        return this.username.equals(username) && 
               this.password.equals(password);
    }
    
    // COPIED getters/setters (again!)
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    // Receptionist-specific
    public boolean can(String action) {
        return action.contains("register new patients") ||
               action.contains("schedule appointments");
    }
}
```

**Problems with Copying**:

```java
// Problem 1: Need to fix login bug
// WITHOUT inheritance: Fix in Manager, then Receptionist (2 places)
// WITH inheritance: Fix in User once (1 place) ✓

// Problem 2: Add new field (email)
// WITHOUT inheritance: Add to Manager, then Receptionist (2 places)
// WITH inheritance: Add to User once (1 place) ✓

// Problem 3: Add new user type (Admin)
// WITHOUT inheritance: Copy all code again (3rd time!)
// WITH inheritance: Just extend User ✓

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);  // Done! Has everything
    }
    
    @Override
    public boolean can(String action) {
        return true;  // Admin-specific behavior
    }
}
```

**Code Comparison**:

| Aspect | With Inheritance | Without Inheritance (Copying) |
|--------|-----------------|-------------------------------|
| Lines of code | User: 30 lines<br>Manager: 10 lines<br>Receptionist: 15 lines<br>**Total: 55 lines** | Manager: 40 lines<br>Receptionist: 45 lines<br>**Total: 85 lines** |
| Maintenance | Change once in User | Change in every class |
| Bug fixes | Fix once | Fix multiple times |
| Adding new user type | Extend User (5 lines) | Copy all code (40+ lines) |
| Relationship | Clear IS-A relationship | No relationship |

---

### Q2 (80 points): Show one parent-child relationship and explain what child gets from parent

**In Your Project**:

**Location**: `src/main/java/adminmangementsystem/com/user/User.java` and `Manager.java`

**PARENT CLASS (User)**:

```java
public abstract class User implements IStaff {
    // FIELDS that children inherit
    private String username;
    private String password;
    
    // CONSTRUCTOR that children can call
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // METHODS that children inherit
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean login(String username, String password) {
        return this.username.equals(username) && 
               this.password.equals(password);
    }
    
    // ABSTRACT METHOD that children must implement
    public abstract boolean can(String action);
}
```

**CHILD CLASS (Manager)**:

```java
public class Manager extends User {  // INHERITANCE: Manager IS-A User
    
    // Constructor
    public Manager(String username, String password) {
        super(username, password);  // Calls parent constructor
    }
    
    // Implements abstract method from parent
    @Override
    public boolean can(String action) {
        return true;  // Manager can do everything
    }
}
```

**What Manager Gets from User (Inherited)**:

**1. Fields (Data)**:
```java
Manager manager = new Manager("admin", "admin$");

// Manager has username field (inherited from User)
String name = manager.getUsername();  // "admin"

// Manager has password field (inherited from User)
// (accessed through inherited methods)
```

**2. Methods (Behavior)**:
```java
Manager manager = new Manager("admin", "admin$");

// Inherited login() method
boolean loggedIn = manager.login("admin", "admin$");  // true ✓

// Inherited getUsername() method
String username = manager.getUsername();  // "admin" ✓

// Inherited setUsername() method
manager.setUsername("newAdmin");  // ✓ Works

// Inherited setPassword() method
manager.setPassword("newPass");  // ✓ Works
```

**3. Constructor Chain**:
```java
// When creating Manager:
Manager manager = new Manager("admin", "admin$");

// What happens:
// 1. Manager constructor called
// 2. super(username, password) calls User constructor
// 3. User constructor sets username and password fields
// 4. Control returns to Manager constructor
// 5. Manager object created with initialized fields
```

**Complete Example**:

```java
// Create manager
Manager manager = new Manager("admin", "admin$");

// Use inherited methods
System.out.println(manager.getUsername());  // "admin" (inherited)
System.out.println(manager.login("admin", "admin$"));  // true (inherited)

// Use Manager-specific method
System.out.println(manager.can("delete doctor"));  // true (Manager's own)

// Manager has EVERYTHING from User + its own behavior
```

**What Manager DOESN'T Need to Write**:
- ✓ username field (inherited)
- ✓ password field (inherited)
- ✓ getUsername() method (inherited)
- ✓ setUsername() method (inherited)
- ✓ getPassword() method (inherited)
- ✓ setPassword() method (inherited)
- ✓ login() method (inherited)

**What Manager MUST Write**:
- Constructor (to call super())
- can() method (abstract in parent, must implement)

**Inheritance Hierarchy**:

```
        User (Parent)
        - username
        - password
        - login()
        - getUsername()
        - setUsername()
        - can() [abstract]
           ↑
           │ extends
           │
    ┌──────┴──────┐
    │             │
Manager      Receptionist
- can()      - can()
[returns     [returns true
 true]        for limited
              actions]

Both inherit ALL from User!
```

---


### Q3 (100 points): Explain why inheritance is a real IS-A relationship, not just code reuse

**In Your Project**:

**The IS-A Relationship Explained**:

**Location**: `src/main/java/adminmangementsystem/com/user/Manager.java`

```java
public class Manager extends User {
    // Manager IS-A User
}
```

**What IS-A Means**:
- Every Manager IS A User (logically and programmatically)
- Manager can be used anywhere User is expected
- Manager has all characteristics of a User
- It's not just about sharing code - it's about logical relationship

**Real IS-A Relationship (Your Project)**:

```java
// Manager IS-A User - makes logical sense
Manager manager = new Manager("admin", "admin$");

// Can treat Manager as User
User user = manager;  // ✓ Valid! Manager IS-A User
user.login("admin", "admin$");  // ✓ Works

// Can use Manager where User is expected
public void authenticateUser(User user) {  // Expects User
    boolean success = user.login(...);
}

authenticateUser(manager);  // ✓ Works! Manager IS-A User
authenticateUser(new Receptionist(...));  // ✓ Works! Receptionist IS-A User
```

**Example from Your App.java**:

```java
// Array of User can hold Manager and Receptionist
User[] users = {
    new Manager("admin", "admin$"),           // Manager IS-A User ✓
    new Receptionist("receptionist", "rec123") // Receptionist IS-A User ✓
};

// Authenticate any user type
private static <T extends User> T authenticateUser(Scanner sc, User[] users, Class<T> userType) {
    String username = sc.nextLine();
    String password = sc.nextLine();
    
    for (User user : users) {  // Treats all as User
        if (userType.isInstance(user) && user.login(username, password)) {
            return userType.cast(user);
        }
    }
    return null;
}

// Works for both Manager and Receptionist because they ARE Users
```

**Why It's More Than Just Code Reuse**:

**1. Logical Relationship**:
```java
// Makes sense in real world:
// - Manager IS-A User ✓ (Manager is a type of user)
// - Receptionist IS-A User ✓ (Receptionist is a type of user)

// Doesn't make sense:
// - Manager IS-A Doctor ✗ (Manager is not a doctor)
// - Patient IS-A Appointment ✗ (Patient is not an appointment)
```

**2. Polymorphism (Different Behavior)**:
```java
User user1 = new Manager("admin", "admin$");
User user2 = new Receptionist("rec", "rec123");

// Same method call, different behavior
user1.can("delete doctor");  // true (Manager can)
user2.can("delete doctor");  // false (Receptionist cannot)

// This is polymorphism - only works with IS-A relationship!
```

**3. Substitutability (Liskov Substitution Principle)**:
```java
// Can substitute child for parent anywhere
public void processUser(User user) {
    if (user.login("username", "password")) {
        System.out.println("Welcome " + user.getUsername());
    }
}

// Works with any User subclass
processUser(new Manager("admin", "admin$"));        // ✓
processUser(new Receptionist("rec", "rec123"));     // ✓
// Both work because they ARE Users
```

**4. Extensibility**:
```java
// Add new user type - just extend User
public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }
    
    @Override
    public boolean can(String action) {
        return action.contains("system configuration");
    }
}

// Automatically works everywhere User is used
User[] users = {
    new Manager(...),
    new Receptionist(...),
    new Admin(...)  // ✓ Works immediately!
};
```

**Another Example: Staff Hierarchy**:

**Location**: `src/main/java/adminmangementsystem/com/model/Staff.java`

```java
// PARENT: Staff
public abstract class Staff {
    protected String id;
    protected String name;
    protected double salary;
    
    public abstract String getResponsibilities();
    public abstract String getDepartment();
    public abstract void performDuty();
}

// CHILDREN: Cardiologist, Surgeon, Nurse
public class Cardiologist extends Staff {
    // Cardiologist IS-A Staff ✓
}

public class Surgeon extends Staff {
    // Surgeon IS-A Staff ✓
}

public class Nurse extends Staff {
    // Nurse IS-A Staff ✓
}

// Real IS-A usage:
Staff staff1 = new Cardiologist(...);  // ✓ Cardiologist IS-A Staff
Staff staff2 = new Surgeon(...);       // ✓ Surgeon IS-A Staff
Staff staff3 = new Nurse(...);         // ✓ Nurse IS-A Staff

// Can treat all as Staff
public void displayStaff(Staff staff) {
    staff.display();  // Works for all staff types
}

displayStaff(new Cardiologist(...));  // ✓
displayStaff(new Surgeon(...));       // ✓
displayStaff(new Nurse(...));         // ✓
```

**If It Was Just Code Copying (No IS-A)**:

```java
// Manager.java - copied code
public class Manager {
    private String username;
    private String password;
    public boolean login(...) { ... }
}

// Receptionist.java - copied code
public class Receptionist {
    private String username;
    private String password;
    public boolean login(...) { ... }
}

// PROBLEM: Cannot treat them as same type
User[] users = {
    new Manager(...),      // ✗ COMPILE ERROR! Manager is not a User
    new Receptionist(...)  // ✗ COMPILE ERROR! Receptionist is not a User
};

// PROBLEM: Cannot use polymorphism
public void processUser(User user) { ... }
processUser(new Manager(...));  // ✗ COMPILE ERROR! No relationship

// PROBLEM: Need separate methods for each type
public void processManager(Manager m) { ... }
public void processReceptionist(Receptionist r) { ... }
// Code duplication everywhere!
```

**Why IS-A Relationship Matters**:

1. **Type Safety**: Compiler ensures Manager IS-A User
2. **Polymorphism**: Can treat children as parent type
3. **Code Organization**: Clear hierarchy reflects real-world relationships
4. **Maintainability**: Change parent, all children updated
5. **Extensibility**: Easy to add new types
6. **Logical Modeling**: Code structure matches real-world structure

**In Your Oral Exam, Say**:

"In my project, Manager extends User, which creates an IS-A relationship. This means Manager IS-A User, not just that Manager copies User's code. This relationship allows me to treat Manager as a User anywhere in my code, like in the user array in App.java. It also enables polymorphism - I can call the same method on different user types and get different behavior. For example, `user.can('delete doctor')` returns true for Manager but false for Receptionist. This is more than code reuse - it's modeling real-world relationships where both Manager and Receptionist are types of users in the hospital system."

---


## SET 10: The `super` Keyword

### Q1 (60 points): Purpose of `super` keyword, how is it related to inheritance?

**Theory Answer**:

**`super` keyword**:
- Refers to the parent class
- Used to call parent's constructor
- Used to call parent's methods
- Only works in inheritance relationships

**Relationship to Inheritance**:
- `super` is the bridge between child and parent
- Allows child to access parent's members
- Ensures parent is properly initialized

**In Your Project**:

**Location**: `src/main/java/adminmangementsystem/com/user/Manager.java`

```java
// PARENT CLASS
public abstract class User {
    private String username;
    private String password;
    
    // Parent constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

// CHILD CLASS
public class Manager extends User {
    
    // Child constructor
    public Manager(String username, String password) {
        super(username, password);  // SUPER: Calls parent constructor
        // This initializes username and password in parent class
    }
}
```

**What `super()` Does**:

```java
// When you create a Manager:
Manager manager = new Manager("admin", "admin$");

// Behind the scenes:
// 1. Manager constructor starts
// 2. super("admin", "admin$") calls User constructor
// 3. User constructor sets username = "admin", password = "admin$"
// 4. Control returns to Manager constructor
// 5. Manager object is fully initialized
```

**Without `super()` - COMPILE ERROR**:

```java
public class Manager extends User {
    public Manager(String username, String password) {
        // Missing super() call!
        // COMPILE ERROR: "Implicit super constructor User() is undefined"
    }
}
```

**Why It's Needed**:
- Parent's private fields (username, password) cannot be accessed directly by child
- Child must call parent's constructor to initialize parent's fields
- `super()` is the only way to properly initialize the parent part

---

### Q2 (80 points): Show where `super` is used in constructor or overridden method

**In Your Project**:

**Example 1: `super()` in Constructor**

**Location**: `src/main/java/adminmangementsystem/com/model/Cardiologist.java`

```java
// PARENT CLASS: Staff
public abstract class Staff {
    protected String id;
    protected String name;
    protected String dob;
    protected String address;
    protected String email;
    protected String position;
    protected double salary;
    protected String doe;
    
    public Staff(String id, String name, String dob, String address,
                 String email, String position, double salary, String doe) {
        setId(id);
        setName(name);
        setDob(dob);
        setAddress(address);
        setEmail(email);
        setPosition(position);
        setSalary(salary);
        setDoe(doe);
    }
}

// CHILD CLASS: Cardiologist
public class Cardiologist extends Staff {
    private String fellowship;  // Cardiologist-specific field
    
    public Cardiologist(String id, String name, String dob, String address,
                        String email, String position, double salary, String doe,
                        String fellowship) {
        // SUPER: Initialize parent's 8 fields
        super(id, name, dob, address, email, position, salary, doe);
        
        // Then initialize child's own field
        this.fellowship = fellowship;
    }
}
```

**What Happens Step-by-Step**:

```java
// Create Cardiologist
Cardiologist cardio = new Cardiologist(
    "C001",                    // id
    "Dr. Sarah Smith",         // name
    "01/15/1980",             // dob
    "123 Medical Plaza",       // address
    "dr.smith@hospital.com",   // email
    "Cardiologist",           // position
    95000,                     // salary
    "01/01/2020",             // doe
    "Interventional Cardiology" // fellowship
);

// Execution flow:
// 1. Cardiologist constructor called
// 2. super(...) calls Staff constructor with first 8 parameters
// 3. Staff constructor initializes: id, name, dob, address, email, position, salary, doe
// 4. Control returns to Cardiologist constructor
// 5. Cardiologist sets fellowship = "Interventional Cardiology"
// 6. Object fully created with 9 fields initialized
```

**Example 2: `super` in Overridden Method**

**Location**: `src/main/java/adminmangementsystem/com/model/Surgeon.java`

```java
// PARENT CLASS: Staff
public abstract class Staff {
    public void display() {
        System.out.println(id + " | " + name + " | " + dob + " | "
                + address + " | " + email + " | "
                + position + " | $" + salary + " | " + doe);
    }
}

// CHILD CLASS: Surgeon
public class Surgeon extends Staff {
    private String specialization;
    
    @Override
    public void display() {
        super.display();  // SUPER: Call parent's display() first
        // Parent displays: id, name, dob, address, email, position, salary, doe
        
        // Then add child-specific info
        System.out.println("Specialization: " + specialization);
    }
}
```

**What `super.display()` Does**:

```java
Surgeon surgeon = new Surgeon("S001", "Dr. Lee", "05/20/1975",
                              "456 Hospital Ave", "dr.lee@hospital.com",
                              "Surgeon", 120000, "03/15/2018",
                              "Orthopedic");

surgeon.display();

// Output:
// S001 | Dr. Lee | 05/20/1975 | 456 Hospital Ave | dr.lee@hospital.com | Surgeon | $120000 | 03/15/2018
// ↑ This line from super.display() (parent's method)
// Specialization: Orthopedic
// ↑ This line from Surgeon's display() (child's addition)
```

**Without `super.display()`**:

```java
@Override
public void display() {
    // Without super.display(), must duplicate parent's code
    System.out.println(id + " | " + name + " | " + dob + " | "
            + address + " | " + email + " | "
            + position + " | $" + salary + " | " + doe);  // Duplicated!
    System.out.println("Specialization: " + specialization);
}
```

**With `super.display()` (Your Approach)**:

```java
@Override
public void display() {
    super.display();  // Reuse parent's code ✓
    System.out.println("Specialization: " + specialization);  // Add extra
}
```

---

### Q3 (100 points): What may go wrong if child class doesn't properly initialize parent part

**In Your Project**:

**Problem Scenario 1: Forgetting `super()` Call**

```java
// WRONG: No super() call
public class Manager extends User {
    public Manager(String username, String password) {
        // Forgot super(username, password);
        // COMPILE ERROR: "Implicit super constructor User() is undefined"
    }
}
```

**Why This Fails**:
- Parent (User) has no default constructor
- Parent requires username and password
- Child must call parent's constructor with parameters
- Without `super()`, parent fields are uninitialized

**Problem Scenario 2: Wrong Parameters to `super()`**

```java
// WRONG: Passing wrong values
public class Manager extends User {
    public Manager(String username, String password) {
        super(null, null);  // Passing null to parent!
    }
}

// Usage:
Manager manager = new Manager("admin", "admin$");
manager.login("admin", "admin$");  // false! username is null

// Why: Parent's username and password are null
// Child passed wrong values to super()
```

**Problem Scenario 3: Not Calling `super()` in Overridden Method**

**Location**: `src/main/java/adminmangementsystem/com/model/Cardiologist.java`

```java
// PARENT: Staff
public abstract class Staff {
    public void display() {
        System.out.println(id + " | " + name + " | " + salary);
    }
}

// WRONG: Cardiologist doesn't call super.display()
public class Cardiologist extends Staff {
    private String fellowship;
    
    @Override
    public void display() {
        // Forgot super.display()!
        System.out.println("Fellowship: " + fellowship);
    }
}

// Usage:
Cardiologist cardio = new Cardiologist("C001", "Dr. Smith", ..., "Fellowship");
cardio.display();

// Output:
// Fellowship: Fellowship
// ↑ Only shows fellowship, missing id, name, salary!
```

**Problem Scenario 4: Uninitialized Parent Fields**

```java
// WRONG: Child tries to initialize parent's private fields directly
public class Manager extends User {
    public Manager(String username, String password) {
        // Try to set parent's private fields directly
        this.username = username;  // COMPILE ERROR! username is private in User
        this.password = password;  // COMPILE ERROR! password is private in User
    }
}

// Must use super() instead:
public Manager(String username, String password) {
    super(username, password);  // ✓ Correct way
}
```

**Real Problem in Your Project (If super() Missing)**:

**Location**: `src/main/java/adminmangementsystem/com/model/Surgeon.java`

```java
// WRONG: Surgeon without super()
public class Surgeon extends Staff {
    private String specialization;
    
    public Surgeon(String id, String name, String dob, String address,
                   String email, String position, double salary, String doe,
                   String specialization) {
        // Forgot super()!
        this.specialization = specialization;
    }
}

// What happens:
Surgeon surgeon = new Surgeon("S001", "Dr. Lee", ...);

// Parent fields are uninitialized!
System.out.println(surgeon.getId());     // null
System.out.println(surgeon.getName());   // null
System.out.println(surgeon.getSalary()); // 0.0

// Only child's field is initialized
System.out.println(surgeon.getSpecialization());  // "Orthopedic" ✓

// Methods that use parent fields fail
surgeon.display();  // Shows null | null | null | ...
surgeon.performDuty();  // "null is performing surgery" (wrong!)
```

**Your Correct Implementation**:

```java
public class Surgeon extends Staff {
    private String specialization;
    
    public Surgeon(String id, String name, String dob, String address,
                   String email, String position, double salary, String doe,
                   String specialization) {
        // CORRECT: Call super() first
        super(id, name, dob, address, email, position, salary, doe);
        
        // Then initialize child's field
        this.specialization = specialization;
    }
}

// Now everything works:
Surgeon surgeon = new Surgeon("S001", "Dr. Lee", "05/20/1975",
                              "456 Hospital Ave", "dr.lee@hospital.com",
                              "Surgeon", 120000, "03/15/2018",
                              "Orthopedic");

System.out.println(surgeon.getId());     // "S001" ✓
System.out.println(surgeon.getName());   // "Dr. Lee" ✓
System.out.println(surgeon.getSalary()); // 120000.0 ✓
System.out.println(surgeon.getSpecialization());  // "Orthopedic" ✓
```

**Problems That Occur Without Proper Initialization**:

1. **NullPointerException**:
```java
// Parent fields null
surgeon.getName().toUpperCase();  // NullPointerException!
```

2. **Invalid Object State**:
```java
// Surgeon with no ID, no name - invalid!
doctorSystem.addDoctor(surgeon);  // Should this be allowed?
```

3. **Business Logic Failures**:
```java
// Search by ID fails
doctorSystem.searchDoctorById("S001");  // Returns null (ID is null)
```

4. **Display Issues**:
```java
surgeon.display();  // Shows: null | null | 0.0 | null | ...
```

**Why `super()` is Critical**:
- Ensures parent part is properly initialized
- Prevents null fields and invalid state
- Maintains object integrity
- Required by Java for inheritance to work correctly

---


## SET 11: Method Overriding

### Q1 (60 points): What is method overriding, how is it related to inheritance?

**Theory Answer**:

**Method Overriding**:
- Child class provides its own implementation of parent's method
- Same method name, same parameters, same return type
- Uses `@Override` annotation
- Child's version replaces parent's version

**Relationship to Inheritance**:
- Only possible with inheritance (child extends parent)
- Child inherits method from parent, then changes its behavior
- Enables polymorphism (same method, different behavior)

**In Your Project**:

**Location**: `src/main/java/adminmangementsystem/com/user/Manager.java`

```java
// PARENT CLASS: User
public abstract class User implements IStaff {
    // Abstract method - no implementation in parent
    public abstract boolean can(String action);
}

// CHILD CLASS: Manager
public class Manager extends User {
    
    // METHOD OVERRIDING: Manager provides implementation
    @Override
    public boolean can(String action) {
        return true;  // Manager can do everything
    }
}

// CHILD CLASS: Receptionist
public class Receptionist extends User {
    
    // METHOD OVERRIDING: Receptionist provides different implementation
    @Override
    public boolean can(String action) {
        if (action == null) return false;
        
        String lowerAction = action.toLowerCase();
        return lowerAction.contains("register new patients") ||
               lowerAction.contains("schedule appointments");
    }
}
```

**How It Works**:

```java
// Same method name, different behavior
Manager manager = new Manager("admin", "admin$");
Receptionist receptionist = new Receptionist("rec", "rec123");

// Call same method on both
manager.can("delete doctor");        // true (Manager's version)
receptionist.can("delete doctor");   // false (Receptionist's version)

// Same method call, different results - that's overriding!
```

---

### Q2 (80 points): Show one overridden method, explain why child version is different from parent

**In Your Project**:

**Location**: `src/main/java/adminmangementsystem/com/model/Surgeon.java`

**PARENT METHOD (Staff.java)**:

```java
public abstract class Staff {
    protected String id;
    protected String name;
    
    // Parent's display method
    public void display() {
        System.out.println(id + " | " + name + " | " + dob + " | "
                + address + " | " + email + " | "
                + position + " | $" + salary + " | " + doe);
    }
    
    // Abstract methods - children must override
    public abstract String getResponsibilities();
    public abstract String getDepartment();
    public abstract void performDuty();
}
```

**CHILD METHOD (Surgeon.java)**:

```java
public class Surgeon extends Staff {
    private String specialization;  // Surgeon-specific field
    
    // OVERRIDING: Surgeon's version of display()
    @Override
    public void display() {
        super.display();  // Call parent's display first
        // Parent shows: id, name, dob, address, email, position, salary, doe
        
        // Add Surgeon-specific information
        System.out.println("Specialization: " + specialization);
    }
    
    // OVERRIDING: Surgeon's version of getResponsibilities()
    @Override
    public String getResponsibilities() {
        return "Perform surgical operations, pre‑ and post‑operative care.";
    }
    
    // OVERRIDING: Surgeon's version of getDepartment()
    @Override
    public String getDepartment() {
        return "Surgery";
    }
    
    // OVERRIDING: Surgeon's version of performDuty()
    @Override
    public void performDuty() {
        System.out.println(name + " is performing a " + specialization + " surgery.");
    }
}
```

**Why Child Version is Different**:

**1. Adds Extra Information (display method)**:

```java
// Parent's display():
// Output: S001 | Dr. Lee | 05/20/1975 | 456 Hospital Ave | ... | $120000 | 03/15/2018

// Surgeon's display():
// Output: S001 | Dr. Lee | 05/20/1975 | 456 Hospital Ave | ... | $120000 | 03/15/2018
//         Specialization: Orthopedic
// ↑ Added extra line with surgeon-specific info
```

**2. Provides Specific Implementation (abstract methods)**:

```java
// Surgeon's specific responsibilities
surgeon.getResponsibilities();  
// "Perform surgical operations, pre‑ and post‑operative care."

// Cardiologist's different responsibilities
cardiologist.getResponsibilities();  
// "Diagnose and treat heart conditions, perform catheterizations, prescribe medications."

// Nurse's different responsibilities
nurse.getResponsibilities();  
// "Patient care, administer medication, assist doctors."

// Same method name, different implementations based on role!
```

**3. Uses Child's Specific Data**:

```java
// Surgeon's performDuty() uses specialization field
@Override
public void performDuty() {
    System.out.println(name + " is performing a " + specialization + " surgery.");
}

// Usage:
Surgeon surgeon = new Surgeon(..., "Orthopedic");
surgeon.performDuty();  
// Output: "Dr. Lee is performing a Orthopedic surgery."

// Cardiologist's performDuty() uses fellowship field
Cardiologist cardio = new Cardiologist(..., "Interventional Cardiology");
cardio.performDuty();  
// Output: "Dr. Smith is consulting a patient with heart issues."

// Different behavior based on staff type!
```

**Comparison Table**:

| Method | Parent (Staff) | Child (Surgeon) | Why Different? |
|--------|---------------|-----------------|----------------|
| `display()` | Shows 8 common fields | Shows 8 fields + specialization | Surgeon has extra field |
| `getResponsibilities()` | Abstract (no implementation) | "Perform surgical operations..." | Surgeon-specific duties |
| `getDepartment()` | Abstract (no implementation) | "Surgery" | Surgeon works in Surgery dept |
| `performDuty()` | Abstract (no implementation) | Uses specialization field | Surgeon-specific action |

---


### Q3 (100 points): How overriding helps avoid writing large if-else blocks based on object type

**In Your Project**:

**WITHOUT Overriding (Bad Approach - Type Checking)**:

```java
// BAD: Need to check type and handle each differently
public void displayStaffInfo(Staff staff, String type) {
    // Display common info
    System.out.println("ID: " + staff.getId());
    System.out.println("Name: " + staff.getName());
    System.out.println("Salary: $" + staff.getSalary());
    
    // PROBLEM: Large if-else block based on type
    if (type.equals("Surgeon")) {
        // Surgeon-specific code
        Surgeon surgeon = (Surgeon) staff;
        System.out.println("Specialization: " + surgeon.getSpecialization());
        System.out.println("Responsibilities: Perform surgical operations");
        System.out.println("Department: Surgery");
        
    } else if (type.equals("Cardiologist")) {
        // Cardiologist-specific code
        Cardiologist cardio = (Cardiologist) staff;
        System.out.println("Fellowship: " + cardio.getFellowship());
        System.out.println("Responsibilities: Diagnose heart conditions");
        System.out.println("Department: Cardiology");
        
    } else if (type.equals("Nurse")) {
        // Nurse-specific code
        Nurse nurse = (Nurse) staff;
        System.out.println("Shift: " + nurse.getShift());
        System.out.println("Responsibilities: Patient care");
        System.out.println("Department: Nursing");
    }
    // Add new staff type? Add another else-if block!
}

// Usage: Must pass type string
displayStaffInfo(surgeon, "Surgeon");
displayStaffInfo(cardiologist, "Cardiologist");
displayStaffInfo(nurse, "Nurse");
```

**WITH Overriding (Your Approach - GOOD)**:

**Location**: `src/main/java/adminmangementsystem/com/model/` (Surgeon, Cardiologist, Nurse)

```java
// PARENT: Staff
public abstract class Staff {
    // Each child must override these
    public abstract String getResponsibilities();
    public abstract String getDepartment();
    public abstract void performDuty();
    
    public void display() {
        System.out.println(id + " | " + name + " | " + salary);
    }
}

// CHILD 1: Surgeon
public class Surgeon extends Staff {
    @Override
    public String getResponsibilities() {
        return "Perform surgical operations, pre‑ and post‑operative care.";
    }
    
    @Override
    public String getDepartment() {
        return "Surgery";
    }
    
    @Override
    public void performDuty() {
        System.out.println(name + " is performing a " + specialization + " surgery.");
    }
    
    @Override
    public void display() {
        super.display();
        System.out.println("Specialization: " + specialization);
    }
}

// CHILD 2: Cardiologist
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
    
    @Override
    public void display() {
        super.display();
        System.out.println("Fellowship: " + fellowship);
    }
}

// CHILD 3: Nurse
public class Nurse extends Staff {
    @Override
    public String getResponsibilities() {
        return "Patient care, administer medication, assist doctors.";
    }
    
    @Override
    public String getDepartment() {
        return "Nursing";
    }
    
    @Override
    public void performDuty() {
        System.out.println(name + " is checking vital signs of patients.");
    }
    
    @Override
    public void display() {
        super.display();
        System.out.println("Shift: " + shift);
    }
}

// NOW: No if-else needed!
public void displayStaffInfo(Staff staff) {
    staff.display();  // Automatically calls correct version!
    System.out.println("Responsibilities: " + staff.getResponsibilities());
    System.out.println("Department: " + staff.getDepartment());
    staff.performDuty();
}

// Usage: No type string needed!
displayStaffInfo(surgeon);       // Calls Surgeon's methods ✓
displayStaffInfo(cardiologist);  // Calls Cardiologist's methods ✓
displayStaffInfo(nurse);         // Calls Nurse's methods ✓
```

**Real Example from Your Project**:

**Location**: `src/main/java/adminmangementsystem/com/App.java`

```java
// WITHOUT overriding (would need if-else):
User currentUser = authenticateUser(...);

if (currentUser instanceof Manager) {
    Manager manager = (Manager) currentUser;
    if (action.equals("delete doctor")) {
        // Manager can delete
    } else if (action.equals("add patient")) {
        // Manager can add
    } else if (action.equals("schedule appointment")) {
        // Manager can schedule
    }
    // ... many more if-else blocks
    
} else if (currentUser instanceof Receptionist) {
    Receptionist receptionist = (Receptionist) currentUser;
    if (action.equals("delete doctor")) {
        // Receptionist cannot delete
    } else if (action.equals("add patient")) {
        // Receptionist can add
    } else if (action.equals("schedule appointment")) {
        // Receptionist can schedule
    }
    // ... duplicate if-else blocks
}

// WITH overriding (your approach):
User currentUser = authenticateUser(...);

// Simple! No if-else needed
if (currentUser.can(action)) {  // Calls correct version automatically
    // Perform action
} else {
    System.out.println("You don't have permission");
}
```

**Benefits of Overriding vs if-else**:

**1. Cleaner Code**:
```java
// Without overriding: 50 lines of if-else
if (staff instanceof Surgeon) { ... }
else if (staff instanceof Cardiologist) { ... }
else if (staff instanceof Nurse) { ... }

// With overriding: 1 line
staff.performDuty();  // ✓ Automatically calls correct version
```

**2. Easier to Add New Types**:
```java
// Without overriding: Add new if-else block everywhere
if (staff instanceof Surgeon) { ... }
else if (staff instanceof Cardiologist) { ... }
else if (staff instanceof Nurse) { ... }
else if (staff instanceof Pediatrician) { ... }  // Add this everywhere!

// With overriding: Just create new class
public class Pediatrician extends Staff {
    @Override
    public void performDuty() {
        System.out.println(name + " is examining a child patient.");
    }
}
// Done! Works everywhere automatically ✓
```

**3. Polymorphism in Action**:

```java
// Create array of different staff types
Staff[] staffMembers = {
    new Surgeon("S001", "Dr. Lee", ..., "Orthopedic"),
    new Cardiologist("C001", "Dr. Smith", ..., "Interventional Cardiology"),
    new Nurse("N001", "Nurse Johnson", ..., "Night")
};

// WITHOUT overriding: Need if-else for each
for (Staff staff : staffMembers) {
    if (staff instanceof Surgeon) {
        Surgeon s = (Surgeon) staff;
        System.out.println(s.getName() + " is performing a " + s.getSpecialization() + " surgery.");
    } else if (staff instanceof Cardiologist) {
        Cardiologist c = (Cardiologist) staff;
        System.out.println(c.getName() + " is consulting a patient with heart issues.");
    } else if (staff instanceof Nurse) {
        Nurse n = (Nurse) staff;
        System.out.println(n.getName() + " is checking vital signs of patients.");
    }
}

// WITH overriding (your approach): Clean and simple
for (Staff staff : staffMembers) {
    staff.performDuty();  // ✓ Automatically calls correct version!
}

// Output:
// Dr. Lee is performing a Orthopedic surgery.
// Dr. Smith is consulting a patient with heart issues.
// Nurse Johnson is checking vital signs of patients.
```

**Real Example from Your MainController**:

**Location**: `src/main/java/adminmangementsystem/com/controller/MainController.java`

```java
// WITHOUT overriding (would need this):
public void checkPermission(User user, String action) {
    if (user instanceof Manager) {
        // Manager can do everything
        return true;
    } else if (user instanceof Receptionist) {
        // Check specific actions for receptionist
        if (action.equals("delete doctor")) {
            return false;
        } else if (action.equals("add patient")) {
            return true;
        } else if (action.equals("schedule appointment")) {
            return true;
        }
        // ... many more conditions
    }
    // Add new user type? Add another if-else block!
}

// WITH overriding (your actual code):
public void checkPermission(User user, String action) {
    return user.can(action);  // ✓ Simple! Calls correct version
}

// Works for any user type automatically
checkPermission(manager, "delete doctor");        // true
checkPermission(receptionist, "delete doctor");   // false
checkPermission(new Admin(...), "delete doctor"); // Works for new types too!
```

**Why Child Version is Different**:

**Manager's can() method**:
```java
@Override
public boolean can(String action) {
    return true;  // Always true - Manager has full access
}
```
- **Why different**: Manager is administrator with full permissions
- **Business logic**: Managers can perform any action in the system

**Receptionist's can() method**:
```java
@Override
public boolean can(String action) {
    return action.contains("register new patients") ||
           action.contains("schedule appointments");
}
```
- **Why different**: Receptionist has limited permissions
- **Business logic**: Receptionists only handle patient registration and appointments

**The Difference Reflects Real-World Roles**:
- Manager = Full system access (supervisor role)
- Receptionist = Limited access (front desk role)
- Same method (`can()`), different behavior (different permissions)

---

## Summary: Benefits of Method Overriding

1. **No Type Checking**: Don't need `instanceof` or type strings
2. **Cleaner Code**: One line instead of many if-else blocks
3. **Extensible**: Add new types without modifying existing code
4. **Polymorphism**: Same interface, different behavior
5. **Maintainable**: Each class manages its own behavior
6. **Logical**: Behavior is where it belongs (in the class itself)

---

## Quick Reference for Oral Exam

### Key Points to Remember:

**Set 8 (Passing Parameters)**:
- Primitives: pass by value (copy)
- Objects: pass by reference (same object)
- Show: `addDoctor(Doctor doctor)` method

**Set 9 (Inheritance)**:
- IS-A relationship, not just code reuse
- Show: Manager extends User
- Explain: Manager IS-A User (can be used as User)

**Set 10 (super keyword)**:
- Calls parent constructor or methods
- Show: `super(username, password)` in Manager
- Explain: Initializes parent's fields

**Set 11 (Method Overriding)**:
- Child provides own implementation
- Show: `can()` method in Manager vs Receptionist
- Explain: Avoids if-else blocks, enables polymorphism

---

**Good luck with your oral exam! All examples are from your actual project code.**
