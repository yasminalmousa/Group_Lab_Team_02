package Business.Person;

/**
 *
 * @author kal bugrara
 */
public class Person {

    String id;
    String name;

    public Person(String id) {
        this.id = id;
        this.name = "";
    }
    
    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getPersonId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
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