package Business.Profiles;

import Business.Person.Person;
import Business.Courses.Course;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentProfile class with proper GPA calculation and course management
 */
public class StudentProfile extends Profile {
    private String nuid;
    private String major;
    private String email;
    private String year;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    private String academicLevel;
    private List<Course> courses;
    private List<String> coursesEnrolled; // Legacy support
    
    public StudentProfile(Person person, String nuid) {
        super(person);
        this.nuid = nuid;
        this.courses = new ArrayList<>();
        this.coursesEnrolled = new ArrayList<>();
        this.major = "";
        this.email = "";
        this.academicLevel = "Undergraduate";
    }
    
    // Getters and setters
    public String getNuid() { return nuid; }
    public String getMajor() { return major; }
    public String getEmail() { return email; }
    public String getAcademicLevel() { return academicLevel; }
    public List<Course> getCourses() { return courses; }
    public List<String> getCoursesEnrolled() { return coursesEnrolled; }
    
    public void setMajor(String major) { this.major = major; }
    public void setEmail(String email) { this.email = email; }
    public void setAcademicLevel(String academicLevel) { this.academicLevel = academicLevel; }
    
    /**
     * Add a new course to the student's course list
     */
    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            // Also add to legacy coursesEnrolled for backward compatibility
            if (!coursesEnrolled.contains(course.getCourseCode())) {
                coursesEnrolled.add(course.getCourseCode());
            }
        }
    }
    
    /**
     * Legacy method - add course by code only
     */
    public void addCourse(String courseCode) {
        if (!coursesEnrolled.contains(courseCode)) {
            coursesEnrolled.add(courseCode);
            // Create a basic course object
            Course course = createCourseFromCode(courseCode);
            if (!courses.contains(course)) {
                courses.add(course);
            }
        }
    }
    
    /**
     * Create a course object from just the course code (for legacy support)
     */
    private Course createCourseFromCode(String courseCode) {
        String courseName = getCourseNameFromCode(courseCode);
        int credits = getCourseCreditsFromCode(courseCode);
        return new Course(courseCode, courseName, "Current", credits);
    }
    
    /**
     * Helper method to get course name from code
     */
    private String getCourseNameFromCode(String courseCode) {
        switch (courseCode.toUpperCase()) {
            case "CS101": return "Introduction to Computer Science";
            case "CS102": return "Data Structures";
            case "CS201": return "Advanced Programming";
            case "CS250": return "Computer Systems";
            case "CS301": return "Database Systems";
            case "MATH201": return "Calculus I";
            case "MATH301": return "Linear Algebra";
            case "ENG101": return "English Composition";
            case "ENG102": return "Advanced Writing";
            case "PHYS101": return "Physics I";
            case "HIST101": return "World History";
            case "HIST201": return "World History II";
            case "CHEM101": return "General Chemistry";
            case "PSYC101": return "Introduction to Psychology";
            case "ECON101": return "Principles of Economics";
            default: return "Course Name";
        }
    }
    
    /**
     * Helper method to get course credits from code
     */
    private int getCourseCreditsFromCode(String courseCode) {
        if (courseCode.startsWith("MATH") || courseCode.startsWith("PHYS") || 
            courseCode.startsWith("CHEM") || courseCode.startsWith("CS")) {
            return 4;
        }
        return 3;
    }
    
    /**
     * Update the grade for a specific course
     */
    public void updateCourseGrade(String courseCode, String newGrade) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                course.setGrade(newGrade);
                break;
            }
        }
    }
    
    /**
     * Calculate the current GPA based on completed courses
     */
    public double getGPA() {
        double totalQualityPoints = 0.0;
        int totalCredits = 0;
        
        for (Course course : courses) {
            if (course.isCompleted()) {
                totalQualityPoints += course.getQualityPoints();
                totalCredits += course.getCredits();
            }
        }
        
        return totalCredits > 0 ? totalQualityPoints / totalCredits : 0.0;
    }
    
    /**
     * Get total credits completed
     */
    public int getCreditsCompleted() {
        int totalCredits = 0;
        for (Course course : courses) {
            if (course.isCompleted()) {
                totalCredits += course.getCredits();
            }
        }
        return totalCredits;
    }
    
    /**
     * Get credits required for graduation (standard 120)
     */
    public int getCreditsRequired() {
        return 120;
    }
    
    /**
     * Get student ID (same as NUID for now)
     */
    public String getStudentId() {
        return nuid;
    }
    
    /**
     * Get student name from the associated Person
     */
    public String getName() {
        return getPerson().getName();
    }
    
    @Override
    public String getRole() {
        return "Student";
    }
    
    @Override
    public String toString() {
        return nuid + " - " + getName() + " (" + major + ")";
    }
}