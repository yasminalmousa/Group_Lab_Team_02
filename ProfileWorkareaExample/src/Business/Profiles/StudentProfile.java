package Business.Profiles;

import Business.Person.Person;
import java.util.Date;
import java.util.ArrayList;

public class StudentProfile extends Profile {

    private String nuid;
    private String studentId;
    private String program;
    private String major;
    private Date enrollmentDate;
    private double gpa;
    private int creditsCompleted;
    private int creditsRequired;
    private String academicLevel; // Freshman, Sophomore, Junior, Senior, Graduate
    private String advisor;
    private String email;
    private String phone;
    private String address;
    private String emergencyContact;
    private ArrayList<String> coursesEnrolled;
    private Date expectedGraduation;
    private String name; // ADD THIS FIELD since Person doesn't have name

    public StudentProfile(Person p) {
        super(p);
        this.enrollmentDate = new Date();
        this.studentId = generateStudentId();
        this.coursesEnrolled = new ArrayList<>();
        this.creditsRequired = 120;
        this.academicLevel = "Freshman";
        this.name = "";
    }
    
    public StudentProfile(Person p, String nuid) {
        this(p);
        this.nuid = nuid;
    }
    
    // ADD THIS CONSTRUCTOR for the ManageStudentsJPanel
    public StudentProfile(String studentId, String name, String email, String major, String year, double gpa) {
        super(new Person(studentId));
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.major = major;
        this.academicLevel = year;
        this.gpa = gpa;
        this.enrollmentDate = new Date();
        this.coursesEnrolled = new ArrayList<>();
        this.creditsRequired = 120;
    }
    
    private String generateStudentId() {
        return "STU" + System.currentTimeMillis() % 10000;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    // UPDATE THIS METHOD to check both student ID and person ID
    @Override
    public boolean isMatch(String id) {
        // Check both the student ID and the underlying person ID
        if (getStudentId() != null && getStudentId().equals(id)) {
            return true;
        }
        if (getPerson().getPersonId().equals(id)) {
            return true;
        }
        return false;
    }
    
    // ADD THESE METHODS for the ManageStudentsJPanel
    public String getName() { 
        return name; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }
    
    // ADD THIS METHOD - panel expects getYear() not getAcademicLevel()
    public String getYear() { 
        return academicLevel; 
    }
    
    // ADD THIS METHOD - panel expects setYear() not setAcademicLevel()
    public void setYear(String year) { 
        this.academicLevel = year; 
    }
    
    // ADD THIS METHOD - panel expects setGPA() not setGpa()
    public void setGPA(double gpa) { 
        this.gpa = gpa; 
    }
    
    // NUID getter and setter
    public String getNuid() { return nuid; }
    public void setNuid(String nuid) { this.nuid = nuid; }
    
    // Major getter and setter
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    
    // All other getters and setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }
    
    public Date getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(Date enrollmentDate) { this.enrollmentDate = enrollmentDate; }
    
    public double getGPA() { return gpa; }

    
    public int getCreditsCompleted() { return creditsCompleted; }
    public void setCreditsCompleted(int creditsCompleted) { this.creditsCompleted = creditsCompleted; }
    
    public int getCreditsRequired() { return creditsRequired; }
    public void setCreditsRequired(int creditsRequired) { this.creditsRequired = creditsRequired; }
    
    public String getAcademicLevel() { return academicLevel; }
    public void setAcademicLevel(String academicLevel) { this.academicLevel = academicLevel; }
    
    public String getAdvisor() { return advisor; }
    public void setAdvisor(String advisor) { this.advisor = advisor; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
    
    public ArrayList<String> getCoursesEnrolled() { return coursesEnrolled; }
    public void setCoursesEnrolled(ArrayList<String> coursesEnrolled) { this.coursesEnrolled = coursesEnrolled; }
    
    public Date getExpectedGraduation() { return expectedGraduation; }
    public void setExpectedGraduation(Date expectedGraduation) { this.expectedGraduation = expectedGraduation; }
    
    public void addCourse(String course) {
        coursesEnrolled.add(course);
    }
    
    public void removeCourse(String course) {
        coursesEnrolled.remove(course);
    }
}