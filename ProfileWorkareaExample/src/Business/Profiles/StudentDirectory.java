package Business.Profiles;

import Business.Person.Person;
import java.util.ArrayList;

public class StudentDirectory {

    ArrayList<StudentProfile> studentlist;

    public StudentDirectory() {
        studentlist = new ArrayList();
    }

    public StudentProfile newStudentProfile(Person p) {
        StudentProfile sp = new StudentProfile(p);
        studentlist.add(sp);
        return sp;
    }
    
    public StudentProfile newStudentProfile(Person p, String nuid) {
        StudentProfile sp = new StudentProfile(p, nuid);
        studentlist.add(sp);
        return sp;
    }

    public StudentProfile findStudent(String id) {
        for (StudentProfile sp : studentlist) {
            if (sp.isMatch(id)) {
                return sp;
            }
        }
        return null;
    }
    
    public StudentProfile findStudentById(String id) {
        for (StudentProfile sp : studentlist) {
            if (sp.getStudentId() != null && sp.getStudentId().equals(id)) {
                return sp;
            }
        }
        return null;
    }
    
    // Add method to find by NUID
    public StudentProfile findStudentByNuid(String nuid) {
        for (StudentProfile sp : studentlist) {
            if (sp.getNuid() != null && sp.getNuid().equals(nuid)) {
                return sp;
            }
        }
        return null;
    }

    public void addStudent(StudentProfile student) {
        studentlist.add(student);
    }

    public void removeStudent(StudentProfile student) {
        studentlist.remove(student);
    }
    
    public ArrayList<StudentProfile> getStudentList() {
        return studentlist;
    }
}