/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.WorkAreas.FacultyRole;

import Business.Business;
import Business.Profiles.FacultyProfile;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 *
 * @author Toluwalase Mebaanne
 */
public class FacultyProfileJPanel extends javax.swing.JPanel {

    private Business business;
    private JPanel CardSequencePanel;
    private String currentFacultyId;

    public FacultyProfileJPanel() {
        initComponents();
        setupEventHandlers();
        loadSampleData(); 
    }
    

    public FacultyProfileJPanel(Business b, JPanel clp, String facultyId) {
        business = b;
        this.CardSequencePanel = clp;
        this.currentFacultyId = facultyId;
        initComponents();
        setupEventHandlers();
        loadFacultyData(); 
    }
    

    private void setupEventHandlers() {
        jButton1.addActionListener(e -> saveChanges());
        
        jButton2.addActionListener(e -> cancelChanges());
        
        btnBack.addActionListener(e -> goBack());
    }

    private void loadSampleData() {
        fieldFacultyID.setText("FAC001");
        fieldFacultyID.setEditable(false);
        fieldFacultyID.setBackground(new java.awt.Color(248, 249, 250));
        
        fieldDepartment.setText("Computer Science");
        fieldDepartment.setEditable(false);
        fieldDepartment.setBackground(new java.awt.Color(248, 249, 250));
        
        fieldFirstName.setText("Sarah");
        fieldLastName.setText("Johnson");
        fieldEmail.setText("sarah.johnson@university.edu");
        fieldPhone.setText("(555) 123-4567");
    }

    private void loadFacultyData() {
        fieldFacultyID.setEditable(false);
        fieldFacultyID.setBackground(new java.awt.Color(248, 249, 250));
        fieldDepartment.setEditable(false);
        fieldDepartment.setBackground(new java.awt.Color(248, 249, 250));
        
        if (business != null && currentFacultyId != null) {
            try {
                FacultyProfile facultyProfile = business.getFacultyDirectory().findFacultyById(currentFacultyId);
                
                if (facultyProfile != null) {
                    fieldFacultyID.setText(facultyProfile.getFacultyId());
                    fieldDepartment.setText(facultyProfile.getDepartment());
                    
                    String fullName = facultyProfile.getName();
                    if (fullName != null && !fullName.isEmpty()) {
                        String[] nameParts = fullName.split(" ", 2);
                        fieldFirstName.setText(nameParts[0]);
                        fieldLastName.setText(nameParts.length > 1 ? nameParts[1] : "");
                    } else {
                        fieldFirstName.setText("");
                        fieldLastName.setText("");
                    }
                    
                    fieldEmail.setText(facultyProfile.getEmail());
                    fieldPhone.setText(facultyProfile.getPhoneNumber());
                    
                    System.out.println("Successfully loaded faculty data for: " + currentFacultyId);
                } else {
                    System.out.println("Faculty profile not found for ID: " + currentFacultyId);
                    JOptionPane.showMessageDialog(this, 
                        "Faculty profile not found: " + currentFacultyId, 
                        "Error", 
                        JOptionPane.WARNING_MESSAGE);
                    loadSampleData();
                }
            } catch (Exception e) {
                System.out.println("Error loading faculty data: " + e.getMessage());
                e.printStackTrace();
                loadSampleData();
            }
        } else {
            System.out.println("No business context or faculty ID provided, using sample data");
            loadSampleData();
        }
    }
    

    private void saveChanges() {
        if (validateInput()) {
            try {
                if (business != null && currentFacultyId != null) {
                    // Get the faculty profile from business layer
                    FacultyProfile facultyProfile = business.getFacultyDirectory().findFacultyById(currentFacultyId);
                    
                    if (facultyProfile != null) {
                        // Update the faculty profile with new data
                        String firstName = fieldFirstName.getText().trim();
                        String lastName = fieldLastName.getText().trim();
                        String fullName = firstName + " " + lastName;
                        
                        facultyProfile.setName(fullName);
                        facultyProfile.setEmail(fieldEmail.getText().trim());
                        facultyProfile.setPhoneNumber(fieldPhone.getText().trim());

                        JOptionPane.showMessageDialog(this, 
                            "Profile updated successfully!", 
                            "Success", 
                            JOptionPane.INFORMATION_MESSAGE);
                            
                        System.out.println("Profile updated for faculty: " + currentFacultyId);
                    } else {
                        JOptionPane.showMessageDialog(this, 
                            "Faculty profile not found.", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Unable to save - no business connection.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Error saving profile: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    private void cancelChanges() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to cancel? Any unsaved changes will be lost.", 
            "Confirm Cancel", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            loadFacultyData();
            
            JOptionPane.showMessageDialog(this, 
                "Changes cancelled - profile reset to original values.", 
                "Cancelled", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    

    private boolean validateInput() {
        if (fieldFirstName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter your first name.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            fieldFirstName.requestFocus();
            return false;
        }
        
        if (fieldLastName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter your last name.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            fieldLastName.requestFocus();
            return false;
        }
        
        if (fieldEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter your email address.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            fieldEmail.requestFocus();
            return false;
        }
        
        String email = fieldEmail.getText().trim();
        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid email address.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            fieldEmail.requestFocus();
            return false;
        }
        
        return true;
    }
    

    private void goBack() {
        if (CardSequencePanel != null) {
            CardSequencePanel.removeAll();
            
            FacultyWorkAreaJPanel facultyWorkArea = new FacultyWorkAreaJPanel(business, CardSequencePanel, currentFacultyId);
            
            CardSequencePanel.add("FacultyWorkArea", facultyWorkArea);
            
            ((java.awt.CardLayout) CardSequencePanel.getLayout()).show(CardSequencePanel, "FacultyWorkArea");
            
            CardSequencePanel.revalidate();
            CardSequencePanel.repaint();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Back button clicked - returning to Faculty Work Area", 
                "Navigation", 
                JOptionPane.INFORMATION_MESSAGE);
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
        btnBack = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        fieldFacultyID = new javax.swing.JTextField();
        fieldFirstName = new javax.swing.JTextField();
        fieldDepartment = new javax.swing.JTextField();
        fieldPhone = new javax.swing.JTextField();
        fieldLastName = new javax.swing.JTextField();
        fieldEmail = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Lao MN", 1, 18)); // NOI18N
        jLabel1.setText("My Faculty Profile");

        btnBack.setText("<<< Back");

        jLabel2.setText("Faculty ID:");

        jLabel3.setText("First Name:");

        jLabel4.setText("Last Name:");

        jLabel5.setText("Email:");

        jLabel6.setText("Department:");

        jLabel8.setText("Phone:");

        jButton1.setText("Save Changes");

        jButton2.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnBack)
                        .addGap(156, 156, 156)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fieldFirstName))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fieldLastName))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(fieldFacultyID, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldDepartment, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(fieldEmail)
                            .addComponent(fieldPhone)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jButton1)
                        .addGap(86, 86, 86)
                        .addComponent(jButton2)))
                .addContainerGap(304, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnBack)))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(fieldFacultyID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(fieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(475, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JTextField fieldDepartment;
    private javax.swing.JTextField fieldEmail;
    private javax.swing.JTextField fieldFacultyID;
    private javax.swing.JTextField fieldFirstName;
    private javax.swing.JTextField fieldLastName;
    private javax.swing.JTextField fieldPhone;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
