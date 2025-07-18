/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.WorkAreas.StudentRole;

import Business.Business;
import Business.Courses.Course;
import Business.Profiles.StudentProfile;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 *
 * @author Paul
 */
public class CourseWorkJPanel extends javax.swing.JPanel {

    private Business business;
    private StudentProfile student;
    private JPanel cardSequencePanel;
    private DefaultTableModel tableModel;

    /**
     * Creates new form CourseWorkJPanel
     */
    public CourseWorkJPanel() {
        initComponents();
    }
    
    /**
     * Creates new form CourseWorkJPanel with student data
     */
    public CourseWorkJPanel(Business business, StudentProfile student, JPanel cardSequencePanel) {
        this.business = business;
        this.student = student;
        this.cardSequencePanel = cardSequencePanel;
        initComponents();
        setupTable();
        populateStudentData();
        loadCourseData();
    }
    
    private void setupTable() {
        tableModel = (DefaultTableModel) jTable1.getModel();
        // Set column widths
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);  // Course Code
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(200); // Course Name
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);  // Credits
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100); // Semester
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);  // Current Grade
    }
    
    private void populateStudentData() {
        if (student != null) {
            jLabel3.setText(student.getName() != null ? student.getName() : "N/A");
            jLabel5.setText(student.getStudentId() != null ? student.getStudentId() : "N/A");
            jLabel7.setText(student.getMajor() != null ? student.getMajor() : "N/A");

            // Set GPA and other summary info from real data
            jLabel10.setText(String.format("%.2f", student.getGPA()));
            jLabel12.setText(student.getCreditsCompleted() + " / " + student.getCreditsRequired());
            jLabel14.setText(String.valueOf(student.getCourses().size()));
        }
    }
    
    private void loadCourseData() {
        // Clear existing data
        tableModel.setRowCount(0);

        // Load real course data from student profile (ALL courses, including completed ones)
        if (student != null && student.getCourses() != null) {
            for (Course course : student.getCourses()) {
                addCourseToTable(
                    course.getCourseCode(),
                    course.getCourseName(), 
                    String.valueOf(course.getCredits()),
                    course.getSemester(),
                    course.getGrade()
                );
            }
        }
    }
    
    private void addCourseToTable(String courseCode, String courseName, String credits, String semester, String grade) {
        Object[] row = {courseCode, courseName, credits, semester, grade};
        tableModel.addRow(row);
    }
    
    private void goBack() {
        if (cardSequencePanel != null) {
            cardSequencePanel.removeAll();
            StudentWorkAreaJPanel studentWorkArea = new StudentWorkAreaJPanel(business, student, cardSequencePanel);
            cardSequencePanel.add("StudentWorkArea", studentWorkArea);
            ((java.awt.CardLayout) cardSequencePanel.getLayout()).show(cardSequencePanel, "StudentWorkArea");
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

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 204));

        jButton1.setText("<<< Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lao MN", 1, 18)); // NOI18N
        jLabel1.setText("My Course Work");

        jLabel2.setText("Student Name:");

        jLabel3.setText("jLabel3");

        jLabel4.setText("Student ID:");

        jLabel5.setText("jLabel5");

        jLabel6.setText("Major:");

        jLabel7.setText("jLabel7");

        jLabel8.setText("My Courses");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course Code", "Course Name", "Credits", "Semester", "Current Grade"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel9.setText("Current GPA:");

        jLabel10.setText("jLabel10");

        jLabel11.setText("Credits Completed: ");

        jLabel12.setText("jLabel12");

        jLabel13.setText("Courses Enrolled:");

        jLabel14.setText("jLabel14");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel13))
                                    .addGap(36, 36, 36)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel14)))
                                .addComponent(jLabel4)
                                .addComponent(jLabel2)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(171, 171, 171)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(223, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(59, 59, 59)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addContainerGap(182, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        goBack();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
