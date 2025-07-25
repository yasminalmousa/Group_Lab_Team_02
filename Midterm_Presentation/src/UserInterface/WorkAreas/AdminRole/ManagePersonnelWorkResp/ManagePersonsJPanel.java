package UserInterface.WorkAreas.AdminRole.ManagePersonnelWorkResp;

import Business.Business;
import Business.Person.Person;
import Business.Person.PersonDirectory;
import Business.Profiles.EmployeeDirectory;
import Business.Profiles.EmployeeProfile;
import Business.Profiles.FacultyDirectory;
import Business.Profiles.FacultyProfile;
import Business.Profiles.StudentDirectory;
import Business.Profiles.StudentProfile;
import Business.UserAccounts.UserAccount;
import Business.UserAccounts.UserAccountDirectory;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JOptionPane;

import Business.UserAccounts.UserAccountDirectory;

import Business.UserAccounts.UserAccount;

/**
 * Fixed ManagePersonsJPanel compatible with updated class structure
 */
public class ManagePersonsJPanel extends javax.swing.JPanel {

    JPanel CardSequencePanel;
    Business business;

    public ManagePersonsJPanel(Business bz, JPanel jp) {
        CardSequencePanel = jp;
        this.business = bz;
        initComponents();
        setupRoleDropdown();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Back = new javax.swing.JButton();
        Next = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        fieldPassword = new javax.swing.JPasswordField();
        fieldUsername = new javax.swing.JTextField();
        fieldFullName = new javax.swing.JTextField();

        setBackground(new java.awt.Color(0, 153, 153));
        setLayout(null);

        Back.setText("<< Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });
        add(Back);
        Back.setBounds(10, 10, 80, 23);

        Next.setText("Save");
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });
        add(Next);
        Next.setBounds(500, 260, 80, 23);

        jLabel2.setFont(new java.awt.Font("Lao MN", 1, 24)); // NOI18N
        jLabel2.setText("Register New Person");
        add(jLabel2);
        jLabel2.setBounds(180, 20, 550, 34);

        jLabel3.setText("Full Name:");
        add(jLabel3);
        jLabel3.setBounds(50, 70, 120, 17);

        jLabel4.setText("Username:");
        add(jLabel4);
        jLabel4.setBounds(50, 110, 63, 17);

        jLabel5.setText("Password:");
        add(jLabel5);
        jLabel5.setBounds(50, 150, 120, 17);

        jLabel6.setText("Role:");
        add(jLabel6);
        jLabel6.setBounds(50, 190, 30, 17);

        add(jComboBox1);
        jComboBox1.setBounds(160, 190, 220, 23);

        fieldPassword.setText("jPasswordField1");
        add(fieldPassword);
        fieldPassword.setBounds(160, 150, 220, 23);
        add(fieldUsername);
        fieldUsername.setBounds(160, 110, 220, 23);
        add(fieldFullName);
        fieldFullName.setBounds(160, 70, 220, 23);
    }// </editor-fold>//GEN-END:initComponents

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        CardSequencePanel.remove(this);
        ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
    }//GEN-LAST:event_BackActionPerformed

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
        // Validate input
        if (!validateInput()) {
            return;
        }

        String fullName = fieldFullName.getText().trim();
        String username = fieldUsername.getText().trim();
        String password = new String(fieldPassword.getPassword());
        String selectedRole = (String) jComboBox1.getSelectedItem();

        try {
            // Check if username already exists
            UserAccountDirectory uad = business.getUserAccountDirectory();
            for (UserAccount ua : uad.getUserAccountList()) {
                if (ua.getUserLoginName().equalsIgnoreCase(username)) {
                    JOptionPane.showMessageDialog(this, "Username already exists. Please choose a different username.", 
                                                "Registration Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Create new person
            PersonDirectory pd = business.getPersonDirectory();
            Person newPerson = pd.newPersonWithName(fullName); // Use the correct method

            // Create profile based on selected role
            UserAccount newAccount = null;
            
            if (selectedRole.equals("Student")) {
                // For students, ask for NUID
                String nuid = JOptionPane.showInputDialog(this, "Enter NUID (9 digits):");
                if (nuid == null || !nuid.matches("\\d{9}")) {
                    JOptionPane.showMessageDialog(this, "Invalid NUID format. Must be 9 digits.", 
                                                "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if NUID already exists using the correct method
                StudentDirectory sd = business.getStudentDirectory();
                if (sd.findStudentByNUID(nuid) != null) { // Use correct method name
                    JOptionPane.showMessageDialog(this, "NUID already exists.", 
                                                "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                StudentProfile studentProfile = sd.newStudentProfile(newPerson, nuid);
                studentProfile.setEmail(username + "@student.university.edu");
                studentProfile.setMajor("Undeclared");
                studentProfile.setAcademicLevel("Undergraduate");
                
                newAccount = uad.newUserAccount(studentProfile, username, password);
                
            } 
            else if (selectedRole.equals("Faculty")) {
                FacultyDirectory fd = business.getFacultyDirectory();

                // Generate unique faculty ID
                String facultyId = "FAC" + String.format("%03d", fd.getFacultyList().size() + 1);

                // Use the constructor that properly sets all fields
                FacultyProfile facultyProfile = fd.newFacultyProfile(newPerson, "General", facultyId, "Room TBD", "(555) 000-0000", username + "@university.edu");

                // Set the name
                facultyProfile.setName(fullName);

                newAccount = uad.newUserAccount(facultyProfile, username, password);
            } 
            else if (selectedRole.equals("Admin")) {
                String email = JOptionPane.showInputDialog(this, "Enter email address:");
                String department = JOptionPane.showInputDialog(this, "Enter department:");
                String position = JOptionPane.showInputDialog(this, "Enter job position:");
                String phone = JOptionPane.showInputDialog(this, "Enter phone number:");
    
                // Set defaults if user clicks Cancel or leaves empty
                if (email == null || email.trim().isEmpty()) {
                    email = username + "@university.edu";
                }
                if (department == null || department.trim().isEmpty()) {
                    department = "Administration";
                }
                if (position == null || position.trim().isEmpty()) {
                    position = "Administrator";
                }
                if (phone == null) {
                    phone = "";
                }

                EmployeeDirectory ed = business.getEmployeeDirectory();
                EmployeeProfile employeeProfile = ed.newEmployeeProfile(newPerson);
                
                // Set employee details (these methods might need to be added to EmployeeProfile)
                // For now, just set basic info
                newAccount = uad.newUserAccount(employeeProfile, username, password);
            }
            
            if (newAccount != null) {
                JOptionPane.showMessageDialog(this, 
                    selectedRole + " account created successfully!\nUsername: " + username, 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                
                // Clear form
                clearForm();
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error creating account: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_NextActionPerformed
    private void setupRoleDropdown() {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Select Role");
        jComboBox1.addItem("Student");
        jComboBox1.addItem("Faculty");
        jComboBox1.addItem("Admin");
    }

    private boolean validateInput() {
        // Check if any field is empty
        if (fieldFullName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Full name is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            fieldFullName.requestFocus();
            return false;
        }

        if (fieldUsername.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            fieldUsername.requestFocus();
            return false;
        }

        if (fieldPassword.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Password is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            fieldPassword.requestFocus();
            return false;
        }

        if (jComboBox1.getSelectedItem() == null || jComboBox1.getSelectedItem().equals("Select Role")) {
            JOptionPane.showMessageDialog(this, "Please select a role.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            jComboBox1.requestFocus();
            return false;
        }

        // Password length validation
        if (fieldPassword.getPassword().length < 4) {
            JOptionPane.showMessageDialog(this, "Password must be at least 4 characters long.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            fieldPassword.requestFocus();
            return false;
        }

        return true;
    }

    private void clearForm() {
        fieldFullName.setText("");
        fieldUsername.setText("");
        fieldPassword.setText("");
        jComboBox1.setSelectedIndex(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JButton Next;
    private javax.swing.JTextField fieldFullName;
    private javax.swing.JPasswordField fieldPassword;
    private javax.swing.JTextField fieldUsername;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables

}
