/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Profiles;

import Business.Person.Person;
import java.util.Date;

/**
 *
 * @author student
 */
public class EmployeeProfile extends Profile {

    private String employeeId;
    private String department;
    private String position;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private double salary;

    public EmployeeProfile(Person p) {
        super(p);
        this.employeeId = "";
        this.department = "";
        this.position = "";
        this.email = "";
        this.phoneNumber = "";
        this.hireDate = new Date();
        this.salary = 0.0;
    }

    public EmployeeProfile(Person p, String employeeId, String department, 
                          String position, String email, String phoneNumber, double salary) {
        super(p);
        this.employeeId = employeeId;
        this.department = department;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = new Date();
        this.salary = salary;
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    // Getters and Setters
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return getPerson().getPersonId() + " (" + position + ")";
    }
}