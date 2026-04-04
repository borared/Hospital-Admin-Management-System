# Oral Exam Preparation - Sets 12-16

## Set 12

### Q1 (60): What is polymorphism in your own words?

Polymorphism means "many forms" - it's when the same method call can behave differently depending on the actual object type at runtime. In our project, when we call `can(String action)` on a User reference, it executes Manager's version (returns true for everything) or Receptionist's version (checks specific permissions) based on what the object actually is.

### Q2 (80): Show one example from your project where one reference can work with different object types.

```java
// From App.java - Step 1: Create array with different object types
User[] users = {
    new Manager("admin", "admin$$"),           // Manager object
    new Receptionist("receptionist", "rec123") // Receptionist object
};

// Step 2: One reference type (User) can hold different objects
User currentUser = users[0];  // Reference type: User
                              // Actual object: Could be Manager OR Receptionist

// Step 3: Same method call, different behavior
if (currentUser.can("manage doctors")) {
    // If currentUser is Manager → Manager.can() runs → returns true
    // If currentUser is Receptionist → Receptionist.can() runs → returns false
}
```

**The key point:** The variable `currentUser` has type `User`, but it can hold a Manager object or a Receptionist object. When we call `can()`, Java automatically calls the correct version based on what the object actually is, not what the reference type is.

**Real-world analogy:** You have a parking spot labeled "Vehicle". You can park a Car or a Motorcycle there. When you say "start the vehicle", a Car starts differently than a Motorcycle - same command, different behavior.

### Q3 (100): Explain how polymorphism makes your design easier to extend when new child classes are added.

If we add a new user type like `Supervisor` that extends User, we don't need to change any existing code that works with User references. The new class just implements its own `can()` method, and all the existing arrays, parameters, and method calls automatically work with it. No need to modify MainController or add new conditional logic - the polymorphic behavior handles it automatically.

---

## Set 13

### Q1 (60): What is the difference between a variable's reference type and the real object type it points to at runtime?

The reference type is what you declare (like `User currentUser`), which determines what methods you can call at compile time. The real object type is what you actually create with `new` (like `new Manager()`), which determines which implementation runs at runtime. Reference type = compile-time contract, object type = runtime behavior.

### Q2 (80): Show one example in your project where the reference type is more general than the created object.

```java
// From App.java
User currentUser = new Manager("admin", "admin$$");
```

Reference type is `User` (abstract parent class), but the actual object is `Manager` (concrete child class). We can only call methods defined in User through this reference, but when we call `can()`, it executes Manager's implementation.

### Q3 (100): Explain how Java decides which overridden method to call at runtime.

Java uses dynamic dispatch (late binding). At runtime, the JVM looks at the actual object type (not the reference type) and walks up the inheritance chain to find the most specific implementation of the method. For example, if we call `can()` on a User reference pointing to a Manager object, Java checks Manager first, finds the `@Override` implementation, and calls that - not the abstract version in User.

---

## Set 14

### Q1 (60): What is an interface, and why is it often described as a contract?

An interface is a pure contract that defines what methods a class must implement, without providing any implementation itself. It's called a contract because any class that implements it promises to provide those methods - like signing an agreement to deliver specific functionality.

### Q2 (80): Show one interface from your project and explain the behavior it promises.

```java
public interface IStaff {
    public abstract boolean can(String action);
}
```

IStaff promises that any implementing class will provide a `can()` method that checks if the staff member has permission to perform a given action. Manager implements it to return true for everything, Receptionist implements it to check specific permissions. The interface doesn't care how they decide - just that they can answer the question.

### Q3 (100): Explain why using an interface is better than depending directly on one concrete class in that situation.

Using IStaff instead of depending on Manager directly means:
1. We can add new staff types (Supervisor, Technician) without changing code that checks permissions
2. Different classes can implement IStaff in completely different ways (not just User subclasses)
3. Code that needs permission checking doesn't care about authentication, salary, or other User details - it only depends on the `can()` contract
4. Makes testing easier - we can create mock IStaff implementations without needing full User objects

---

## Set 15

### Q1 (60): How are interface and polymorphism connected?

Interfaces enable polymorphism by defining a common contract that multiple classes can implement differently. When you have a reference of an interface type, it can point to any object that implements that interface, and method calls will execute the appropriate implementation - that's polymorphism in action.

### Q2 (80): Show how one interface in your project could allow multiple implementations.

```java
public interface IStaff {
    boolean can(String action);
}

// Multiple implementations:
class Manager extends User implements IStaff {
    public boolean can(String action) { return true; }
}

class Receptionist extends User implements IStaff {
    public boolean can(String action) { 
        return action.contains("patient") || action.contains("appointment");
    }
}

// Could add more:
class Technician implements IStaff {
    public boolean can(String action) {
        return action.contains("equipment");
    }
}
```

One interface, multiple different permission strategies.

### Q3 (100): Explain how this design helps future extension without changing too much old code.

When we add a new staff type, we just create a new class that implements IStaff with its own permission logic. All existing code that uses `IStaff` references or checks `can()` continues working without modification. We're extending behavior by adding new classes, not by modifying existing ones (Open/Closed Principle). The interface acts as a stable contract that isolates changes.

---

## Set 16

### Q1 (60): What is an abstract class, and why can it not be used to create direct objects?

An abstract class is a partial implementation that serves as a template for subclasses. You can't instantiate it directly because it's incomplete - it typically has abstract methods without implementations, or represents a concept too general to exist on its own (like "User" - you need to be a specific type of user).

### Q2 (80): Show one place in your project where an abstract class would make sense.

```java
public abstract class Staff {
    // Common fields and concrete methods
    protected String id;
    protected String name;
    // ... other fields
    
    public void checkIn() { /* implementation */ }
    
    // Abstract methods - each staff type implements differently
    public abstract String getResponsibilities();
    public abstract String getDepartment();
    public abstract void performDuty();
}
```

Staff is abstract because there's no such thing as a generic "staff member" - you're always a Doctor, Nurse, Cardiologist, etc. The abstract methods force each subclass to define their specific responsibilities and duties.

### Q3 (100): Explain why making that class abstract is better than making it a normal concrete class.

Making Staff abstract:
1. **Prevents invalid objects** - Can't create a generic Staff with no department or responsibilities
2. **Enforces implementation** - Compiler guarantees every staff type defines their duties and department
3. **Expresses intent** - Clearly communicates that Staff is a template, not a real entity
4. **Provides shared code** - Unlike interfaces, abstract classes can have concrete methods (checkIn, checkOut) that all subclasses inherit

If Staff were concrete, someone could create a Staff object with undefined responsibilities, which doesn't make sense in the hospital domain.
