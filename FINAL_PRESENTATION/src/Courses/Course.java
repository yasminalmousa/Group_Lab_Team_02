package Business.Courses;

/**
 * Course class to represent individual courses with proper GPA calculation
 */
public class Course {
    private String courseCode;
    private String courseName;
    private String semester;
    private int credits;
    private String grade;
    private boolean completed;
    
    public Course(String courseCode, String courseName, String semester, int credits) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.semester = semester;
        this.credits = credits;
        this.grade = "In Progress";
        this.completed = false;
    }
    
    // Getters and setters
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public String getSemester() { return semester; }
    public int getCredits() { return credits; }
    public String getGrade() { return grade; }
    public boolean isCompleted() { return completed; }
    
    public void setGrade(String grade) {
        this.grade = grade;
        this.completed = !grade.equals("In Progress");
    }
    
    /**
     * Calculate quality points based on grade and credits
     * @return quality points for this course
     */
    public double getQualityPoints() {
        if (!completed || grade.equals("In Progress")) {
            return 0.0;
        }
        
        double gradePoints = getGradePoints(grade);
        return gradePoints * credits;
    }
    
    /**
     * Convert letter grade to grade points
     * @param grade letter grade
     * @return grade points (4.0 scale)
     */
    private double getGradePoints(String grade) {
        switch (grade.toUpperCase()) {
            case "A": return 4.0;
            case "A-": return 3.7;
            case "B+": return 3.3;
            case "B": return 3.0;
            case "B-": return 2.7;
            case "C+": return 2.3;
            case "C": return 2.0;
            case "C-": return 1.7;
            case "D+": return 1.3;
            case "D": return 1.0;
            case "D-": return 0.7;
            case "F": return 0.0;
            default: return 0.0;
        }
    }
    
    @Override
    public String toString() {
        return courseCode + " - " + courseName + " (" + credits + " credits)";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course course = (Course) obj;
        return courseCode.equals(course.courseCode);
    }
    
    @Override
    public int hashCode() {
        return courseCode.hashCode();
    }
}