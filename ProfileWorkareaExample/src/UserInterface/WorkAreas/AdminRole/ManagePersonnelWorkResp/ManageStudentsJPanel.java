package UserInterface.WorkAreas.AdminRole.ManagePersonnelWorkResp;

import Business.Business;
import Business.Profiles.StudentProfile;
import Business.Profiles.StudentDirectory;
import UserInterface.WorkAreas.AdminRole.AdminRoleWorkAreaJPanel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManageStudentsJPanel extends javax.swing.JPanel {

    private Business business;
    private javax.swing.JPanel CardSequencePanel;
    private DefaultTableModel tableModel;

    public ManageStudentsJPanel(Business b, JPanel clp) {
        this.business = b;
        this.CardSequencePanel = clp;
        initComponents();
        initializeTable();
        setupTableSelection();
        setupEventHandlers();
        refreshTable();
    }

    private void initializeTable() {
        // Set up the table model
        tableModel = (DefaultTableModel) studentTable.getModel();
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Set column widths to match faculty panel
        studentTable.getColumnModel().getColumn(0).setPreferredWidth(80);  // Student ID
        studentTable.getColumnModel().getColumn(1).setPreferredWidth(120); // Full Name
        studentTable.getColumnModel().getColumn(2).setPreferredWidth(180); // Email
        studentTable.getColumnModel().getColumn(3).setPreferredWidth(100); // Major
        studentTable.getColumnModel().getColumn(4).setPreferredWidth(80);  // Year Level
        studentTable.getColumnModel().getColumn(5).setPreferredWidth(60);  // GPA
        studentTable.getColumnModel().getColumn(6).setPreferredWidth(80);  // Status
        studentTable.getColumnModel().getColumn(7).setPreferredWidth(100); // Enrollment Date
    }

    private void setupTableSelection() {
        // Add mouse listener to populate fields when row is selected
        studentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    populateFieldsFromSelectedRow();
                }
            }
        });
    }
    
    private void setupEventHandlers() {
        // Add Student button (jButton1)
        jButton1.addActionListener(e -> addStudent());
        
        // Edit Student button (jButton2)
        jButton2.addActionListener(e -> editStudent());
        
        // Delete Student button (jButton3)
        jButton3.addActionListener(e -> deleteStudent());
        
        // Back button
        btnBack.addActionListener(e -> goBack());
    }

    private void populateFieldsFromSelectedRow() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1 && selectedRow < tableModel.getRowCount()) {
            Object studentId = tableModel.getValueAt(selectedRow, 0);
            Object fullName = tableModel.getValueAt(selectedRow, 1);
            Object email = tableModel.getValueAt(selectedRow, 2);
            Object major = tableModel.getValueAt(selectedRow, 3);
            Object year = tableModel.getValueAt(selectedRow, 4);
            Object status = tableModel.getValueAt(selectedRow, 6);
        
            fieldStudentID.setText(studentId != null ? studentId.toString() : "");
            fieldEmail.setText(email != null ? email.toString() : "");
            fieldMajor.setText(major != null ? major.toString() : "");
            fieldStatus.setText(status != null ? status.toString() : "");
            
            // Split full name into first and last name
            if (fullName != null) {
                String[] nameParts = fullName.toString().split(" ", 2);
                fieldFirstName.setText(nameParts[0]);
                fieldLastName.setText(nameParts.length > 1 ? nameParts[1] : "");
            } else {
                fieldFirstName.setText("");
                fieldLastName.setText("");
            }
        
            if (year != null) {
                String yearStr = year.toString();
                for (int i = 0; i < yearComboBox.getItemCount(); i++) {
                    if (yearComboBox.getItemAt(i).equals(yearStr)) {
                        yearComboBox.setSelectedIndex(i);
                        break;
                    }
                }
            } else {
                yearComboBox.setSelectedIndex(0);
            }
        }
    }

    private void refreshTable() {
        // Clear existing rows
        tableModel.setRowCount(0);
    
        // Get student list from business
        StudentDirectory studentDirectory = business.getStudentDirectory();
        if (studentDirectory != null) {
            ArrayList<StudentProfile> studentList = studentDirectory.getStudentList();
        
            // Populate table with student data
            if (studentList != null) {
                for (StudentProfile student : studentList) {
                    if (student != null) {
                        String gpaString = student.getGPA() != 0.0 ? 
                            String.format("%.2f", student.getGPA()) : "0.00";
                        String status = "Active"; // Default status
                        String enrollmentDate = "2024-01-01"; // Default enrollment date
                        
                        Object[] row = {
                            student.getStudentId() != null ? student.getStudentId() : "",
                            student.getName() != null ? student.getName() : "",
                            student.getEmail() != null ? student.getEmail() : "",
                            student.getMajor() != null ? student.getMajor() : "",
                            student.getYear() != null ? student.getYear() : "",
                            gpaString,
                            status,
                            enrollmentDate
                        };
                        tableModel.addRow(row);
                    }
                }
            }
        }
    }

    private void clearFields() {
        fieldStudentID.setText("");
        fieldFirstName.setText("");
        fieldLastName.setText("");
        fieldEmail.setText("");
        fieldMajor.setText("");
        fieldStatus.setText("");
        yearComboBox.setSelectedIndex(0);
        fieldPhone.setText("");
        studentTable.clearSelection();
    }

    private boolean validateFields() {
        if (fieldStudentID.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Student ID", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (fieldFirstName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter First Name", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (fieldLastName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Last Name", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (fieldEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Email", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (fieldMajor.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Major", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (fieldStatus.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Status", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Basic email validation
        if (!fieldEmail.getText().trim().contains("@")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void addStudent() {
        if (!validateFields()) {
            return;
        }

        try {
            String studentId = fieldStudentID.getText().trim();
            String firstName = fieldFirstName.getText().trim();
            String lastName = fieldLastName.getText().trim();
            String fullName = firstName + " " + lastName;
            String email = fieldEmail.getText().trim();
            String major = fieldMajor.getText().trim();
            String year = yearComboBox.getSelectedItem().toString();
            String status = fieldStatus.getText().trim();
            double gpa = 0.0; // Default GPA for new students

            // Check if student ID already exists
            StudentDirectory studentDirectory = business.getStudentDirectory();
            if (studentDirectory.findStudentById(studentId) != null) {
                JOptionPane.showMessageDialog(this, "Student ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create new student profile
            StudentProfile newStudent = new StudentProfile(studentId, fullName, email, major, year, gpa);
            studentDirectory.addStudent(newStudent);

            JOptionPane.showMessageDialog(this, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            refreshTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding student: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void editStudent() {
        if (!validateFields()) {
            return;
        }

        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to update", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String studentId = fieldStudentID.getText().trim();
            String firstName = fieldFirstName.getText().trim();
            String lastName = fieldLastName.getText().trim();
            String fullName = firstName + " " + lastName;
            String email = fieldEmail.getText().trim();
            String major = fieldMajor.getText().trim();
            String year = yearComboBox.getSelectedItem().toString();
            String status = fieldStatus.getText().trim();

            // Find the student profile
            StudentDirectory studentDirectory = business.getStudentDirectory();
            StudentProfile student = studentDirectory.findStudentById(studentId);

            if (student == null) {
                JOptionPane.showMessageDialog(this, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update student information
            student.setName(fullName);
            student.setEmail(email);
            student.setMajor(major);
            student.setYear(year);

            JOptionPane.showMessageDialog(this, "Student updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            refreshTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating student: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String studentId = tableModel.getValueAt(selectedRow, 0).toString();
            String name = tableModel.getValueAt(selectedRow, 1).toString();

            // Confirm deletion
            int result = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete student: " + name + "?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                StudentDirectory studentDirectory = business.getStudentDirectory();
                StudentProfile student = studentDirectory.findStudentById(studentId);

                if (student != null) {
                    studentDirectory.removeStudent(student);
                    JOptionPane.showMessageDialog(this, "Student deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error deleting student: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void goBack() {
        if (CardSequencePanel != null) {
            CardSequencePanel.removeAll();
            AdminRoleWorkAreaJPanel adminWorkArea = new AdminRoleWorkAreaJPanel(business, CardSequencePanel);
            CardSequencePanel.add("AdminWorkArea", adminWorkArea);
            ((java.awt.CardLayout) CardSequencePanel.getLayout()).show(CardSequencePanel, "AdminWorkArea");
            CardSequencePanel.revalidate();
            CardSequencePanel.repaint();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fieldStudentID = new javax.swing.JTextField();
        fieldEmail = new javax.swing.JTextField();
        yearComboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        fieldFirstName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        fieldLastName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        fieldPhone = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        fieldMajor = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        fieldStatus = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        btnBack.setText("<<< Back");

        jLabel1.setFont(new java.awt.Font("Lao MN", 1, 18)); // NOI18N
        jLabel1.setText("Manage Student Profiles");

        jLabel2.setText("Student Information");

        jLabel3.setText("Student ID:");

        jLabel4.setText("Email:");

        jLabel5.setText("Year Level:");

        yearComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Freshman", "Sophomore", "Junior", "Senior" }));

        jLabel6.setText("First Name:");

        jLabel7.setText("Last Name:");

        jLabel8.setText("Phone:");

        jLabel9.setText("Major:");

        jLabel10.setText("Status:");

        jLabel11.setText("Student Profile List");

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Full Name", "Email", "Major", "Year Level", "GPA", "Status", "Enrollment Date"
            }
        ));
        jScrollPane1.setViewportView(studentTable);

        jButton1.setText("Add Student");

        jButton2.setText("Edit Student ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete Student");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(btnBack)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fieldStudentID)
                                    .addComponent(fieldEmail)
                                    .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fieldFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(fieldLastName)
                                    .addComponent(fieldPhone))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fieldMajor, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                    .addComponent(fieldStatus))))))
                .addContainerGap(72, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(140, 140, 140)
                .addComponent(jButton3)
                .addGap(117, 117, 117))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnBack)
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fieldStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(fieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(fieldMajor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(fieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(fieldStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(fieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(180, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 887, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 759, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JTextField fieldEmail;
    private javax.swing.JTextField fieldFirstName;
    private javax.swing.JTextField fieldLastName;
    private javax.swing.JTextField fieldMajor;
    private javax.swing.JTextField fieldPhone;
    private javax.swing.JTextField fieldStatus;
    private javax.swing.JTextField fieldStudentID;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable studentTable;
    private javax.swing.JComboBox<String> yearComboBox;
    // End of variables declaration//GEN-END:variables
}
