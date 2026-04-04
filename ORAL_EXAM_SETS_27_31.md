# Oral Exam Preparation - Sets 27-31

## Set 27

### Q1 (60): What is method overloading, and how is it different from method overriding?

**Method overloading**: Multiple methods in the same class with the same name but different parameters (different number, type, or order). Happens at compile-time.

**Method overriding**: A subclass provides a different implementation of a method that already exists in the parent class. Same name AND same parameters. Happens at runtime.

**Key difference**: Overloading = same name, different parameters, same class. Overriding = same name, same parameters, different classes (parent/child).

### Q2 (80): Explain a situation in your project where overloading would be useful.

```java
public class PatientSystem {
    // Overloaded methods - same name, different parameters
    
    // Version 1: Search by ID
    public Patient searchPatient(String id) {
        return searchPatientById(id);
    }
    
    // Version 2: Search by name and DOB
    public List<Patient> searchPatient(String name, String dob) {
        List<Patient> results = new ArrayList<>();
        for (Patient p : records) {
            if (p.getName().equals(name) && p.getDob().equals(dob)) {
                results.add(p);
            }
        }
        return results;
    }
    
    // Version 3: Search by multiple criteria
    public List<Patient> searchPatient(String name, String dob, String disease) {
        List<Patient> results = new ArrayList<>();
        for (Patient p : records) {
            if (p.getName().equals(name) && 
                p.getDob().equals(dob) && 
                p.getDisease().equals(disease)) {
                results.add(p);
            }
        }
        return results;
    }
}

// Usage:
Patient p1 = system.searchPatient("P001");              // Calls version 1
List<Patient> p2 = system.searchPatient("John", "01/01/1990");  // Calls version 2
List<Patient> p3 = system.searchPatient("John", "01/01/1990", "Flu");  // Calls version 3
```

This makes the API more flexible - users can search with different amounts of information.

### Q3 (100): Compare overloading and overriding in terms of extensibility and design value.

**Overloading - Convenience & Flexibility:**

Pros:
- Provides multiple ways to call the same logical operation
- Makes API easier to use (don't need different method names)
- Compile-time safety - errors caught early

Cons:
- Can be confusing if overloaded versions do very different things
- Doesn't support polymorphism
- Limited extensibility - can't add overloads from subclasses

**Overriding - Polymorphism & Extensibility:**

Pros:
- Enables polymorphism - core of OOP design
- Highly extensible - add new subclasses without changing existing code
- Runtime flexibility - behavior determined by actual object type

Cons:
- Must maintain same method signature (less flexible)
- Can break if parent contract changes
- Harder to debug (which version runs?)

**Example comparison:**

```java
// OVERLOADING: Convenience, not extensible
public class Calculator {
    public int add(int a, int b) { return a + b; }
    public double add(double a, double b) { return a + b; }
    public int add(int a, int b, int c) { return a + b + c; }
}

// OVERRIDING: Extensibility, polymorphism
public abstract class Staff {
    public abstract void performDuty();  // Contract
}

public class Cardiologist extends Staff {
    @Override
    public void performDuty() {
        System.out.println("Consulting heart patient");
    }
}

public class Surgeon extends Staff {
    @Override
    public void performDuty() {
        System.out.println("Performing surgery");
    }
}

// Can add new staff types without changing existing code
Staff[] staff = {new Cardiologist(), new Surgeon()};
for (Staff s : staff) {
    s.performDuty();  // Polymorphism through overriding
}
```

**Design value**: Use overloading for convenience and API usability. Use overriding for extensibility and polymorphic behavior. Overriding is more powerful for OOP design.

---

## Set 28

### Q1 (60): What is an ArrayList, and how is it different from a normal array?

**ArrayList**: A dynamic, resizable collection from Java's Collections framework. Can grow and shrink automatically.

**Normal array**: Fixed size, set at creation time. Cannot change size after creation.

**Key differences:**
- Array: `Patient[] patients = new Patient[10];` - fixed at 10
- ArrayList: `ArrayList<Patient> patients = new ArrayList<>();` - grows as needed
- ArrayList provides methods like `add()`, `remove()`, `contains()`
- Arrays are faster but less flexible

### Q2 (80): Show where you used an ArrayList in your project and explain why it was a better choice.

```java
public class PatientSystem implements IPatientSystem {
    // ArrayList - dynamic size
    private List<Patient> records = new ArrayList<>();
    
    @Override
    public void addPatient(Patient patient) {
        records.add(patient);  // No size limit, grows automatically
    }
    
    @Override
    public boolean deletePatient(String id) {
        Patient patient = searchPatientById(id);
        if (patient != null) {
            records.remove(patient);  // Easy removal, list shrinks
            return true;
        }
        return false;
    }
}
```

**Why ArrayList is better here:**

1. **Unknown size**: We don't know how many patients will be registered. With an array, we'd have to guess (100? 1000?) and either waste memory or run out of space.

2. **Easy removal**: `records.remove(patient)` automatically shifts elements. With an array, we'd need manual shifting logic.

3. **Flexible operations**: Can use `records.isEmpty()`, `records.size()`, `records.contains()` without writing custom code.

4. **No null gaps**: Arrays leave null gaps when you remove elements. ArrayList maintains a continuous list.

### Q3 (100): Explain how ArrayList makes object management easier when the number of items can change.

**Problem with arrays:**

```java
// BAD: Using array
public class PatientSystem {
    private Patient[] records = new Patient[100];  // Fixed size
    private int count = 0;
    
    public void addPatient(Patient patient) {
        if (count >= records.length) {
            // Need to manually resize!
            Patient[] newArray = new Patient[records.length * 2];
            for (int i = 0; i < records.length; i++) {
                newArray[i] = records[i];
            }
            records = newArray;
        }
        records[count++] = patient;
    }
    
    public void deletePatient(String id) {
        // Find and remove
        for (int i = 0; i < count; i++) {
            if (records[i].getId().equals(id)) {
                // Manually shift all elements left
                for (int j = i; j < count - 1; j++) {
                    records[j] = records[j + 1];
                }
                records[--count] = null;
                break;
            }
        }
    }
}
```

**Solution with ArrayList:**

```java
// GOOD: Using ArrayList
public class PatientSystem {
    private List<Patient> records = new ArrayList<>();
    
    public void addPatient(Patient patient) {
        records.add(patient);  // Automatic resizing!
    }
    
    public void deletePatient(String id) {
        records.removeIf(p -> p.getId().equals(id));  // Automatic shifting!
    }
}
```

**Benefits:**

1. **Automatic memory management**: ArrayList handles resizing internally - grows by ~50% when full

2. **No manual bookkeeping**: Don't need to track `count` or manage null values

3. **Cleaner code**: Less boilerplate, fewer bugs

4. **Better performance**: ArrayList's internal array management is optimized

5. **Rich API**: Built-in methods for searching, sorting, filtering

6. **Type safety with generics**: `ArrayList<Patient>` prevents adding wrong types

ArrayList abstracts away the complexity of dynamic array management, letting you focus on business logic instead of low-level array manipulation.

---

## Set 29

### Q1 (60): Why is it useful to store objects inside a collection of a common type?

Storing objects of a common type (like a parent class or interface) in one collection allows you to treat different objects uniformly. You can loop through them, call common methods, and process them together without knowing their specific types. This enables polymorphism and makes code more flexible.

### Q2 (80): Show how inheritance or interfaces allow multiple objects to be kept in the same list.

```java
// Using inheritance - Staff is the common parent type
public class StaffSystem<T extends Staff> {
    private List<T> records = new ArrayList<>();
    
    public void displayAll() {
        for (T staff : records) {
            staff.display();           // Common method from Staff
            staff.performDuty();       // Common abstract method
            System.out.println("Department: " + staff.getDepartment());
        }
    }
}

// Can store different staff types in the same list
List<Staff> allStaff = new ArrayList<>();
allStaff.add(new Cardiologist("C001", "Dr. Smith", "01/01/1980", "123 St", 
                              "smith@email.com", "Cardiologist", 150000, "01/01/2020", 
                              "Interventional Cardiology"));
allStaff.add(new Surgeon("S001", "Dr. Jones", "02/02/1975", "456 Ave", 
                         "jones@email.com", "Surgeon", 180000, "01/01/2018", 
                         "Cardiac Surgery"));
allStaff.add(new Nurse("N001", "Jane Doe", "03/03/1990", "789 Blvd", 
                       "jane@email.com", "Nurse", 60000, "01/01/2021", 
                       "Night"));

// Process all staff uniformly
for (Staff s : allStaff) {
    s.checkIn();              // Common method
    s.performDuty();          // Polymorphic - different for each type
    System.out.println(s.getDepartment());  // Different for each type
}
```

**Using interface:**

```java
// All users implement IStaff interface
User[] users = {
    new Manager("admin", "admin$$"),
    new Receptionist("receptionist", "rec123")
};

// Can check permissions uniformly
for (User user : users) {
    if (user.can("manage doctors")) {
        System.out.println(user.getUsername() + " has permission");
    }
}
```

### Q3 (100): Explain how that design helps when you need to process many similar objects using one loop.

**Without common type - messy and inflexible:**

```java
// BAD: Separate lists for each type
public class HospitalSystem {
    private List<Cardiologist> cardiologists = new ArrayList<>();
    private List<Surgeon> surgeons = new ArrayList<>();
    private List<Nurse> nurses = new ArrayList<>();
    
    public void checkInAllStaff() {
        // Need separate loops for each type
        for (Cardiologist c : cardiologists) {
            c.checkIn();
        }
        for (Surgeon s : surgeons) {
            s.checkIn();
        }
        for (Nurse n : nurses) {
            n.checkIn();
        }
    }
    
    public void displayAllStaff() {
        // Duplicate code for each type
        for (Cardiologist c : cardiologists) {
            c.display();
        }
        for (Surgeon s : surgeons) {
            s.display();
        }
        for (Nurse n : nurses) {
            n.display();
        }
    }
    
    // Adding new staff type requires modifying ALL methods!
}
```

**With common type - clean and extensible:**

```java
// GOOD: Single list with common type
public class HospitalSystem {
    private List<Staff> allStaff = new ArrayList<>();
    
    public void checkInAllStaff() {
        // One loop handles all types
        for (Staff s : allStaff) {
            s.checkIn();
        }
    }
    
    public void displayAllStaff() {
        // One loop, polymorphic behavior
        for (Staff s : allStaff) {
            s.display();           // Calls appropriate version
            s.performDuty();       // Different for each type
        }
    }
    
    public void calculateTotalSalary() {
        double total = 0;
        for (Staff s : allStaff) {
            total += s.getSalary();  // Works for all staff types
        }
        return total;
    }
    
    // Adding new staff type (Technician) requires NO changes here!
}
```

**Benefits:**

1. **Code reuse**: Write the loop once, works for all types

2. **Maintainability**: Changes to processing logic happen in one place

3. **Extensibility**: Add new staff types without modifying existing loops

4. **Polymorphism**: Each object behaves according to its actual type

5. **Uniform operations**: Can apply common operations (checkIn, getSalary) to all objects

6. **Reduced complexity**: One list instead of many, one loop instead of many

This is the power of polymorphism - write code that works with the general type, and it automatically works with all specific types. The loop doesn't need to know whether it's processing a Cardiologist or Surgeon - it just knows it's processing Staff.

---

## Set 30

### Q1 (60): Why is validation important when changing object state?

Validation ensures that objects maintain valid, consistent state. Without validation, you could set invalid data (negative salary, empty name, invalid email) that breaks business rules and causes errors later. Validation protects object integrity and prevents bugs.

### Q2 (80): Show one method in your project that changes an object and explain what should be validated.

```java
public class Patient {
    private String name;
    private String dob;
    private String email;
    
    // Setter with validation
    public boolean setName(String name) {
        // Validation: not null, not empty, only letters and spaces, max 50 chars
        if (name != null && !name.isEmpty() && name.matches("[a-zA-Z ]{1,50}")) {
            this.name = name;
            return true;
        }
        return false;  // Validation failed
    }
    
    public boolean setDob(String dob) {
        // Validation: matches date format DD/MM/YYYY
        if (dob != null && !dob.isEmpty() && dob.matches("\\d{2}/\\d{2}/\\d{4}")) {
            this.dob = dob;
            return true;
        }
        return false;
    }
}
```

**What's being validated:**

1. **Name**: Not null, not empty, only alphabetic characters and spaces, length 1-50
2. **DOB**: Matches specific date format (DD/MM/YYYY)

**Why validation matters**: Without it, you could have a patient with name "123!@#" or DOB "invalid", which would break reports, searches, and display logic.

### Q3 (100): Explain where object-level rules should be enforced so the rest of the program stays safe.

**Principle: Validate at the boundary - in setters and constructors**

```java
public class Staff {
    private String id;
    private String name;
    private double salary;
    
    // Constructor validation - first line of defense
    public Staff(String id, String name, double salary) {
        // Validate in constructor
        if (!setId(id) || !setName(name) || !setSalary(salary)) {
            throw new IllegalArgumentException("Invalid staff data");
        }
    }
    
    // Setter validation - enforce rules
    public boolean setId(String id) {
        if (id != null && !id.trim().isEmpty()) {
            this.id = id;
            return true;
        }
        return false;  // Invalid ID rejected
    }
    
    public boolean setName(String name) {
        if (name != null && name.matches("[a-zA-Z ]{1,50}")) {
            this.name = name;
            return true;
        }
        return false;  // Invalid name rejected
    }
    
    public boolean setSalary(double salary) {
        if (salary > 0) {  // Business rule: salary must be positive
            this.salary = salary;
            return true;
        }
        return false;  // Negative salary rejected
    }
}
```

**Why enforce at object level:**

**1. Single source of truth:**
```java
// GOOD: Validation in one place
staff.setSalary(50000);  // Validated here

// BAD: Validation scattered everywhere
if (salary > 0) {  // Validation in controller
    staff.salary = salary;
}
if (salary > 0) {  // Duplicate validation in service
    staff.salary = salary;
}
```

**2. Impossible to create invalid objects:**
```java
// Constructor validation prevents this
Staff s = new Staff("", "", -1000);  // Throws exception immediately
```

**3. Rest of program can trust the data:**
```java
public class PayrollSystem {
    public void calculatePay(Staff staff) {
        // No need to check if salary is valid - object guarantees it
        double pay = staff.getSalary() * 1.1;
        // No risk of negative salary breaking calculations
    }
}
```

**4. Centralized business rules:**
```java
// Business rule: "Salary must be positive" lives in Staff class
// If rule changes (e.g., "Salary must be >= minimum wage"), 
// change it in ONE place (setSalary method)
```

**Layered validation approach:**

```java
// Layer 1: Object-level (Staff class)
public boolean setSalary(double salary) {
    if (salary > 0) {  // Basic validity
        this.salary = salary;
        return true;
    }
    return false;
}

// Layer 2: Business logic (StaffSystem)
public void updateStaffSalary(String id, double newSalary) {
    Staff staff = findById(id);
    if (newSalary < MINIMUM_WAGE) {  // Business rule
        throw new IllegalArgumentException("Below minimum wage");
    }
    if (!staff.setSalary(newSalary)) {  // Object validation
        throw new IllegalArgumentException("Invalid salary");
    }
}

// Layer 3: UI validation (View)
public double getSalaryInput(Scanner sc) {
    // Validate format before sending to business layer
    while (!sc.hasNextDouble()) {
        System.out.println("Please enter a number");
        sc.next();
    }
    return sc.nextDouble();
}
```

**Result**: The rest of the program can safely assume all Staff objects have valid data, eliminating defensive checks everywhere and preventing bugs.

---

## Set 31

### Q1 (60): What is an exception in Java, and how is it different from a compile-time syntax error?

**Exception**: A runtime error that occurs during program execution (like dividing by zero, null pointer, file not found). The code compiles fine but fails when running.

**Compile-time syntax error**: A mistake in code syntax that prevents compilation (like missing semicolon, wrong type, undefined variable). The code won't even compile.

**Key difference**: Syntax errors are caught before running, exceptions happen while running.

### Q2 (80): Show one place in your project where invalid data or bad input could cause a runtime exception.

```java
public class Patient {
    private final String id;
    private String name;
    
    // Constructor that throws exception
    public Patient(String patId, String patName, String patDOB,
                   String patDisease, String patPhoneNumber, String patDOE) {
        // Could throw IllegalArgumentException at runtime
        if (patId == null || patId.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient ID cannot be null or empty");
        }
        this.id = patId.trim();
        
        // Could throw exception if validation fails
        if(!setName(patName) ||
           !setDob(patDOB) ||
           !setDisease(patDisease) ||
           !setAddress(patPhoneNumber) ||
           !setEntryDate(patDOE)) {
            throw new IllegalArgumentException("Invalid patient data.");
        }
    }
}

// Usage that could cause exception:
try {
    Patient p = new Patient(null, "John", "01/01/1990", "Flu", "123 St", "01/01/2024");
    // Throws IllegalArgumentException because ID is null
} catch (IllegalArgumentException e) {
    System.out.println("Error: " + e.getMessage());
}
```

**Other potential exceptions in the project:**

```java
// NullPointerException
Patient patient = null;
patient.getName();  // Crashes at runtime

// ArrayIndexOutOfBoundsException
User[] users = new User[2];
User u = users[5];  // Crashes - index out of bounds

// NumberFormatException
String input = "abc";
int age = Integer.parseInt(input);  // Crashes - not a number
```

### Q3 (100): Explain why exception handling should support good object design, not replace proper validation and class structure.

**Bad approach: Using exceptions instead of validation**

```java
// WRONG: Using exceptions as control flow
public class Staff {
    private double salary;
    
    public void setSalary(double salary) {
        this.salary = salary;  // No validation!
    }
}

public class StaffSystem {
    public void updateSalary(Staff staff, double newSalary) {
        try {
            staff.setSalary(newSalary);
            // Hope it works, catch problems later
            double tax = calculateTax(newSalary);
        } catch (Exception e) {
            // Trying to catch validation problems with exceptions
            System.out.println("Something went wrong");
        }
    }
}
```

**Good approach: Validation first, exceptions for unexpected errors**

```java
// CORRECT: Proper validation in object
public class Staff {
    private double salary;
    
    public boolean setSalary(double salary) {
        // Validation prevents invalid state
        if (salary > 0) {
            this.salary = salary;
            return true;
        }
        return false;  // Invalid input rejected gracefully
    }
}

public class StaffSystem {
    public void updateSalary(Staff staff, double newSalary) {
        // Validate before attempting
        if (newSalary <= 0) {
            System.out.println("Salary must be positive");
            return;
        }
        
        // Use validation method
        if (!staff.setSalary(newSalary)) {
            System.out.println("Failed to update salary");
            return;
        }
        
        // Exceptions only for truly unexpected errors
        try {
            saveToDatabase(staff);  // External system might fail
        } catch (DatabaseException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
```

**Why exceptions shouldn't replace validation:**

**1. Performance**: Exceptions are expensive - throwing and catching is slow
```java
// SLOW: Using exceptions for validation
for (int i = 0; i < 1000; i++) {
    try {
        staff.setSalary(-100);  // Throws exception 1000 times
    } catch (Exception e) { }
}

// FAST: Using validation
for (int i = 0; i < 1000; i++) {
    if (!staff.setSalary(-100)) {  // Returns false 1000 times
        // Handle gracefully
    }
}
```

**2. Clarity**: Exceptions should be exceptional, not expected
```java
// CONFUSING: Exception for expected case
public void processInput(String input) {
    try {
        int value = Integer.parseInt(input);  // Might fail often
    } catch (NumberFormatException e) {
        // User input errors are expected, not exceptional
    }
}

// CLEAR: Validation for expected cases
public void processInput(String input) {
    if (input.matches("\\d+")) {  // Validate first
        int value = Integer.parseInt(input);
    } else {
        System.out.println("Please enter a number");
    }
}
```

**3. Object integrity**: Validation maintains invariants
```java
// BAD: Object can be in invalid state
public class Patient {
    private String id;
    
    public void setId(String id) {
        this.id = id;  // No validation - could be null or empty
    }
}
// Now patient.getId() might return null - breaks assumptions

// GOOD: Validation ensures valid state
public class Patient {
    private final String id;
    
    public Patient(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID required");
        }
        this.id = id;  // Guaranteed non-null, non-empty
    }
}
// Now patient.getId() is always valid - safe to use
```

**4. Proper separation of concerns**:
- **Validation**: Checks if input meets business rules (expected)
- **Exceptions**: Handles unexpected errors (file not found, network failure, null pointer)

**Best practice:**
```java
public class PatientSystem {
    public void addPatient(Patient patient) {
        // 1. Validation - expected checks
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null");
        }
        
        if (!isIdUnique(patient.getId())) {
            throw new IllegalArgumentException("Patient ID already exists");
        }
        
        // 2. Business logic
        records.add(patient);
        
        // 3. Exceptions - unexpected errors
        try {
            notifyAdmissions(patient);  // External system
        } catch (IOException e) {
            // Truly exceptional - network failure
            logger.error("Failed to notify admissions", e);
        }
    }
}
```

**Summary**: Use validation to prevent invalid states and handle expected errors. Use exceptions for truly unexpected, exceptional situations. Good object design means objects validate themselves and maintain their own integrity, with exceptions as a safety net for the unexpected.
