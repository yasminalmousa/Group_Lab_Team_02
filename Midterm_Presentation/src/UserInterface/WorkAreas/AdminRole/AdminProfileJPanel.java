package UserInterface.WorkAreas.AdminRole;

import Business.Business;
import javax.swing.*;
import Business.UserAccounts.UserAccount;
import Business.UserAccounts.UserAccountDirectory;

public class AdminProfileJPanel extends javax.swing.JPanel {

    private Business business;
    private javax.swing.JPanel CardSequencePanel;

    // Constructor with parameters
    public AdminProfileJPanel(Business b, JPanel clp) {
        this.business = b;
        this.CardSequencePanel = clp;
        initComponents();
        //setupReadOnlyFields();
        setupButtonActions();
        loadAdminData();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fieldFullName = new javax.swing.JTextField();
        fieldEmail = new javax.swing.JTextField();
        fieldPhone = new javax.swing.JTextField();
        fieldOfficeLocation = new javax.swing.JTextField();
        fieldDepartment = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        fieldUserID = new javax.swing.JTextField();
        fieldUsername = new javax.swing.JTextField();
        fieldRole = new javax.swing.JTextField();
        fieldAccountStatus = new javax.swing.JTextField();
        fieldLastLogin = new javax.swing.JTextField();
        fieldAccountCreated = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        fieldCurrentPassword = new javax.swing.JTextField();
        fieldNewPassword = new javax.swing.JTextField();
        fieldConfirmPassword = new javax.swing.JTextField();
        btnUpdateProfile = new javax.swing.JButton();
        btnChangePassword = new javax.swing.JButton();
        btnResetForm = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setBackground(new java.awt.Color(190, 225, 242));

        jLabel1.setFont(new java.awt.Font("Lao MN", 1, 18)); // NOI18N
        jLabel1.setText("Admin Profile");

        jLabel2.setText("Personal Information");

        jLabel3.setText("Full Name:");

        jLabel4.setText("Email:");

        jLabel5.setText("Phone:");

        jLabel6.setText("Department:");

        jLabel7.setText("Office Location:");

        jLabel8.setText("Account Information");

        jLabel9.setText("User ID:");

        jLabel10.setText("Username:");

        jLabel11.setText("Role:");

        jLabel12.setText("Account Status:");

        jLabel13.setText("Last Login:");

        jLabel14.setText("Account Created:");

        jLabel15.setText("Change Password");

        jLabel16.setText("Current Password:");

        jLabel17.setText("New Password:");

        jLabel18.setText("Confirm Password:");

        btnUpdateProfile.setText("Update Profile");

        btnChangePassword.setText("Change Password");

        btnResetForm.setText("Reset Form");

        btnBack.setText("<<< Back");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(273, 273, 273)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(fieldOfficeLocation, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel3)
                                                    .addGap(48, 48, 48))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(jLabel4)
                                                    .addGap(74, 74, 74)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel6))
                                                .addGap(38, 38, 38)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(fieldEmail)
                                            .addComponent(fieldFullName)
                                            .addComponent(fieldPhone)
                                            .addComponent(fieldDepartment, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel16)
                                        .addComponent(jLabel17)
                                        .addComponent(jLabel18))
                                    .addGap(24, 24, 24)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(fieldCurrentPassword)
                                        .addComponent(fieldNewPassword)
                                        .addComponent(fieldConfirmPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)))
                                .addComponent(jLabel15))
                            .addGap(72, 72, 72)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(105, 105, 105)
                                    .addComponent(fieldRole, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(88, 88, 88)
                                    .addComponent(fieldUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addGap(34, 34, 34)
                                    .addComponent(fieldAccountCreated))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(71, 71, 71)
                                    .addComponent(fieldLastLogin))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(43, 43, 43)
                                    .addComponent(fieldAccountStatus))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(145, 145, 145)
                            .addComponent(btnUpdateProfile)
                            .addGap(291, 291, 291)
                            .addComponent(btnResetForm))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnChangePassword)
                            .addGap(173, 173, 173)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btnBack)))
                .addContainerGap(329, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fieldFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(fieldUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(fieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(fieldRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fieldDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(fieldAccountStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(fieldOfficeLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(fieldLastLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(fieldAccountCreated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(98, 98, 98)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(fieldCurrentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(fieldNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(fieldConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateProfile)
                    .addComponent(btnChangePassword)
                    .addComponent(btnResetForm))
                .addContainerGap(68, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void setupButtonActions() {
        // Update Profile button
        btnUpdateProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProfile();
            }
        });
        
        // Change Password button
        btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePassword();
            }
        });
        
        // Reset Form button
        btnResetForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetForm();
            }
        });
        
        // Back button
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
    }
    
    private void loadAdminData() {
        // Load editable personal information (mock data)
        fieldFullName.setText("System Administrator");
        fieldEmail.setText("admin@university.edu");
        fieldPhone.setText("(555) 123-4567");
        fieldDepartment.setText("Information Technology");
        fieldOfficeLocation.setText("Admin Building, Room 205");
        
        // Load read-only account information (using actual admin credentials)
        fieldUserID.setText("admin");
        fieldUsername.setText("admin");
        fieldRole.setText("Administrator");
        fieldAccountStatus.setText("Active");
        fieldLastLogin.setText("2025-01-15 10:30 AM");
        fieldAccountCreated.setText("2024-09-01");
    }
    
    private void updateProfile() {
        try {
            // Validate required fields
            if (fieldFullName.getText().trim().isEmpty() || 
                fieldEmail.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please fill in all required fields (Name and Email)", 
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Basic email validation
            String email = fieldEmail.getText().trim();
            if (!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(this, 
                    "Please enter a valid email address", 
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // In a real application, you would save to the business layer
            // business.updateAdminProfile(fieldFullName.getText(), fieldEmail.getText(), etc.)
            
            JOptionPane.showMessageDialog(this, 
                "Profile updated successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error updating profile: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void changePassword() {
        try {
            String currentPassword = fieldCurrentPassword.getText();
            String newPassword = fieldNewPassword.getText();
            String confirmPassword = fieldConfirmPassword.getText();
            
            // Validation
            if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please fill in all password fields", 
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, 
                    "New passwords do not match", 
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (newPassword.length() < 4) {
                JOptionPane.showMessageDialog(this, 
                    "Password must be at least 4 characters long", 
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Find and update the admin user account using AuthenticateUser to verify current password
            if (business != null && business.getUserAccountDirectory() != null) {
                UserAccountDirectory userDir = business.getUserAccountDirectory();                
                // First authenticate with current credentials to verify current password
                UserAccount adminAccount = userDir.AuthenticateUser("admin", currentPassword);                
                if (adminAccount != null) {
                    // Current password is correct, update to new password
                    adminAccount.setPassword(newPassword);
                    
                    JOptionPane.showMessageDialog(this, 
                        "Password changed successfully!\nNew password: " + newPassword, 
                        "Success", 
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Current password is incorrect", 
                        "Authentication Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, 
                    "System error: Cannot access user accounts", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
                
            // Clear password fields
            fieldCurrentPassword.setText("");
            fieldNewPassword.setText("");
            fieldConfirmPassword.setText("");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error changing password: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void resetForm() {
        // Reset editable fields to original values
        loadAdminData();
        
        // Clear password fields
        fieldCurrentPassword.setText("");
        fieldNewPassword.setText("");
        fieldConfirmPassword.setText("");
        
        JOptionPane.showMessageDialog(this, 
            "Form reset to original values", 
            "Reset", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        // Navigate back to AdminRoleWorkAreaJPanel
        CardSequencePanel.remove(this);
        ((java.awt.CardLayout) CardSequencePanel.getLayout()).previous(CardSequencePanel);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JButton btnResetForm;
    private javax.swing.JButton btnUpdateProfile;
    private javax.swing.JTextField fieldAccountCreated;
    private javax.swing.JTextField fieldAccountStatus;
    private javax.swing.JTextField fieldConfirmPassword;
    private javax.swing.JTextField fieldCurrentPassword;
    private javax.swing.JTextField fieldDepartment;
    private javax.swing.JTextField fieldEmail;
    private javax.swing.JTextField fieldFullName;
    private javax.swing.JTextField fieldLastLogin;
    private javax.swing.JTextField fieldNewPassword;
    private javax.swing.JTextField fieldOfficeLocation;
    private javax.swing.JTextField fieldPhone;
    private javax.swing.JTextField fieldRole;
    private javax.swing.JTextField fieldUserID;
    private javax.swing.JTextField fieldUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
