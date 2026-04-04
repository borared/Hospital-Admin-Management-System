# Hospital Admin Management System - OOP Concepts Guide

## Overview
This document explains all OOP concepts used in the system with clear examples and comments.

---

## 1. ENCAPSULATION (Data Hiding)

**What it is**: Hiding internal data and providing controlled access through methods.

**Where it's used**:

### Example 1: User Class (`src/main/java/adminmangementsystem/com/user/User.java`)
```java
public abstract class User implements IStaff {
    // ENCAPSULATION: Private fields - cannot be accessed directly from outside
    private String username;
    private String password;
    
    // ENCAPSULATION: Public getter - controlled read access
    public String getUsername() {
        return username;
    }
    
    // ENCAPSULATION: Public setter - controlled write access with validation
    public void setUsername(String username) {
        this.username = username;
    }
}
```

### Example 2: Doctor Model (`src/main/java/adminmangementsystem/com/model/Doctor.java`)
```java
public class Doctor {
    // ENCAPSULATION: Private fields
    private String id;
    private String name;
    private double salary;
    
    // ENCAPSULATION: Setter with validation
    public boolean setSalary(double salary) {
        if (salary > 0) {  // Validation logic hidden inside
            this.salary = salary;
            return true;
        }
        return false;
    }
}
```

**Benefits**:
- Protects data from invalid values
- Can change internal implementation without affecting other code
- Provides validation in one place

---

## 2. INHERITANCE (IS-A Relationship)

**What it is**: A class inherits properties and methods from a parent class.

**Where it's used**:

### Example 1: User Hierarchy (`src/main/java/adminmangementsystem/com/user/`)
```java
// INHERITANCE: User is the parent (base) class
public abstract class User {
    private String username;
    private String password;
    
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}

// INHERITANCE: Manager IS-A User (inherits username, password, login method)
public class Manager extends User {
    public Manager(String username, String password) {
        super(username, password);  // Call parent constructor
    }
}

// INHERITANCE: Receptionist IS-A User (inherits username, password, login method)
public class Receptionist extends User {
    public Receptionist(String username, String password) {
        super(username, password);  // Call parent constructor
    }
}
```

### Example 2: Management System Hierarchy
```java
// INHERITANCE: Abstract parent class
public abstract class AbstractManagementSystem<T> {
    protected List<T> records = new ArrayList<>();
    
    public int getCount() {
        return records.size();
    }
}

// INHERITANCE: DoctorSystem IS-A AbstractManagementSystem
public class DoctorSystem extends AbstractManagementSystem<Doctor> {
    // Inherits records list and getCount() method
    // Adds doctor-specific methods
}
```

**Benefits**:
- Code reuse - don't repeat common code
- Logical hierarchy - models real-world relationships
- Easy to maintain - change parent affects all children

---

## 3. POLYMORPHISM (Many Forms)

**What it is**: Same method name behaves differently in different classes.

**Where it's used**:

### Example 1: Method Overriding (`src/main/java/adminmangementsystem/com/user/`)
```java
// POLYMORPHISM: Interface defines the contract
public interface IStaff {
    boolean can(String action);
}

// POLYMORPHISM: Manager overrides can() - returns true for everything
public class Manager extends User {
    @Override
    public boolean can(String action) {
        return true;  // Manager can do anything
    }
}

// POLYMORPHISM: Receptionist overrides can() - returns true only for specific actions
public class Receptionist extends User {
    @Override
    public boolean can(String action) {
        return action.contains("register new patients") ||
               action.contains("schedule appointments");
    }
}

// POLYMORPHISM IN ACTION:
User user1 = new Manager("admin", "pass");
User user2 = new Receptionist("rec", "pass");

user1.can("delete doctor");  // Returns true (Manager can do anything)
user2.can("delete doctor");  // Returns false (Receptionist cannot)
```

### Example 2: Runtime Polymorphism (`src/main/java/adminmangementsystem/com/controller/MainController.java`)
```java
public void start(Scanner sc, User currentUser) {
    // POLYMORPHISM: instanceof checks the actual type at runtime
    if (currentUser instanceof Manager) {
        handleManagerMenu(sc, currentUser);  // Show full menu
    } else if (currentUser instanceof Receptionist) {
        handleReceptionistMenu(sc, currentUser);  // Show limited menu
    }
}
```

**Benefits**:
- Same interface, different behavior
- Flexible code - easy to add new user types
- Runtime decision making

---

## 4. ABSTRACTION (Hiding Complexity)

**What it is**: Showing only essential features, hiding implementation details.

**Where it's used**:

### Example 1: Abstract Class (`src/main/java/adminmangementsystem/com/management/AbstractManagementSystem.java`)
```java
// ABSTRACTION: Abstract class - cannot create objects directly
public abstract class AbstractManagementSystem<T> {
    protected List<T> records = new ArrayList<>();
    
    // ABSTRACTION: Concrete method - implementation provided
    public int getCount() {
        return records.size();
    }
    
    // ABSTRACTION: Abstract method - subclasses must implement
    public abstract void displayAll();
}

// Concrete class provides implementation
public class DoctorSystem extends AbstractManagementSystem<Doctor> {
    @Override
    public void displayAll() {
        // Specific implementation for displaying doctors
        for (Doctor d : records) {
            d.display();
        }
    }
}
```

### Example 2: Interface (`src/main/java/adminmangementsystem/com/management/IDoctorSystem.java`)
```java
// ABSTRACTION: Interface - defines what to do, not how
public interface IDoctorSystem {
    void addDoctor(Doctor doctor);
    boolean deleteDoctor(String id);
    Doctor searchDoctorById(String id);
}

// Implementation provides the "how"
public class DoctorSystem implements IDoctorSystem {
    @Override
    public void addDoctor(Doctor doctor) {
        // Actual implementation here
        records.add(doctor);
    }
}
```

**Benefits**:
- Simplifies complex systems
- Focuses on "what" not "how"
- Easy to change implementation

---

## 5. EXCEPTION HANDLING (Error Management)

**What it is**: Handling errors gracefully without crashing the program.

**Where it's used**:

### Example 1: Try-Catch (`src/main/java/adminmangementsystem/com/App.java`)
```java
// EXCEPTION HANDLING: Try-catch block
try {
    choice = Integer.parseInt(sc.nextLine());  // Might throw exception
} catch (NumberFormatException e) {
    // EXCEPTION HANDLING: Catch specific exception
    System.out.println("Invalid input. Please enter a number.");
    continue;  // Continue loop instead of crashing
} catch (Exception e) {
    // EXCEPTION HANDLING: Catch any other exception
    System.out.println("An error occurred: " + e.getMessage());
}
```

### Example 2: Try-Catch-Finally
```java
Scanner sc = null;
try {
    sc = new Scanner(System.in);
    int number = Integer.parseInt(sc.nextLine());
    System.out.println("You entered: " + number);
} catch (NumberFormatException e) {
    System.out.println("Invalid number format!");
} finally {
    // EXCEPTION HANDLING: Finally block always executes
    if (sc != null) {
        sc.close();  // Clean up resources
    }
}
```

### Example 3: Throwing Exceptions
```java
public void setSalary(double salary) throws IllegalArgumentException {
    if (salary <= 0) {
        // EXCEPTION HANDLING: Throw exception for invalid data
        throw new IllegalArgumentException("Salary must be positive!");
    }
    this.salary = salary;
}
```

**Benefits**:
- Program doesn't crash on errors
- User-friendly error messages
- Clean resource management

---

## 6. COMPLETE EXAMPLE: Putting It All Together

```java
// ABSTRACTION: Interface
public interface IEmployee {
    double calculateSalary();
    void displayInfo();
}

// INHERITANCE & ABSTRACTION: Abstract base class
public abstract class Employee implements IEmployee {
    // ENCAPSULATION: Private fields
    private String id;
    private String name;
    protected double baseSalary;
    
    // ENCAPSULATION: Constructor
    public Employee(String id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }
    
    // ENCAPSULATION: Getters
    public String getId() { return id; }
    public String getName() { return name; }
    
    // ABSTRACTION: Abstract method - subclasses must implement
    public abstract double calculateSalary();
    
    // POLYMORPHISM: Can be overridden by subclasses
    public void displayInfo() {
        System.out.println("ID: " + id + ", Name: " + name);
    }
}

// INHERITANCE: Manager IS-AN Employee
public class Manager extends Employee {
    private double bonus;
    
    public Manager(String id, String name, double baseSalary, double bonus) {
        super(id, name, baseSalary);  // INHERITANCE: Call parent constructor
        this.bonus = bonus;
    }
    
    // POLYMORPHISM: Override abstract method
    @Override
    public double calculateSalary() {
        return baseSalary + bonus;  // Manager gets base + bonus
    }
    
    // POLYMORPHISM: Override parent method
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call parent method
        System.out.println("Role: Manager, Bonus: $" + bonus);
    }
}

// INHERITANCE: Developer IS-AN Employee
public class Developer extends Employee {
    private int projectCount;
    
    public Developer(String id, String name, double baseSalary, int projectCount) {
        super(id, name, baseSalary);
        this.projectCount = projectCount;
    }
    
    // POLYMORPHISM: Different implementation than Manager
    @Override
    public double calculateSalary() {
        return baseSalary + (projectCount * 500);  // Developer gets base + project bonus
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Role: Developer, Projects: " + projectCount);
    }
}

// USING THE CLASSES:
public class Main {
    public static void main(String[] args) {
        // POLYMORPHISM: Array of Employee can hold Manager and Developer
        Employee[] employees = {
            new Manager("M001", "John", 5000, 2000),
            new Developer("D001", "Alice", 4000, 5)
        };
        
        // EXCEPTION HANDLING
        try {
            for (Employee emp : employees) {
                emp.displayInfo();  // POLYMORPHISM: Calls correct version
                System.out.println("Salary: $" + emp.calculateSalary());  // POLYMORPHISM
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

---

## Summary of OOP Benefits

1. **Encapsulation**: Protects data, easier to maintain
2. **Inheritance**: Code reuse, logical hierarchy
3. **Polymorphism**: Flexible, extensible code
4. **Abstraction**: Simplifies complexity, focuses on essentials
5. **Exception Handling**: Robust, user-friendly programs

---

## Quick Reference: Where to Find Each Concept

| Concept | File Location | Line Numbers |
|---------|--------------|--------------|
| Encapsulation | `user/User.java` | Lines 10-40 |
| Inheritance | `user/Manager.java`, `user/Receptionist.java` | Entire files |
| Polymorphism | `user/Manager.java` (can method) | Lines 15-20 |
| Abstraction | `management/AbstractManagementSystem.java` | Entire file |
| Exception Handling | `App.java` (login loop) | Lines 50-60 |
| Interface | `user/IStaff.java` | Entire file |

---

## Tips for Understanding OOP

1. **Encapsulation**: Think of it as a capsule - data is protected inside
2. **Inheritance**: Think of family tree - children inherit from parents
3. **Polymorphism**: Think of actors - same person, different roles
4. **Abstraction**: Think of a car - you drive it without knowing engine details
5. **Exception Handling**: Think of safety net - catches errors before crash
