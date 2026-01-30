package adminmangementsystem.com;

public abstract class Person {
    protected String id;
    protected String name;
    protected String dob;
    protected String address;

    public Person(String id, String name, String dob, String address) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public abstract void display();
}
