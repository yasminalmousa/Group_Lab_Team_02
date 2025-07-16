package Business.Profiles;

import Business.Person.Person;

public class FacultyProfile extends Profile {

    private String department;
    private String employeeId;
    private String officeLocation;
    private String phoneNumber;
    private String email;
    private String name;

    public FacultyProfile(Person p) {
        super(p);
        this.department = "";
        this.employeeId = "";
        this.officeLocation = "";
        this.phoneNumber = "";
        this.email = "";
        this.name = "";
    }

    public FacultyProfile(Person p, String department, String employeeId, 
        String officeLocation, String phoneNumber, String email) {
        super(p);
        this.department = department;
        this.employeeId = employeeId;
        this.officeLocation = officeLocation;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = "";
    }

    public FacultyProfile(String facultyId, String name, String email, String department) {
        super(new Person(facultyId)); 
        this.employeeId = facultyId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.officeLocation = "";
        this.phoneNumber = "";
    }

    @Override
    public String getRole() {
        return "Faculty";
    }

    public String getFacultyId() {
        return getEmployeeId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getters and Setters
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return getPerson().getPersonId() + " (" + department + ")";
    }
    
    @Override
    public boolean isMatch(String id) {
        if (getEmployeeId() != null && getEmployeeId().equals(id)) {
        return true;
    }
        if (getPerson().getPersonId().equals(id)) {
            return true;
    }
        return false;
    }
    }