package Business.Profiles;

import Business.Person.Person;
import Business.Courses.Course;
import java.util.ArrayList;

/**
 * StudentDirectory class to manage student profiles
 */
public class StudentDirectory {
    private ArrayList<StudentProfile> studentlist;

    public StudentDirectory() {
        studentlist = new ArrayList<>();
    }

    public StudentProfile newStudentProfile(Person person, String nuid) {
        StudentProfile sp = new StudentProfile(person, nuid);
        studentlist.add(sp);
        return sp;
    }

    public StudentProfile newStudentProfile(Person person) {
        // Generate a unique NUID if none provided
        String nuid = generateNUID();
        return newStudentProfile(person, nuid);
    }

    /**
     * Generate a unique NUID
     */
    private String generateNUID() {
        // Generate a 9-digit NUID
        long timestamp = System.currentTimeMillis();
        String nuid = String.format("%09d", timestamp % 1000000000L);
        
        // Ensure uniqueness
        while (findStudentByNUID(nuid) != null) {
            timestamp++;
            nuid = String.format("%09d", timestamp % 1000000000L);
        }
        
        return nuid;
    }

    /**
     * Find student by NUID
     */
    public StudentProfile findStudentByNUID(String nuid) {
        for (StudentProfile sp : studentlist) {
            if (sp.getNuid().equals(nuid)) {
                return sp;
            }
        }
        return null;
    }

    /**
     * Find student by ID (same as NUID for now)
     */
    public StudentProfile findStudentById(String id) {
        return findStudentByNUID(id);
    }

    /**
     * Find student by person ID
     */
    public StudentProfile findStudentByPersonId(String personId) {
        for (StudentProfile sp : studentlist) {
            if (sp.getPerson().getPersonId().equals(personId)) {
                return sp;
            }
        }
        return null;
    }

    /**
     * Get all student profiles
     */
    public ArrayList<StudentProfile> getStudentList() {
        return studentlist;
    }

    /**
     * Initialize sample student data with proper courses and grades
     */
    public void initializeSampleData() {
        // This method can be called to populate sample data
        // Only add if no students exist
        if (studentlist.isEmpty()) {
            // Sample data can be added here if needed
        }
    }

    /**
     * Add a course to a student's record
     */
    public void addCourseToStudent(String studentId, Course course) {
        StudentProfile student = findStudentById(studentId);
        if (student != null) {
            student.addCourse(course);
        }
    }

    /**
     * Update a student's grade for a specific course
     */
    public void updateStudentGrade(String studentId, String courseCode, String newGrade) {
        StudentProfile student = findStudentById(studentId);
        if (student != null) {
            student.updateCourseGrade(courseCode, newGrade);
        }
    }

    /**
     * Get student count
     */
    public int getStudentCount() {
        return studentlist.size();
    }

    /**
     * Remove a student profile
     */
    public boolean removeStudentProfile(StudentProfile student) {
        return studentlist.remove(student);
    }

    /**
     * Get students by major
     */
    public ArrayList<StudentProfile> getStudentsByMajor(String major) {
        ArrayList<StudentProfile> result = new ArrayList<>();
        for (StudentProfile student : studentlist) {
            if (student.getMajor().equalsIgnoreCase(major)) {
                result.add(student);
            }
        }
        return result;
    }

    /**
     * Get students with GPA above threshold
     */
    public ArrayList<StudentProfile> getStudentsWithHighGPA(double threshold) {
        ArrayList<StudentProfile> result = new ArrayList<>();
        for (StudentProfile student : studentlist) {
            if (student.getGPA() >= threshold) {
                result.add(student);
            }
        }
        return result;
    }
}