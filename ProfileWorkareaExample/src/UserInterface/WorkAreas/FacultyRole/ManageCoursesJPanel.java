/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.WorkAreas.FacultyRole;

import Business.Business;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author PrimeAgent28th
 */
public class ManageCoursesJPanel extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private Business business;
    private JPanel CardSequencePanel;
    
    /**
     * Creates new form ManageCoursesJPanel
     */
    public ManageCoursesJPanel() {
        initComponents();
        initializeTable();
        setupEventHandlers();
        loadSampleData();
    }
    
    /**
     * Creates new form ManageCoursesJPanel with Business and CardSequencePanel
     */
    public ManageCoursesJPanel(Business b, JPanel clp) {
        business = b;
        this.CardSequencePanel = clp;
        initComponents();
        initializeTable();
        setupEventHandlers();
        loadSampleData();
    }
    
    /**
     * Initialize the table model and setup
     */
    private void initializeTable() {
        tableModel = (DefaultTableModel) tableCourse.getModel();
        tableCourse.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Set column widths
        tableCourse.getColumnModel().getColumn(0).setPreferredWidth(100); // Course Code
        tableCourse.getColumnModel().getColumn(1).setPreferredWidth(200); // Course Name
        tableCourse.getColumnModel().getColumn(2).setPreferredWidth(80);  // Credits
        tableCourse.getColumnModel().getColumn(3).setPreferredWidth(250); // Description
    }
    
    /**
     * Setup event handlers for table selection and buttons
     */
    private void setupEventHandlers() {
        // Table selection listener
        tableCourse.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    populateFieldsFromSelection();
                }
            }
        });
        
        // Add Course button
        btnCourse.addActionListener(e -> addCourse());
        
        // Edit Course button (jButton1)
        jButton1.addActionListener(e -> editCourse());
        
        // Delete Course button (jButton2)
        jButton2.addActionListener(e -> deleteCourse());
        
        // Back button
        btnBack.addActionListener(e -> goBack());
    }
    
    /**
     * Load sample data into the table
     */
    private void loadSampleData() {
        // Clear existing data
        tableModel.setRowCount(0);
        
        // Add sample courses
        addCourseToTable("CS101", "Introduction to Programming", "3", "Basic programming concepts and fundamentals");
        addCourseToTable("CS102", "Data Structures", "4", "Advanced data structures and algorithms");
        addCourseToTable("MATH201", "Calculus I", "3", "Differential and integral calculus");
        addCourseToTable("ENG101", "English Composition", "3", "Writing and communication skills");
        addCourseToTable("PHYS201", "Physics I", "4", "Mechanics and thermodynamics");
    }
    
    /**
     * Add a course to the table
     */
    private void addCourseToTable(String courseCode, String courseName, String credits, String description) {
        Object[] row = {courseCode, courseName, credits, description};
        tableModel.addRow(row);
    }
    
    /**
     * Populate form fields from selected table row
     */
    private void populateFieldsFromSelection() {
        int selectedRow = tableCourse.getSelectedRow();
        if (selectedRow >= 0) {
            fieldCourseCode.setText(tableModel.getValueAt(selectedRow, 0).toString());
            fieldCourseName.setText(tableModel.getValueAt(selectedRow, 1).toString());
            fieldCredits.setText(tableModel.getValueAt(selectedRow, 2).toString());
            courseDescription.setText(tableModel.getValueAt(selectedRow, 3).toString());
        }
    }
    
    /**
     * Add a new course
     */
    private void addCourse() {
        if (validateInput()) {
            String courseCode = fieldCourseCode.getText().trim();
            String courseName = fieldCourseName.getText().trim();
            String credits = fieldCredits.getText().trim();
            String description = courseDescription.getText().trim();
            
            // Check if course code already exists
            if (courseExists(courseCode)) {
                JOptionPane.showMessageDialog(this, 
                    "Course code already exists. Please use a different code.", 
                    "Duplicate Course Code", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Add to table
            addCourseToTable(courseCode, courseName, credits, description);
            
            // Clear form
            clearFields();
            
            // Show success message
            JOptionPane.showMessageDialog(this, 
                "Course added successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Here you would typically save to your business layer/database
            // Example: business.getCourseDirectory().addCourse(new Course(courseCode, courseName, credits, description));
        }
    }
    
    /**
     * Edit selected course
     */
    private void editCourse() {
        int selectedRow = tableCourse.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, 
                "Please select a course to edit.", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (validateInput()) {
            String courseCode = fieldCourseCode.getText().trim();
            String courseName = fieldCourseName.getText().trim();
            String credits = fieldCredits.getText().trim();
            String description = courseDescription.getText().trim();
            
            // Update table
            tableModel.setValueAt(courseCode, selectedRow, 0);
            tableModel.setValueAt(courseName, selectedRow, 1);
            tableModel.setValueAt(credits, selectedRow, 2);
            tableModel.setValueAt(description, selectedRow, 3);
            
            // Clear form
            clearFields();
            
            // Show success message
            JOptionPane.showMessageDialog(this, 
                "Course updated successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Here you would typically update in your business layer/database
            // Example: business.getCourseDirectory().updateCourse(courseCode, courseName, credits, description);
        }
    }
    
    /**
     * Delete selected course
     */
    private void deleteCourse() {
        int selectedRow = tableCourse.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, 
                "Please select a course to delete.", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String courseCode = tableModel.getValueAt(selectedRow, 0).toString();
        String courseName = tableModel.getValueAt(selectedRow, 1).toString();
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete course: " + courseCode + " - " + courseName + "?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Remove from table
            tableModel.removeRow(selectedRow);
            
            // Clear form
            clearFields();
            
            // Show success message
            JOptionPane.showMessageDialog(this, 
                "Course deleted successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Here you would typically delete from your business layer/database
            // Example: business.getCourseDirectory().deleteCourse(courseCode);
        }
    }
    
    /**
     * Validate input fields
     */
    private boolean validateInput() {
        if (fieldCourseCode.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a course code.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            fieldCourseCode.requestFocus();
            return false;
        }
        
        if (fieldCourseName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a course name.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            fieldCourseName.requestFocus();
            return false;
        }
        
        if (fieldCredits.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter the number of credits.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            fieldCredits.requestFocus();
            return false;
        }
        
        // Validate credits is a number
        try {
            int credits = Integer.parseInt(fieldCredits.getText().trim());
            if (credits <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "Credits must be a positive number.", 
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
                fieldCredits.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Credits must be a valid number.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            fieldCredits.requestFocus();
            return false;
        }
        
        if (courseDescription.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a course description.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            courseDescription.requestFocus();
            return false;
        }
        
        return true;
    }
    
    /**
     * Check if course code already exists
     */
    private boolean courseExists(String courseCode) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).toString().equalsIgnoreCase(courseCode)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Clear all input fields
     */
    private void clearFields() {
        fieldCourseCode.setText("");
        fieldCourseName.setText("");
        fieldCredits.setText("");
        courseDescription.setText("");
        tableCourse.clearSelection();
    }
    
    /**
     * Go back to Faculty Work Area
     */
    private void goBack() {
        if (CardSequencePanel != null) {
            CardSequencePanel.removeAll();

            // CHANGE THIS LINE - you'll need to pass the faculty ID:
            FacultyWorkAreaJPanel facultyWorkArea = new FacultyWorkAreaJPanel(business, CardSequencePanel, "FAC001");
            CardSequencePanel.add("FacultyWorkArea", facultyWorkArea);
            ((java.awt.CardLayout) CardSequencePanel.getLayout()).show(CardSequencePanel, "FacultyWorkArea");
            CardSequencePanel.revalidate();
            CardSequencePanel.repaint();
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fieldCourseCode = new javax.swing.JTextField();
        fieldCredits = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        courseDescription = new javax.swing.JTextField();
        fieldCourseName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCourse = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        btnCourse = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.setFont(new java.awt.Font("Lao MN", 1, 18)); // NOI18N
        jLabel1.setText("Manage Courses");

        jLabel2.setText("Course Information");

        jLabel3.setText("Course Code:");

        jLabel4.setText("Credits:");

        jLabel5.setText("Course Name:");

        jLabel6.setText("Description:");

        jLabel7.setText("Course List");

        tableCourse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course Code", "Course Name", "Credits", "Description"
            }
        ));
        jScrollPane1.setViewportView(tableCourse);

        btnBack.setText("<<< Back");

        btnCourse.setText("Add Course");

        jButton1.setText("Edit Course");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Delete Course");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnBack)
                        .addGap(191, 191, 191)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton2)
                                    .addGap(34, 34, 34))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(52, 52, 52)
                                            .addComponent(fieldCredits))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(18, 18, 18)
                                            .addComponent(fieldCourseCode, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(121, 121, 121)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel5))
                                    .addGap(37, 37, 37)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(fieldCourseName, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                        .addComponent(courseDescription))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCourse)
                                .addGap(70, 70, 70)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(213, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnBack)))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fieldCourseCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(fieldCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fieldCredits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(courseDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCourse)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(13, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCourse;
    private javax.swing.JTextField courseDescription;
    private javax.swing.JTextField fieldCourseCode;
    private javax.swing.JTextField fieldCourseName;
    private javax.swing.JTextField fieldCredits;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableCourse;
    // End of variables declaration//GEN-END:variables
}
