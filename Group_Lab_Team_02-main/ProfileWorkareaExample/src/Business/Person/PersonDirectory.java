package Business.Person;

import java.util.ArrayList;

public class PersonDirectory {
    
    ArrayList<Person> personlist;

    public PersonDirectory() {
        personlist = new ArrayList();
    }

    public Person newPerson(String id) {
        Person p = new Person(id);
        personlist.add(p);
        return p;
    }
    
    // ADD THIS METHOD - creates person with name
    public Person newPersonWithName(String name) {
        String id = generatePersonId();
        Person p = new Person(id, name);
        personlist.add(p);
        return p;
    }
    
    // ADD THIS METHOD - generates unique ID
    private String generatePersonId() {
        return "PER" + System.currentTimeMillis() % 10000;
    }

    public Person findPerson(String id) {
        for (Person p : personlist) {
            if (p.isMatch(id)) {
                return p;
            }
        }
        return null;
    }
    
    public ArrayList<Person> getPersonList() {
        return personlist;
    }
}