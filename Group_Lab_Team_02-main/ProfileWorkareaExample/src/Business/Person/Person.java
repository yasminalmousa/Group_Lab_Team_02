package Business.Person;

/**
 *
 * @author kal bugrara
 */
public class Person {

    String id;
    String name; // ADD THIS FIELD

    public Person(String id) {
        this.id = id;
        this.name = ""; // Initialize empty
    }
    
    // ADD THIS CONSTRUCTOR
    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getPersonId() {
        return id;
    }
    
    // ADD THIS METHOD
    public String getName() {
        return name;
    }
    
    // ADD THIS METHOD
    public void setName(String name) {
        this.name = name;
    }

    public boolean isMatch(String id) {
        if (getPersonId().equals(id)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getPersonId();
    }
}