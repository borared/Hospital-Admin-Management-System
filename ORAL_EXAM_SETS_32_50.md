# Oral Exam Preparation - Sets 32-50

## Set 32: Try-Catch Blocks and Error Handling

### Q1 (60): What is the purpose of a try-catch block in practical program design?
A try-catch block is used to handle runtime exceptions gracefully. It allows the program to catch errors that occur during execution and respond appropriately instead of crashing. The try block contains code that might throw an exception, while the catch block defines how to handle that exception.

### Q2 (80): Show one place in your project where a try-catch block could prevent the program from crashing.
In the project, a try-catch block could be used when parsing user input for IDs or dates:

```java
// In AppointmentController or similar input handling
try {
    int patientId = Integer.parseInt(scanner.nextLine());
    // Process the ID
} catch (NumberFormatException e) {
    System.out.println("Invalid input. Please enter a valid number.");
}
```

This prevents the program from crashing when a user enters non-numeric input where a number is expected.

### Q3 (100): Explain why it is bad practice to rely on try-catch everywhere instead of designing objects and validation properly.
Relying on try-catch everywhere is bad practice because:
- It treats symptoms rather than preventing problems at the source
- It makes code harder to read and understand (exception flow is less clear than normal control flow)
- It can hide design flaws where proper validation or object constraints should exist
- Performance overhead: exceptions are expensive operations
- It violates the principle of "fail fast" - problems should be caught early through validation
- Proper object design with constructors, validation methods, and invariants prevents invalid states from ever existing


## Set 33: Prevention vs. Catching Errors

### Q1 (60): What is the difference between preventing an error and catching an error after it happens?
Preventing an error means validating inputs and checking conditions before performing an operation, ensuring invalid states never occur. Catching an error means allowing the operation to proceed and handling the exception if something goes wrong. Prevention is proactive (validation first), while catching is reactive (handle failure after).

### Q2 (80): Show one rule in your project that should be checked before the risky operation happens.
In the Patient class, before scheduling an appointment, we should validate:

```java
// In AppointmentService or Patient class
public boolean canScheduleAppointment(LocalDate date) {
    if (date.isBefore(LocalDate.now())) {
        return false; // Cannot schedule in the past
    }
    if (hasAppointmentOnDate(date)) {
        return false; // Already has appointment
    }
    return true;
}
```

This prevents invalid appointments from being created rather than catching exceptions later.

### Q3 (100): Compare validation through object methods with handling an exception afterward. Which should come first, and why?
Validation should come first because:
- It prevents invalid states from ever existing (fail fast principle)
- It's more efficient - no exception overhead
- It provides clearer error messages at the point of validation
- It maintains object invariants and encapsulation
- Exception handling should be reserved for truly exceptional circumstances (file I/O, network issues, etc.)

Example: Validating a patient ID before lookup is better than catching a NullPointerException when the patient doesn't exist.


## Set 34: Variable Scope

### Q1 (60): What is variable scope, and what is the difference between a field and a local variable?
Variable scope defines where a variable can be accessed in code. A field (instance variable) belongs to an object and exists for the object's lifetime, accessible by all methods in the class. A local variable exists only within a method or block, created when the method is called and destroyed when it exits.

### Q2 (80): Show one method in your project and identify its local variables and class fields.
In the Doctor class:

```java
public class Doctor {
    // Class fields
    private int id;
    private String name;
    private String specialization;
    
    public void displayInfo() {
        // Local variable
        String formattedInfo = String.format("Dr. %s - %s", name, specialization);
        System.out.println(formattedInfo);
    }
}
```

Fields: `id`, `name`, `specialization` (belong to the Doctor object)
Local variable: `formattedInfo` (exists only during method execution)

### Q3 (100): Explain how poor scope decisions can create confusion or bugs in object-oriented code.
Poor scope decisions cause problems:
- Making everything a field when local variables suffice increases coupling and makes state harder to track
- Using overly broad scope (public fields) breaks encapsulation and allows invalid modifications
- Shadowing (local variable with same name as field) creates confusion about which variable is being used
- Unnecessary fields increase memory usage and make objects harder to understand
- Local variables that should be fields force passing data between methods unnecessarily

Example: If temporary calculation results are stored as fields instead of local variables, the object's state becomes unclear and methods may accidentally depend on stale data.


## Set 35: Naming in Class and Method Design

### Q1 (60): Why is naming important in class and method design?
Naming is crucial because it communicates intent, responsibility, and behavior. Good names make code self-documenting, reduce the need for comments, and help developers understand what a class represents or what a method does without reading implementation details. Poor names lead to confusion and maintenance difficulties.

### Q2 (80): Show one class name and one method name from your project and explain why they are appropriate or not.
Class name: `AppointmentService`
- Appropriate: Clearly indicates it's a service layer handling appointment operations
- The "Service" suffix follows common naming conventions for business logic classes

Method name: `displayInfo()` in Doctor class
- Could be improved: "display" suggests UI responsibility, which shouldn't be in a model class
- Better name: `getFormattedInfo()` or `toString()` - separates data formatting from display logic

### Q3 (100): Explain how poor naming can confuse object responsibility, inheritance meaning, or class interaction.
Poor naming creates confusion:
- Vague names like `Manager` or `Handler` don't indicate specific responsibilities
- Misleading names like `PatientController` doing database operations suggests wrong layer responsibility
- Generic names in inheritance (e.g., `Base` or `Abstract`) don't convey the abstraction's meaning
- Method names like `process()` or `handle()` don't indicate what's being processed or how
- Inconsistent naming (e.g., `getPatient()` vs `retrieveDoctor()`) makes the API harder to learn

Example: If `DoctorSystem` is named `DoctorManager`, it's unclear if it manages doctor objects, doctor data, or doctor operations. Clear naming like `DoctorRepository` or `DoctorService` immediately communicates purpose.


## Set 36: Access Modifiers

### Q1 (60): What is the role of access modifiers like private, protected, and public in OOP?
Access modifiers control visibility and access to class members:
- `private`: Only accessible within the same class (strongest encapsulation)
- `protected`: Accessible within the same class, subclasses, and same package
- `public`: Accessible from anywhere (weakest encapsulation)
- (default/package-private): Accessible within the same package

They enforce encapsulation by hiding implementation details and controlling how objects interact.

### Q2 (80): Show one class in your project and explain why some members should not all have the same access level.
In the Patient class:

```java
public class Patient {
    private int id;              // private: internal identifier, shouldn't be changed
    private String name;         // private: controlled through setter with validation
    private LocalDate birthDate; // private: immutable after creation
    
    public String getName() {    // public: safe read access
        return name;
    }
    
    protected void updateMedicalRecord() { // protected: only for subclasses/package
        // sensitive operation
    }
}
```

Different access levels protect data integrity, expose only necessary operations, and allow controlled extension.

### Q3 (100): Explain how poor access control can make inheritance, maintenance, or debugging more difficult.
Poor access control causes problems:
- Making everything public breaks encapsulation - external code can create invalid states
- Making everything private prevents reasonable extension through inheritance
- Wrong access levels make it unclear which methods are the public API vs internal helpers
- Public fields allow direct modification, bypassing validation and breaking invariants
- Debugging becomes harder when many classes can modify internal state

Example: If Patient fields are public, any class can change the ID, breaking uniqueness constraints. If all methods are private, subclasses like VIPPatient can't extend behavior properly.


## Set 37: Hiding Implementation Details

### Q1 (60): What is the benefit of hiding implementation details behind methods?
Hiding implementation details (encapsulation) allows internal changes without affecting external code. Clients use public methods without knowing how they work internally. This reduces coupling, makes code more maintainable, and allows optimization or bug fixes without breaking dependent code.

### Q2 (80): Show one example in your project where other classes use a method without needing to know the internal logic.
In the DoctorSystem class:

```java
// In AppointmentController.java
public class AppointmentController {
    private final IAppointmentService appointmentService;

    public void run(Scanner sc) {
        // ... menu logic ...
        switch (appointmentChoice) {
            case 1:
                appointmentService.addAppointment(sc);  // Calls the method
                break;
            case 2:
                appointmentService.viewAppointments();  // Calls the method
                break;
            // ...
        }
    }
}
```

The controller doesn't know that:

addAppointment(sc) internally calls AppointmentView.getAppointmentInput(sc, patientSystem) to get user input
It validates patient IDs using isPatientIdUnique() and searchAppointmentById()
Appointments are stored in a private ArrayList<Appointment> field
It interacts with PatientSystem for patient validation
The implementation could change to use a database or different storage without affecting AppointmentController—it only depends on the interface contract. This demonstrates encapsulation: the controller uses the public method without needing to know how appointments are created, validated, or stored internally

### Q3 (100): Explain how this kind of encapsulation makes debugging and modification easier later.
Encapsulation improves maintenance:
- Changes are localized - switching from ArrayList to HashMap only requires modifying DoctorSystem
- Debugging is focused - if doctor lookup fails, you only check one method, not every place that accesses the collection
- Validation and logging can be added in one place
- Performance improvements benefit all callers automatically
- Refactoring is safer - internal changes don't ripple through the codebase

Example: If we add caching to `findDoctorById()`, all controllers benefit immediately. If the collection was public, we'd need to update every access point and couldn't add caching centrally.


## Set 38: Reusability in OOP

### Q1 (60): Why is reusability an important goal in OOP?
Reusability reduces code duplication, saves development time, and improves consistency. When classes and methods are reusable, the same logic doesn't need to be rewritten, reducing bugs and maintenance effort. Changes to reused code automatically benefit all users of that code.

### Q2 (80): Show one class or method in your project that can be reused in multiple situations.
The Validator class is highly reusable:

```java
public class Validator {
    public static boolean isValidId(int id) {
        return id > 0;
    }
    
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }
}
```

This can be used by Patient, Doctor, Appointment, and any other class needing input validation. The same validation logic is reused across the entire application.

### Q3 (100): Explain what design choices made that reuse possible.
Design choices enabling reusability:
- Static utility methods - no object instantiation needed, accessible anywhere
- Single Responsibility - each method validates one specific thing
- No dependencies - methods don't depend on specific classes or state
- Generic parameters - works with any int or String, not specific types
- Clear naming - immediately obvious what each method validates
- No side effects - pure functions that only return validation results

These choices make Validator usable in any context. If it were tied to specific classes or had dependencies, reusability would be limited.


## Set 39: When Inheritance Isn't the Best Solution

### Q1 (60): Why is inheritance not always the best solution in OOP?
Inheritance creates tight coupling between parent and child classes. Changes to the parent affect all children. It's inflexible - a class can only inherit from one parent (in Java). Inheritance should represent "is-a" relationships, but it's often misused for code reuse. Composition (has-a) is often more flexible and maintainable.

### Q2 (80): Give one example of a situation where inheritance seems possible but may not be the best design.
Consider adding "availability schedule" to doctors:

```java
// Inheritance approach (questionable)
class ScheduledDoctor extends Doctor {
    private List<TimeSlot> schedule;
}

// Composition approach (better)
class Doctor {
    private Schedule schedule; // has-a relationship
}
```

While "ScheduledDoctor is-a Doctor" seems logical, not all doctors may need scheduling, and schedule management is a separate concern.

### Q3 (100): Compare inheritance and composition for that case, and justify which one would be more maintainable.
Composition is more maintainable here:

Inheritance problems:
- Forces all doctors to have scheduling even if not needed
- Can't easily add/remove scheduling at runtime
- Tight coupling - changes to Doctor affect ScheduledDoctor
- Can't reuse Schedule for other entities (Nurses, Rooms)

Composition benefits:
- Schedule can be null if not needed
- Schedule class can be reused for Nurse, Room, etc.
- Can swap schedule implementations (DailySchedule, WeeklySchedule)
- Loose coupling - Schedule changes don't affect Doctor
- More flexible - can add multiple schedules or change scheduling strategy

Composition follows "favor composition over inheritance" principle and provides better flexibility.


## Set 40: Type Checking vs. Polymorphism

### Q1 (60): Why is using many if-else checks on object type often a sign of weak OOP design?
Type checking with if-else violates polymorphism principles. It creates rigid code that must be modified every time a new type is added. It centralizes behavior that should belong to the objects themselves. Good OOP uses polymorphism - objects know their own behavior, and the correct method is called automatically.

### Q2 (80): Show one place in a project where polymorphism could replace repeated type checking.
Instead of type checking in display logic:

```java
// Bad: Type checking
if (doctor instanceof Cardiologist) {
    System.out.println("Cardiology: " + doctor.getName());
} else if (doctor instanceof Surgeon) {
    System.out.println("Surgery: " + doctor.getName());
}

// Good: Polymorphism
doctor.displaySpecialty(); // Each subclass overrides this method
```

Each doctor type knows how to display itself without external type checking.

### Q3 (100): Explain how redesigning that part with overriding or interfaces would make the code cleaner.
Polymorphic redesign:

```java
abstract class Doctor {
    public abstract void displaySpecialty();
}

class Cardiologist extends Doctor {
    @Override
    public void displaySpecialty() {
        System.out.println("Cardiology: " + getName());
    }
}

class Surgeon extends Doctor {
    @Override
    public void displaySpecialty() {
        System.out.println("Surgery: " + getName());
    }
}
```

Benefits:
- Adding new doctor types doesn't require modifying existing code
- Each class encapsulates its own behavior
- No central switch/if-else that grows with each type
- Compiler ensures all types implement required behavior
- Follows Open/Closed Principle - open for extension, closed for modification


## Set 41: General vs. Specialized Classes

### Q1 (60): What is the difference between a general class and a specialized subclass?
A general class (parent/superclass) defines common attributes and behaviors shared by multiple types. A specialized subclass (child) inherits from the general class and adds specific attributes or behaviors unique to that specialization. The subclass "is-a" more specific version of the general class.

### Q2 (80): Show one general idea and one specialized idea from your project that fit a parent-child design.
General class: `Doctor`
- Common attributes: id, name, specialization
- Common behaviors: getId(), getName()

Specialized classes: `Cardiologist`, `Surgeon`
- Cardiologist adds: heart-specific methods or certifications
- Surgeon adds: surgery-specific methods or operating room access

The relationship: "A Cardiologist is-a Doctor" and "A Surgeon is-a Doctor"

### Q3 (100): Explain how to decide whether specialization should be modeled with inheritance or handled another way.
Decision criteria for inheritance:

Use inheritance when:
- True "is-a" relationship exists (Cardiologist IS-A Doctor)
- Subclass needs all parent functionality
- Specialization adds or refines behavior, doesn't replace it
- The hierarchy is stable and won't change frequently

Avoid inheritance when:
- Relationship is "has-a" or "uses-a" (Doctor HAS-A Schedule)
- Only code reuse is needed, not conceptual relationship
- Specialization might change at runtime
- Multiple "types" of specialization exist (use composition or strategy pattern)

Example: If doctor specialization is just a String field that changes, don't use inheritance. If specialization involves different behaviors and certifications, inheritance makes sense.


## Set 42: Object Responsibility

### Q1 (60): What does it mean for an object to be responsible for its own behavior?
An object being responsible for its own behavior means it contains both the data and the methods that operate on that data. The object encapsulates its state and provides methods to manipulate it safely. External code shouldn't directly access or modify the object's internal state - it should ask the object to perform operations.

### Q2 (80): Show one method in your project that belongs inside the class because it uses or protects that object's own data.
In the Patient class:

```java
public class Patient {
    private int id;
    private String name;
    private List<Appointment> appointments;
    
    public boolean hasAppointmentOnDate(LocalDate date) {
        for (Appointment apt : appointments) {
            if (apt.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }
}
```

This method belongs in Patient because it operates on the patient's appointment list and encapsulates the logic for checking appointment conflicts.

### Q3 (100): Explain why moving that behavior outside the class would weaken encapsulation or create duplication.
Moving behavior outside weakens design:

If `hasAppointmentOnDate()` were external:
- External code needs direct access to appointments list (breaks encapsulation)
- Logic would be duplicated wherever appointment checking is needed
- Changes to appointment storage (List to Set) require updating all external code
- Can't enforce business rules (e.g., validation) in one place
- Testing becomes harder - must test the logic in multiple places

Keeping it inside Patient:
- Appointments list stays private
- Logic is centralized and reusable
- Internal implementation can change without affecting callers
- Business rules are enforced consistently
- Single point of testing and debugging

This follows "Tell, Don't Ask" principle - tell the object what to do, don't ask for its data and do it yourself.


## Set 43: Constructors and Object Validity

### Q1 (60): What is the relationship between constructors and object validity?
Constructors establish object validity by initializing all required fields and enforcing invariants. A well-designed constructor ensures that an object is never in an invalid state - once constructed, the object is ready to use. Constructors should validate inputs and reject invalid data, preventing invalid objects from being created.

### Q2 (80): Explain one important rule that should always be true for an object in your project.
For the Patient class, an important invariant is: "A patient must always have a valid ID and non-empty name."

```java
public Patient(int id, String name) {
    if (id <= 0) {
        throw new IllegalArgumentException("Patient ID must be positive");
    }
    if (name == null || name.trim().isEmpty()) {
        throw new IllegalArgumentException("Patient name cannot be empty");
    }
    this.id = id;
    this.name = name;
}
```

This ensures no Patient object exists with invalid data.

### Q3 (100): Show how constructors and state-changing methods can help guarantee that rule remains true.
Maintaining invariants throughout object lifetime:

```java
public class Patient {
    private int id;
    private String name;
    
    // Constructor enforces initial validity
    public Patient(int id, String name) {
        if (id <= 0 || name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid patient data");
        }
        this.id = id;
        this.name = name;
    }
    
    // Setter maintains validity
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }
    
    // ID is immutable - no setter provided
}
```

This design guarantees:
- Objects are valid from creation (constructor validation)
- Objects stay valid during lifetime (setter validation)
- Critical fields can't be changed (no ID setter)
- Invalid states are impossible to create


## Set 44: "Who Should Do This Work?"

### Q1 (60): Why is it useful to think about "who should do this work" when designing OOP classes?
Asking "who should do this work?" helps assign responsibilities correctly. It ensures methods are placed in the class that owns the relevant data and logic. This improves cohesion (related things stay together), reduces coupling (classes don't depend on each other's internals), and makes code more intuitive and maintainable.

### Q2 (80): Show one feature in your project and explain which class should own that logic.
Feature: "Calculate total appointments for a patient"

Should belong to: Patient class

```java
public class Patient {
    private List<Appointment> appointments;
    
    public int getTotalAppointments() {
        return appointments.size();
    }
}
```

Patient owns the appointments data, so it should provide methods to query it. External classes shouldn't access the appointments list directly.

### Q3 (100): Explain a case where putting the logic in the wrong class would hurt readability, cohesion, or reuse.
Wrong placement example:

```java
// BAD: Logic in Controller
public class PatientController {
    public void showPatientStats(Patient patient) {
        int total = patient.getAppointments().size(); // Accessing internal list
        System.out.println("Total: " + total);
    }
}
```

Problems:
- Readability: Logic is scattered - appointment counting is in Controller, not Patient
- Cohesion: Controller now knows about Patient's internal structure
- Reuse: Every place needing appointment count must duplicate this logic
- Encapsulation: Requires exposing appointments list publicly
- Maintenance: Changing appointment storage requires updating all external code

Correct placement:
```java
// GOOD: Logic in Patient
patient.getTotalAppointments(); // Simple, reusable, encapsulated
```

This keeps related data and behavior together, making the code more maintainable and intuitive.


## Set 45: State and Behavior Connection

### Q1 (60): Why should object state and behavior stay logically connected?
Object state (data/fields) and behavior (methods) should stay connected because methods operate on the data they're designed to manage. This connection is the foundation of encapsulation. When state and behavior are separated, it leads to poor cohesion, broken encapsulation, and code that's harder to understand and maintain.

### Q2 (80): Show one class in your project where fields and methods clearly belong together.
The Appointment class demonstrates this connection:

```java
public class Appointment {
    // State
    private int id;
    private Patient patient;
    private Doctor doctor;
    private LocalDate date;
    private String status;
    
    // Behavior operating on this state
    public boolean isScheduledFor(LocalDate date) {
        return this.date.equals(date);
    }
    
    public void cancel() {
        this.status = "CANCELLED";
    }
    
    public boolean canBeRescheduled() {
        return !status.equals("CANCELLED") && date.isAfter(LocalDate.now());
    }
}
```

All methods work with the appointment's own data, maintaining logical cohesion.

### Q3 (100): Explain what design problem appears if methods change data that belongs conceptually to another class.
Problems when methods modify foreign data:

```java
// BAD: AppointmentService modifying Patient's internal state
public class AppointmentService {
    public void scheduleAppointment(Patient patient, Appointment apt) {
        patient.getAppointments().add(apt); // Directly modifying Patient's list
    }
}
```

Issues:
- Breaks encapsulation - Patient can't control its own state
- Violates Single Responsibility - AppointmentService manages Patient data
- Prevents validation - Patient can't enforce rules about its appointments
- Creates tight coupling - Service depends on Patient's internal structure
- Makes debugging harder - Patient state changes from outside
- Reduces reusability - Logic is in wrong place

Better design:
```java
// GOOD: Patient manages its own state
patient.addAppointment(apt); // Patient controls how appointments are added
```

This keeps state and behavior together, maintaining encapsulation and cohesion.


## Set 46: Designing for Extension

### Q1 (60): What is the difference between designing for current needs and designing for extension?
Designing for current needs focuses only on immediate requirements, creating code that works now but may be rigid. Designing for extension anticipates future changes and uses patterns (inheritance, interfaces, polymorphism) that allow new features to be added without modifying existing code. It balances current simplicity with future flexibility.

### Q2 (80): Show one part of your project that may need new object types or new behaviors later.
The Doctor hierarchy may need extension:

```java
// Current
abstract class Doctor {
    // Common doctor functionality
}

class Cardiologist extends Doctor { }
class Surgeon extends Doctor { }

// Future extensions might include:
// - Pediatrician
// - Neurologist
// - GeneralPractitioner
// - Specialist with multiple certifications
```

The system should allow adding new doctor types without modifying existing code.

### Q3 (100): Explain how your current design supports or fails to support that future extension.
Current design evaluation:

Supports extension:
- Abstract Doctor class provides common base
- Polymorphism allows treating all doctors uniformly
- Controllers use Doctor type, not specific subclasses
- New doctor types can be added by extending Doctor

Potential weaknesses:
- If controllers have type-checking (instanceof), adding new types requires modifying those checks
- If specialization is hardcoded in switch statements, not extensible
- If Doctor class has too many specific methods, new types may not fit

Improvement for better extension:
```java
// Use interfaces for behaviors
interface Prescribable {
    void prescribeMedication();
}

interface Surgical {
    void performSurgery();
}

class Surgeon extends Doctor implements Surgical { }
class Cardiologist extends Doctor implements Prescribable, Surgical { }
```

This allows flexible combinations of behaviors without rigid inheritance hierarchies.


## Set 47: Extension vs. Modification

### Q1 (60): What is the difference between extending a system and modifying existing code everywhere?
Extending a system means adding new functionality through new classes or methods without changing existing code. Modifying everywhere means editing multiple existing classes to add features. Extension follows the Open/Closed Principle (open for extension, closed for modification), while widespread modification is risky and time-consuming.

### Q2 (80): Show one area in your project where adding a new object type should require minimal changes.
Adding a new doctor type should require minimal changes:

```java
// To add Pediatrician, only need to create new class
class Pediatrician extends Doctor {
    @Override
    public void displaySpecialty() {
        System.out.println("Pediatrics: " + getName());
    }
}
```

If designed well, no changes needed in:
- DoctorSystem (works with Doctor type)
- Controllers (use polymorphism)
- Patient or Appointment classes

Only need to update: Factory or creation logic where new doctors are instantiated.

### Q3 (100): Explain which OOP principles help reduce the need to edit many old classes when a new feature is added.
Key principles for extensibility:

1. Polymorphism
   - Code uses base types (Doctor), not specific types (Cardiologist)
   - New types work automatically through inheritance

2. Abstraction
   - Interfaces and abstract classes define contracts
   - New implementations fulfill contracts without changing existing code

3. Encapsulation
   - Internal changes don't affect external code
   - Adding features to a class doesn't impact its users

4. Open/Closed Principle
   - Classes open for extension (inheritance, interfaces)
   - Closed for modification (don't edit existing code)

5. Dependency Inversion
   - Depend on abstractions, not concrete classes
   - New concrete classes can be added without changing dependents

Example: If DoctorSystem uses `List<Doctor>` instead of separate lists for each type, adding Pediatrician requires zero changes to DoctorSystem.


## Set 48: Testing and Clear Responsibilities

### Q1 (60): Why is testing easier when classes have clear inputs, outputs, and responsibilities?
Clear responsibilities make testing easier because each class has a focused purpose with predictable behavior. Well-defined inputs and outputs allow writing specific test cases. When a class does one thing well, tests are simple and isolated. Unclear responsibilities lead to complex tests that depend on many external factors.

### Q2 (80): Show one class or method in your project that would be easy to test and explain why.
The Validator class is easy to test:

```java
public class Validator {
    public static boolean isValidId(int id) {
        return id > 0;
    }
    
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }
}
```

Easy to test because:
- Pure functions - no side effects or state
- Clear inputs (int, String) and outputs (boolean)
- No dependencies on other classes or external resources
- Single responsibility - each method validates one thing
- Deterministic - same input always produces same output

Test example:
```java
@Test
public void testValidId() {
    assertTrue(Validator.isValidId(1));
    assertFalse(Validator.isValidId(0));
    assertFalse(Validator.isValidId(-1));
}
```

### Q3 (100): Explain how bad coupling or unclear responsibilities make OOP code harder to test and debug.
Testing problems from poor design:

Bad coupling issues:
- Must set up many dependent objects to test one class
- Changes in one class break tests for unrelated classes
- Can't test in isolation - need entire system running
- Mock objects become complex and brittle

Unclear responsibilities issues:
- Don't know what to test - class does too many things
- Tests become integration tests, not unit tests
- Hard to reproduce bugs - unclear which class is responsible
- Test failures don't pinpoint the problem

Example:
```java
// BAD: Tightly coupled, unclear responsibility
public class PatientController {
    public void processPatient(String input) {
        // Parses input, validates, creates patient, saves to database, sends email
    }
}
```

Testing this requires: database setup, email server, input parsing logic. Failure could be in any of these areas.

Better design: Separate parsing, validation, persistence, and notification into different classes, each easily testable in isolation.


## Set 49: Refactoring Signs and Improvements

### Q1 (60): What are common signs that an OOP design needs refactoring?
Common signs of poor design:
- Large classes with many responsibilities (God objects)
- Long methods that do multiple things
- Repeated code across multiple classes
- Many parameters in methods
- Type checking with instanceof or switch on type
- Public fields or getters/setters for everything
- Deep inheritance hierarchies
- Classes that change frequently for unrelated reasons
- Difficult to test or extend

### Q2 (80): Point to one part of your project that could be improved in structure, naming, access control, or class responsibility.
The view classes (PatientView, DoctorView, etc.) could be improved:

Current issue:
```java
public class PatientView {
    public void displayInfo() {
        // Display logic mixed with formatting
    }
}
```

Problems:
- View classes might have too much responsibility if they also handle input
- Naming suggests UI but might contain business logic
- Could have better separation between display and data formatting

### Q3 (100): Propose a refactoring for that part and explain why the new design would be better.
Refactoring proposal:

```java
// Separate concerns clearly
public class Patient {
    // Model - data only
    public String getFormattedInfo() {
        return String.format("ID: %d, Name: %s", id, name);
    }
}

public class PatientView {
    // View - display only, no business logic
    public void display(String info) {
        System.out.println(info);
    }
}

public class PatientController {
    private PatientView view;
    private PatientSystem system;
    
    public void showPatient(int id) {
        Patient patient = system.findById(id);
        String info = patient.getFormattedInfo();
        view.display(info);
    }
}
```

Improvements:
- Clear separation of concerns (MVC pattern)
- Patient handles its own formatting (encapsulation)
- View only displays, doesn't know about Patient structure
- Controller coordinates but doesn't contain business logic
- Each class has single responsibility
- Easy to test each component independently
- Can swap view implementation (console, GUI, web) without changing Patient or Controller

This follows SOLID principles and makes the code more maintainable and extensible.


## Set 50: Three Pillars of OOP - Summary and Evaluation

### Q1 (60): Define the three main pillars of OOP used in this course: encapsulation, inheritance, and polymorphism.

1. Encapsulation
   - Bundling data and methods that operate on that data within a class
   - Hiding internal implementation details behind public interfaces
   - Controlling access through private/protected/public modifiers
   - Protecting object state and enforcing invariants

2. Inheritance
   - Creating new classes based on existing classes
   - Child classes inherit attributes and behaviors from parent classes
   - Represents "is-a" relationships
   - Enables code reuse and hierarchical organization

3. Polymorphism
   - Objects of different types responding to the same method call
   - Method overriding - subclasses provide specific implementations
   - Treating objects through their common interface or parent type
   - Enables flexible, extensible code without type checking

### Q2 (80): Point to one concrete example of each of these three pillars in your project.

1. Encapsulation example - Patient class:
```java
public class Patient {
    private int id;              // Hidden state
    private String name;
    
    public String getName() {    // Controlled access
        return name;
    }
    
    public void setName(String name) {  // Validated modification
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }
}
```

2. Inheritance example - Doctor hierarchy:
```java
abstract class Doctor {
    protected int id;
    protected String name;
}

class Cardiologist extends Doctor {
    // Inherits id and name, adds specific behavior
}

class Surgeon extends Doctor {
    // Inherits id and name, adds specific behavior
}
```

3. Polymorphism example - Doctor display:
```java
List<Doctor> doctors = new ArrayList<>();
doctors.add(new Cardiologist(...));
doctors.add(new Surgeon(...));

for (Doctor doctor : doctors) {
    doctor.displaySpecialty();  // Each type displays differently
}
```

### Q3 (100): Evaluate your project design: what is one strong OOP decision you made, and what is one weak part you would improve next?

Strong decision:
The separation of concerns with system classes (DoctorSystem, PatientSystem, AppointmentService) is strong. These classes encapsulate data management logic, provide clear interfaces, and hide implementation details. Controllers don't directly manipulate collections, which maintains encapsulation and makes the system easier to modify (e.g., switching from ArrayList to database).

Weak part to improve:
The view classes could be better designed. Currently, they might mix display logic with formatting or input handling. I would refactor to:
- Move formatting logic into model classes (Patient.getFormattedInfo())
- Keep views purely for display (System.out.println only)
- Ensure controllers coordinate between model and view without business logic
- This would improve testability and allow easier UI changes (console to GUI)

Additionally, error handling could be more robust with custom exceptions instead of returning null, and validation could be more consistently applied through constructors and setters to maintain object invariants throughout the system lifecycle.

