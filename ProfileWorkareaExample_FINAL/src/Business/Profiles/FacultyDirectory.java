package Business.Profiles;

import Business.Person.Person;
import java.util.ArrayList;

public class FacultyDirectory {

    ArrayList<FacultyProfile> facultylist;

    public FacultyDirectory() {
        facultylist = new ArrayList<>();
    }

    public FacultyProfile newFacultyProfile(Person p) {
        FacultyProfile fp = new FacultyProfile(p);
        facultylist.add(fp);
        return fp;
    }

    public FacultyProfile newFacultyProfile(Person p, String department, String employeeId, 
                                          String officeLocation, String phoneNumber, String email) {
        FacultyProfile fp = new FacultyProfile(p, department, employeeId, officeLocation, phoneNumber, email);
        facultylist.add(fp);
        return fp;
    }

    public FacultyProfile findFacultyById(String id) {
        for (FacultyProfile fp : facultylist) {
            if (fp.isMatch(id)) {
                return fp;
            }
        }
        return null;
    }

    public FacultyProfile findFaculty(String id) {
        return findFacultyById(id);
    }

    public ArrayList<FacultyProfile> getFacultyList() {
        return facultylist;
    }

    public void addFaculty(FacultyProfile faculty) {
        facultylist.add(faculty);
    }

    public void removeFaculty(FacultyProfile faculty) {
        facultylist.remove(faculty);
    }

    public void deleteFaculty(FacultyProfile faculty) {
        facultylist.remove(faculty);
    }

    public int getFacultyCount() {
        return facultylist.size();
    }
}