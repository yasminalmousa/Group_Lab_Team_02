/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.WorkAreas.FacultyRole;

import Business.Business;
import Business.Profiles.StudentProfile;
import Business.Profiles.StudentDirectory;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author PrimeAgent28th
 */
public class ManageStudentProfilesJPanel extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private Business business;
    private JPanel CardSequencePanel;
    private String currentFacultyId;
    
    /**
     * Creates new form ManageStudentProfilesJPanel
     */
    public ManageStudentProfilesJPanel() {
        initComponents();
        initializeTable();
        setupEventHandlers();
        hideEditButtons(); // Faculty can't edit
    }
    
    /**
     * Creates new form ManageStudentProfilesJPanel with Business and CardSequencePanel
     */
    public ManageStudentProfilesJPanel(Business b, JPanel clp) {
        business = b;
        this.CardSequencePanel = clp;
        initComponents();
        initializeTable();
        setupEventHandlers();
        hideEditButtons(); // Faculty can't edit
        loadRealStudentData();
    }
    
    /**
     * Creates new form ManageStudentProfilesJPanel with Business, CardSequencePanel, and Faculty ID
     */
    public ManageStudentProfilesJPanel(Business b, JPanel clp, String facultyId) {
        business = b;
        this.CardSequencePanel = clp;
        this.currentFacultyId = facultyId;
        initComponents();
        initializeTable();
        setupEventHandlers();
        hideEditButtons(); // Faculty can't edit
        loadRealStudentData();
    }
    
    /**
     * Hide edit buttons for faculty (view-only access)
     */
    private void hideEditButtons() {
        jButton1.setVisible(false); // Add Student
        jButton2.setVisible(false); // Edit Student
        jButton3.setVisible(false); // Delete Student
    }
    
    /**
     * Initialize the table model and setup
     */
    private void initializeTable() {
        tableModel = (DefaultTableModel) studentTable.getModel();
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Set column widths
        studentTable.getColumnModel().getColumn(0).setPreferredWidth(80);  // Student ID
        studentTable.getColumnModel().getColumn(1).setPreferredWidth(120); // Full Name
        studentTable.getColumnModel().getColumn(2).setPreferredWidth(180); // Email
        studentTable.getColumnModel().getColumn(3).setPreferredWidth(100); // Major
        studentTable.getColumnModel().getColumn(4).setPreferredWidth(80);  // Year Level
        studentTable.getColumnModel().getColumn(5).setPreferredWidth(60);  // GPA
        studentTable.getColumnModel().getColumn(6).setPreferredWidth(80);  // Status
        studentTable.getColumnModel().getColumn(7).setPreferredWidth(100); // Enrollment Date
    }
    
    /**
     * Setup event handlers for table selection and buttons
     */
    private void setupEventHandlers() {
        // Table selection listener
        studentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    populateFieldsFromSelection();
                }
            }
        });
        
        // Back button
        btnBack.addActionListener(e -> goBack());
    }
    
    /**
     * Load real student data from the business layer
     */
    private void loadRealStudentData() {
        // Clear existing data
        tableModel.setRowCount(0);
        
        if (business != null) {
            try {
                StudentDirectory studentDirectory = business.getStudentDirectory();
                if (studentDirectory != null) {
                    ArrayList<StudentProfile> studentList = studentDirectory.getStudentList();
                    
                    if (studentList != null) {
                        for (StudentProfile student : studentList) {
                            if (student != null) {
                                String gpaString = student.getGPA() != 0.0 ? 
                                    String.format("%.2f", student.getGPA()) : "0.00";
                                String status = "Active"; // Default status
                                String enrollmentDate = "2024-01-01"; // Default date
                                
                                addStudentToTable(
                                    student.getStudentId() != null ? student.getStudentId() : "",
                                    student.getName() != null ? student.getName() : "",
                                    student.getEmail() != null ? student.getEmail() : "",
                                    student.getMajor() != null ? student.getMajor() : "",
                                    student.getYear() != null ? student.getYear() : "Freshman",
                                    gpaString,
                                    status,
                                    enrollmentDate
                                );
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error loading student data: " + e.getMessage());
            }
        }
    }
    
    /**
     * Add a student to the table
     */
    private void addStudentToTable(String studentId, String fullName, String email, String major, String yearLevel, String gpa, String status, String enrollmentDate) {
        Object[] row = {studentId, fullName, email, major, yearLevel, gpa, status, enrollmentDate};
        tableModel.addRow(row);
    }
    
    /**
     * Populate form fields from selected table row
     */
    private void populateFieldsFromSelection() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow >= 0) {
            fieldStudentID.setText(tableModel.getValueAt(selectedRow, 0).toString());
            
            // Split full name into first and last name
            String fullName = tableModel.getValueAt(selectedRow, 1).toString();
            String[] nameParts = fullName.split(" ", 2);
            fieldFirstName.setText(nameParts[0]);
            fieldLastName.setText(nameParts.length > 1 ? nameParts[1] : "");
            
            fieldEmail.setText(tableModel.getValueAt(selectedRow, 2).toString());
            fieldMajor.setText(tableModel.getValueAt(selectedRow, 3).toString());
            yearComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 4).toString());
            fieldStatus.setText(tableModel.getValueAt(selectedRow, 6).toString());
            fieldPhone.setText("(555) 123-4567"); // Sample phone number
            
            // Make fields read-only for faculty
            fieldStudentID.setEditable(false);
            fieldFirstName.setEditable(false);
            fieldLastName.setEditable(false);
            fieldEmail.setEditable(false);
            fieldMajor.setEditable(false);
            fieldStatus.setEditable(false);
            fieldPhone.setEditable(false);
            yearComboBox.setEnabled(false);
        }
    }
    
    /**
     * Go back to Faculty Work Area
     */
    private void goBack() {
        if (CardSequencePanel != null) {
            CardSequencePanel.removeAll();

            // Use the current faculty ID if available, otherwise use a default
            String facultyId = (currentFacultyId != null) ? currentFacultyId : "FAC001";
            FacultyWorkAreaJPanel facultyWorkArea = new FacultyWorkAreaJPanel(business, CardSequencePanel, facultyId);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(btnBack)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fieldStudentID)
                                    .addComponent(fieldEmail)
                                    .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fieldFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(fieldLastName)
                                    .addComponent(fieldPhone))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fieldMajor, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                    .addComponent(fieldStatus))))))
                .addContainerGap(72, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(140, 140, 140)
                .addComponent(jButton3)
                .addGap(117, 117, 117))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnBack)
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fieldStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(fieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(fieldMajor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(fieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(fieldStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(fieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(180, Short.MAX_VALUE))
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable studentTable;
    private javax.swing.JComboBox<String> yearComboBox;
    // End of variables declaration//GEN-END:variables
}
