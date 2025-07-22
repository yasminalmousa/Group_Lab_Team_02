/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.WorkAreas.AdminRole;

import Business.Business;
import Business.Profiles.StudentProfile;
import Business.Profiles.StudentDirectory;
import Business.Courses.Course;
import UserInterface.WorkAreas.AdminRole.AdminRoleWorkAreaJPanel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 *
 * @author yasminalmousa
 */
public class GradeManagementJPanel extends javax.swing.JPanel {

    private Business business;
    private JPanel cardSequencePanel;
    private DefaultTableModel tableModel;
    private StudentProfile selectedStudent;

    /**
     * Creates new form GradeManagementJPanel
     */
    public GradeManagementJPanel() {
        initComponents();
    }
    
    /**
     * Creates new form GradeManagementJPanel with business logic
     */
    public GradeManagementJPanel(Business business, JPanel cardSequencePanel) {
        this.business = business;
        this.cardSequencePanel = cardSequencePanel;
        initComponents();
        setupTable();
        setupEventHandlers();
        loadStudents();
    }
    
    private void setupTable() {
        tableModel = (DefaultTableModel) jTable1.getModel();
        // Set column widths
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);  // Course Code
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(200); // Course Name
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100); // Semester
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(60);  // Credits
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);  // Grade
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(80);  // Quality Points
    }
    
    private void setupEventHandlers() {
        // Back button
        btnBack.addActionListener(e -> goBack());
        
        // Student selection
        jComboBox1.addActionListener(e -> onStudentSelected());
        
        // Update grade button
        jButton1.addActionListener(e -> updateSelectedGrade());
        
        // Refresh button
        jButton2.addActionListener(e -> refreshData());
    }
    
    private void loadStudents() {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Select Student...");
        
        StudentDirectory studentDirectory = business.getStudentDirectory();
        if (studentDirectory != null) {
            for (StudentProfile student : studentDirectory.getStudentList()) {
                if (student != null) {
                    String displayText = student.getStudentId() + " - " + student.getName();
                    jComboBox1.addItem(displayText);
                }
            }
        }
    }
    
    private void onStudentSelected() {
        int selectedIndex = jComboBox1.getSelectedIndex();
        if (selectedIndex > 0) {
            String selectedItem = jComboBox1.getSelectedItem().toString();
            String studentId = selectedItem.split(" - ")[0];
            
            StudentDirectory studentDirectory = business.getStudentDirectory();
            selectedStudent = studentDirectory.findStudentById(studentId);
            loadStudentGrades();
        } else {
            selectedStudent = null;
            clearStudentInfo();
        }
    }
    
    private void loadStudentGrades() {
        tableModel.setRowCount(0);
        
        if (selectedStudent != null) {
            // Update student info display
            jLabel4.setText(selectedStudent.getName());
            jLabel6.setText(String.format("%.2f", selectedStudent.getGPA()));
            
            // Load courses
            for (Course course : selectedStudent.getCourses()) {
                Object[] row = {
                    course.getCourseCode(),
                    course.getCourseName(),
                    course.getSemester(),
                    course.getCredits(),
                    course.getGrade(),
                    String.format("%.1f", course.getQualityPoints())
                };
                tableModel.addRow(row);
            }
        }
    }
    
    private void clearStudentInfo() {
        jLabel4.setText("No student selected");
        jLabel6.setText("0.00");
        tableModel.setRowCount(0);
    }
    
    private void updateSelectedGrade() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a course from the table", 
                "No Course Selected", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (selectedStudent == null) {
            JOptionPane.showMessageDialog(this, 
                "Please select a student first", 
                "No Student Selected", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String courseCode = tableModel.getValueAt(selectedRow, 0).toString();
        String courseName = tableModel.getValueAt(selectedRow, 1).toString();
        String currentGrade = tableModel.getValueAt(selectedRow, 4).toString();
        
        String[] gradeOptions = {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F", "In Progress"};
        
        String newGrade = (String) JOptionPane.showInputDialog(
            this,
            "Select new grade for " + courseCode + " - " + courseName + ":",
            "Update Grade",
            JOptionPane.QUESTION_MESSAGE,
            null,
            gradeOptions,
            currentGrade
        );
        
        if (newGrade != null && !newGrade.equals(currentGrade)) {
            selectedStudent.updateCourseGrade(courseCode, newGrade);
            loadStudentGrades(); // Refresh display
            JOptionPane.showMessageDialog(this, 
                "Grade updated successfully!\nNew GPA: " + String.format("%.2f", selectedStudent.getGPA()), 
                "Grade Updated", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void refreshData() {
        loadStudents();
        if (selectedStudent != null) {
            loadStudentGrades();
        }
    }
    
    private void goBack() {
        if (cardSequencePanel != null) {
            cardSequencePanel.removeAll();
            AdminRoleWorkAreaJPanel adminWorkArea = new AdminRoleWorkAreaJPanel(business, cardSequencePanel);
            cardSequencePanel.add("AdminWorkArea", adminWorkArea);
            ((java.awt.CardLayout) cardSequencePanel.getLayout()).show(cardSequencePanel, "AdminWorkArea");
            cardSequencePanel.revalidate();
            cardSequencePanel.repaint();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 204, 204));

        btnBack.setText("<<< Back");

        jLabel1.setFont(new java.awt.Font("Lao MN", 1, 18)); // NOI18N
        jLabel1.setText("Grade Management");

        jLabel2.setText("Select Student:");

        jLabel3.setText("Student Name:");

        jLabel4.setText("jLabel4");

        jLabel5.setText("Current GPA:");

        jLabel6.setText("jLabel6");

        jLabel7.setText("Course Grades:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course Code", "Course Name", "Semester", "Credits", "Current Grade", "Quality Points"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Update Selected Grade");

        jButton2.setText("Refresh Data");

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 3, 14)); // NOI18N
        jLabel8.setText("Note: Select a course row and click \"Update Selected Grade\" to modify grades. GPA will be automatically recalculated when grades are updated.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel4)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(151, 151, 151)
                                .addComponent(jButton2))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(btnBack)))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(30, 30, 30)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(36, 36, 36)
                .addComponent(jLabel8)
                .addContainerGap(157, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
