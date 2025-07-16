package Business;

import Business.Person.Person;
import Business.Person.PersonDirectory;
import Business.Profiles.EmployeeDirectory;
import Business.Profiles.EmployeeProfile;
import Business.Profiles.FacultyDirectory;
import Business.Profiles.FacultyProfile;
import Business.Profiles.StudentDirectory;
import Business.Profiles.StudentProfile;
import Business.Courses.Course;
import Business.UserAccounts.UserAccount;
import Business.UserAccounts.UserAccountDirectory;

/**
 * Configuration class with proper sample data including courses and grades
 */
class ConfigureABusiness {

    static Business initialize() {
        Business business = new Business("Information Systems");

        // Create Persons
        PersonDirectory persondirectory = business.getPersonDirectory();
        Person person001 = persondirectory.newPerson("Kal Bugrara");
        Person person002 = persondirectory.newPerson("Gina Montana");
        Person person004 = persondirectory.newPerson("Dr. Sarah Wilson"); // Faculty
        Person person005 = persondirectory.newPerson("Jim Dellon");
        Person person006 = persondirectory.newPerson("Anna Shnider");
        Person person007 = persondirectory.newPerson("Laura Brown");
        Person person008 = persondirectory.newPerson("Jack While");
        Person person009 = persondirectory.newPerson("Fidelity");
        
        // Create sample student person
        Person studentPerson = persondirectory.newPerson("Yasmin Almousa");

        // Create Admins to manage the business
        EmployeeDirectory employeedirectory = business.getEmployeeDirectory();
        EmployeeProfile employeeprofile0 = employeedirectory.newEmployeeProfile(person001);
        
        // Create Faculty profiles with complete information
        FacultyDirectory facultydirectory = business.getFacultyDirectory();
        FacultyProfile facultyprofile0 = facultydirectory.newFacultyProfile(person004, "Computer Science", "FAC001", "Room 301", "(555) 123-4567", "sarah.wilson@university.edu");
        facultyprofile0.setName("Dr. Sarah Wilson");

        // Create sample student with proper course data
        StudentDirectory studentdirectory = business.getStudentDirectory();
        StudentProfile sampleStudent = studentdirectory.newStudentProfile(studentPerson, "STU4347");
        sampleStudent.setMajor("SES"); // Software Engineering Systems
        sampleStudent.setEmail("yasmin.almousa@student.edu");
        sampleStudent.setAcademicLevel("Undergraduate");
        
        // Add courses with proper grades for realistic GPA calculation
        addSampleCoursesToStudent(sampleStudent);

        // Create User accounts that link to specific profiles
        UserAccountDirectory uadirectory = business.getUserAccountDirectory();
        UserAccount ua3 = uadirectory.newUserAccount(employeeprofile0, "admin", "****"); // admin user account
        UserAccount ua5 = uadirectory.newUserAccount(facultyprofile0, "faculty", "****"); // faculty user account
        UserAccount studentAccount = uadirectory.newUserAccount(sampleStudent, "yasminalmousa", "****"); // sample student account

        return business;
    }

    /**
     * Add sample courses with realistic grades to demonstrate proper GPA calculation
     */
    private static void addSampleCoursesToStudent(StudentProfile student) {
        // Fall 2023 courses
        Course cs101 = new Course("CS101", "Introduction to Computer Science", "Fall 2023", 3);
        cs101.setGrade("A"); // 4.0 * 3 = 12.0 quality points
        student.addCourse(cs101);
        
        Course math201 = new Course("MATH201", "Calculus I", "Fall 2023", 4);
        math201.setGrade("A-"); // 3.7 * 4 = 14.8 quality points
        student.addCourse(math201);
        
        Course eng101 = new Course("ENG101", "English Composition", "Fall 2023", 3);
        eng101.setGrade("B+"); // 3.3 * 3 = 9.9 quality points
        student.addCourse(eng101);
        
        // Spring 2024 courses
        Course cs102 = new Course("CS102", "Data Structures", "Spring 2024", 4);
        cs102.setGrade("B+"); // 3.3 * 4 = 13.2 quality points
        student.addCourse(cs102);
        
        Course phys101 = new Course("PHYS101", "Physics I", "Spring 2024", 4);
        phys101.setGrade("B"); // 3.0 * 4 = 12.0 quality points
        student.addCourse(phys101);
        
        Course hist101 = new Course("HIST101", "World History", "Spring 2024", 3);
        hist101.setGrade("A"); // 4.0 * 3 = 12.0 quality points
        student.addCourse(hist101);
        
        // Fall 2024 courses
        Course cs301 = new Course("CS301", "Database Systems", "Fall 2024", 3);
        cs301.setGrade("A"); // 4.0 * 3 = 12.0 quality points
        student.addCourse(cs301);
        
        Course math301 = new Course("MATH301", "Linear Algebra", "Fall 2024", 3);
        math301.setGrade("A-"); // 3.7 * 3 = 11.1 quality points
        student.addCourse(math301);
        
        Course psyc101 = new Course("PSYC101", "Introduction to Psychology", "Fall 2024", 3);
        psyc101.setGrade("A"); // 4.0 * 3 = 12.0 quality points
        student.addCourse(psyc101);
        
        // Spring 2025 courses (current - in progress)
        Course cs201 = new Course("CS201", "Advanced Programming", "Spring 2025", 4);
        cs201.setGrade("In Progress"); // Not counted toward GPA
        student.addCourse(cs201);
        
        Course chem101 = new Course("CHEM101", "General Chemistry", "Spring 2025", 4);
        chem101.setGrade("In Progress"); // Not counted toward GPA
        student.addCourse(chem101);
        
        // Total completed: 30 credits
        // Total quality points: 109.0
        // GPA: 109.0 / 30 = 3.63 (this matches the calculation from the images)
    }
}