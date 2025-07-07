package Business;

import Business.Person.PersonDirectory;
import Business.Profiles.EmployeeDirectory;
import Business.Profiles.FacultyDirectory;  // Add this import
import Business.Profiles.StudentDirectory;
import Business.UserAccounts.UserAccountDirectory;

public class Business {

    String name;
    PersonDirectory persondirectory;
    EmployeeDirectory employeedirectory;
    UserAccountDirectory useraccountdirectory;
    StudentDirectory studentdirectory;
    FacultyDirectory facultydirectory;  // Add this field

    public Business(String n) {
        name = n;

        persondirectory = new PersonDirectory();
        employeedirectory = new EmployeeDirectory(this);
        useraccountdirectory = new UserAccountDirectory();
        studentdirectory = new StudentDirectory();
        facultydirectory = new FacultyDirectory();  // Add this line
    }

    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return useraccountdirectory;
    }

    public EmployeeDirectory getEmployeeDirectory() {
        return employeedirectory;
    }

    public StudentDirectory getStudentDirectory() {
        return studentdirectory;
    }

    public FacultyDirectory getFacultyDirectory() {  // Add this method
        return facultydirectory;
    }
}