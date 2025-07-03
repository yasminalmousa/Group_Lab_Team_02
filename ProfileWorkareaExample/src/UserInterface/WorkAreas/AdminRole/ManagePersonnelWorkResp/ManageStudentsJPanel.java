package UserInterface.WorkAreas.AdminRole.ManagePersonnelWorkResp;

import Business.Business;
import Business.Profiles.StudentProfile;
import Business.Profiles.StudentDirectory;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

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
        refreshTable();
    }

    private void initializeTable() {
        // Set up the table model
        tableModel = (DefaultTableModel) studentTable.getModel();
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

    private void populateFieldsFromSelectedRow() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1 && selectedRow < tableModel.getRowCount()) {
            Object studentId = tableModel.getValueAt(selectedRow, 0);
            Object name = tableModel.getValueAt(selectedRow, 1);
            Object email = tableModel.getValueAt(selectedRow, 2);
            Object major = tableModel.getValueAt(selectedRow, 3);
            Object year = tableModel.getValueAt(selectedRow, 4);
            Object gpa = tableModel.getValueAt(selectedRow, 5);
        
            fieldStudentID.setText(studentId != null ? studentId.toString() : "");
            fieldName.setText(name != null ? name.toString() : "");
            fieldEmail.setText(email != null ? email.toString() : "");
            fieldMajor.setText(major != null ? major.toString() : "");
            fieldGPA.setText(gpa != null ? gpa.toString() : "");
        
            if (year != null) {
                String yearStr = year.toString();
                for (int i = 0; i < yearComboBox.getItemCount(); i++) {
                    if (yearComboBox.getItemAt(i).equals(yearStr)) {
                        yearComboBox.setSelectedIndex(i);
                        break;
                    }
                }
            }
            else{
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
                        Object[] row = {
                            student.getStudentId() != null ? student.getStudentId() : "",
                            student.getName() != null ? student.getName() : "",
                            student.getEmail() != null ? student.getEmail() : "",
                            student.getMajor() != null ? student.getMajor() : "",
                            student.getYear() != null ? student.getYear() : "",
                            student.getGPA() != 0.0 ? String.format("%.2f", student.getGPA()) : "0.00"
                        };
                        tableModel.addRow(row);
                    }
                }
            }
        }
    }

    private void clearFields() {
        fieldStudentID.setText("");
        fieldName.setText("");
        fieldEmail.setText("");
        fieldMajor.setText("");
        yearComboBox.setSelectedIndex(0);
        fieldGPA.setText("");
        studentTable.clearSelection();
    }

    private boolean validateFields() {
        if (fieldStudentID.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Student ID", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (fieldName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Name", "Validation Error", JOptionPane.ERROR_MESSAGE);
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
        if (fieldGPA.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter GPA", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validate GPA is a number
        try {
            double gpa = Double.parseDouble(fieldGPA.getText().trim());
            if (gpa < 0.0 || gpa > 4.0) {
                JOptionPane.showMessageDialog(this, "GPA must be between 0.0 and 4.0", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "GPA must be a valid number", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        fieldStudentID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        fieldEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        fieldName = new javax.swing.JTextField();
        fieldMajor = new javax.swing.JTextField();
        btnAddStudent = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnUpdateStudent = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnDeleteStudent = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnClearStudent = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        yearComboBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fieldGPA = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 204));

        jLabel7.setText("Student List");

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Name", "Email", "Major", "Year", "GPA"
            }
        ));
        jScrollPane1.setViewportView(studentTable);

        jLabel5.setText("Name:");

        btnBack.setText("<<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel6.setText("Major:");

        btnAddStudent.setText("Add Student");
        btnAddStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStudentActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lao MN", 1, 24)); // NOI18N
        jLabel1.setText("Manage Students");

        btnUpdateStudent.setText("Update Student");
        btnUpdateStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateStudentActionPerformed(evt);
            }
        });

        jLabel2.setText("Student Information");

        btnDeleteStudent.setText("Delete Student");
        btnDeleteStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteStudentActionPerformed(evt);
            }
        });

        jLabel3.setText("Student ID:");

        btnClearStudent.setText("Clear Student");
        btnClearStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearStudentActionPerformed(evt);
            }
        });

        jLabel4.setText("Email:");

        yearComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Freshman", "Sophomore", "Junior", "Senior" }));

        jLabel8.setText("Year:");

        jLabel9.setText("GPA:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 826, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(fieldStudentID, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                        .addComponent(fieldEmail))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(349, 349, 349)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                    .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(18, 18, 18))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addGap(24, 24, 24)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(fieldMajor, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                        .addComponent(fieldGPA))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(btnAddStudent)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateStudent)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteStudent)
                        .addGap(18, 18, 18)
                        .addComponent(btnClearStudent))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBack)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fieldStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(fieldMajor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(fieldGPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddStudent)
                    .addComponent(btnUpdateStudent)
                    .addComponent(btnDeleteStudent)
                    .addComponent(btnClearStudent))
                .addGap(25, 25, 25)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // Remove current panel and go back to admin main panel
        CardSequencePanel.remove(this);
        ((java.awt.CardLayout) CardSequencePanel.getLayout()).previous(CardSequencePanel);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnAddStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStudentActionPerformed
        if (!validateFields()) {
            return;
        }

        try {
            String studentId = fieldStudentID.getText().trim();
            String name = fieldName.getText().trim();
            String email = fieldEmail.getText().trim();
            String major = fieldMajor.getText().trim();
            String year = yearComboBox.getSelectedItem().toString();
            double gpa = Double.parseDouble(fieldGPA.getText().trim());

            // Check if student ID already exists
            StudentDirectory studentDirectory = business.getStudentDirectory();
            if (studentDirectory.findStudentById(studentId) != null) {
                JOptionPane.showMessageDialog(this, "Student ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create new student profile
            StudentProfile newStudent = new StudentProfile(studentId, name, email, major, year, gpa);
            studentDirectory.addStudent(newStudent);

            JOptionPane.showMessageDialog(this, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            refreshTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding student: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddStudentActionPerformed

    private void btnUpdateStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateStudentActionPerformed
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
            String name = fieldName.getText().trim();
            String email = fieldEmail.getText().trim();
            String major = fieldMajor.getText().trim();
            String year = yearComboBox.getSelectedItem().toString();
            double gpa = Double.parseDouble(fieldGPA.getText().trim());

            // Find the student profile
            StudentDirectory studentDirectory = business.getStudentDirectory();
            StudentProfile student = studentDirectory.findStudentById(studentId);

            if (student == null) {
                JOptionPane.showMessageDialog(this, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update student information
            student.setName(name);
            student.setEmail(email);
            student.setMajor(major);
            student.setYear(year);
            student.setGPA(gpa);

            JOptionPane.showMessageDialog(this, "Student updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            refreshTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating student: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateStudentActionPerformed

    private void btnDeleteStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteStudentActionPerformed
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
    }//GEN-LAST:event_btnDeleteStudentActionPerformed

    private void btnClearStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearStudentActionPerformed
        clearFields();
    }//GEN-LAST:event_btnClearStudentActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddStudent;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClearStudent;
    private javax.swing.JButton btnDeleteStudent;
    private javax.swing.JButton btnUpdateStudent;
    private javax.swing.JTextField fieldEmail;
    private javax.swing.JTextField fieldGPA;
    private javax.swing.JTextField fieldMajor;
    private javax.swing.JTextField fieldName;
    private javax.swing.JTextField fieldStudentID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable studentTable;
    private javax.swing.JComboBox<String> yearComboBox;
    // End of variables declaration//GEN-END:variables
}
