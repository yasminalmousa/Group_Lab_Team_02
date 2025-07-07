# Digital University System - Access-Controlled Implementation

## 1. Project Title
**Digital University System with Role-Based Access Control**
INFO 5100 Midterm Project - Implementing Access-Controlled Use Cases

## 2. Team Information

| Team Member | Role Assignment | Responsibilities |
|-------------|----------------|-----------------|
| Yasmin Almousa | Administrator Use Case |User account management, person registration, student/faculty records management, admin profile management |
| Toluwalase Mbaanne  Faculty Use Case | Course management, performance reports, student profile viewing, faculty profile management |
| Paul Kwofie | Student Use Case |Coursework management, class registration, graduation audit, transcript review |

## 3. Project Overview

This project implements a comprehensive Digital University System with robust role-based access control. The system enables three distinct user roles (Administrator, Faculty, and Student) to perform their specific responsibilities within a secure, authenticated environment.

### Key Features Implemented:
- **Authentication System**: Secure login/logout functionality with username and password validation
- **Role-Based Authorization**: Users can only access features appropriate to their role
- **Administrator Functions**: Complete user and data management capabilities
- **Faculty Functions**: Course and student management tools
- **Student Functions**: Academic tracking and course registration tools
- **Real-time GPA Calculation**: Automatic GPA computation based on completed courses
- **Course Registration Integration**: Seamless integration between course registration and transcript systems

## 4. Installation & Setup Instructions

### Prerequisites:
- **Java Development Kit (JDK)**: Version 8 or higher
- **IDE**: NetBeans, Eclipse, or IntelliJ IDEA
- **Operating System**: Windows, macOS, or Linux

### Setup Instructions:
1. **Clone the Repository**:
   ```bash
   git clone [repository-url]
   cd digital-university-system
   ```

2. **Open in IDE**:
   - Import the project as a Java project
   - Ensure all source files are in the correct package structure

3. **Build the Project**:
   - Compile all Java files
   - Resolve any dependencies

4. **Run the Application**:
   - Execute `ProfileWorkAreaMainFrame.java` as the main class
   - The login screen will appear

## 5. Authentication & Access Control

### Authentication Process:
1. **Login**: Users enter username and password on the main login screen
2. **Validation**: System validates credentials against stored user accounts
3. **Session Management**: Successful login creates a user session
4. **Logout**: Users can securely logout, clearing session data

### Authorization Rules:

| Role | Access Permissions |
|------|-------------------|
| **Administrator** | • Manage all user accounts<br>• Register new persons<br>• Manage student records<br>• Manage faculty records<br>• Update grades<br>• View all system data |
| **Faculty** | • Manage assigned courses<br>• View student profiles<br>• Generate performance reports<br>• Update own profile<br>• Access course-related data |
| **Student** | • Register for courses<br>• View own transcript<br>• Track graduation progress<br>• Manage coursework<br>• Update own profile |

### Default Login Credentials:
- **Administrator**: Username: `admin`, Password: `****`
- **Faculty**: Username: `faculty`, Password: `****`
- **Sample Student**: Username: `yasminalmousa`, Password: `****`

## 6. Features Implemented

### Administrator Use Case (Implemented by Yasmin Almousa:
#### Core Administrative Functions:
- **User Account Management** (`ManageUserAccountsJPanel.java`): Create, modify, and delete user accounts for all roles
- **Person Registration** (`ManagePersonsJPanel.java`): Register new students, faculty, and administrative staff with role-based setup
- **Student Records Management** (`ManageStudentsJPanel.java`): Comprehensive student data management including:
  - Add/Edit/Delete student profiles
  - Update student information (name, email, major, academic level)
  - View student academic progress
- **Faculty Records Management** (`ManageFacultyJPanel.java`): Complete faculty profile management system
- **Grade Management** (`GradeManagementJPanel.java`): Advanced grade management with:
  - Update individual course grades
  - Automatic GPA recalculation
  - Student performance tracking
- **Admin Profile Management** (`AdminProfileJPanel.java`): Personal profile and password management

### Faculty Use Case (Implemented by Toluwalase Mbaanne):
#### Academic Management Functions:
- **Course Management** (`ManageCoursesJPanel.java`): Comprehensive course administration including:
  - View and update course details
  - Manage course enrollment
  - Course scheduling and information updates
- **Student Profile Management** (`ManageStudentProfilesJPanel.java`): Access to student information including:
  - Academic progress monitoring
  - Student performance analytics
  - Individual student record viewing
- **Performance Reports** (`PerformanceReportsJPanel.java`): Advanced reporting system featuring:
  - Class performance analytics
  - Individual student grade reports
  - Academic progress summaries
- **Faculty Profile Management** (`FacultyProfileJPanel.java`): Personal profile and credential management

### Student Use Case (Implemented by Paul Kwofie):
#### Academic Self-Service Functions:
- **Course Registration** (`RegisterationJPanel.java`): Comprehensive enrollment system including:
  - Browse available courses
  - Enroll in new courses
  - Drop existing courses
  - Real-time course availability checking
- **Transcript Management** (`TranscriptJPanel.java`): Complete academic record access featuring:
  - View complete academic history
  - Real-time GPA calculation
  - Semester-by-semester grade breakdown
  - Quality points tracking
- **Graduation Audit** (`GraduationAuditJPanel.java`): Degree progress tracking including:
  - Credits completed vs. required
  - Remaining degree requirements
  - Graduation timeline estimation
- **Course Work Management** (`CourseWorkJPanel.java`): Current semester management with:
  - Active course listing
  - Current grades and progress
  - Assignment and project tracking
- **Profile Management** (`StudentProfileJPanel.java`): Personal information management
- **New Student Registration** (`SignUpJPanel.java`): Self-service registration for new students

## 7. Usage Instructions

### For Administrators:
1. Login with admin credentials
2. Navigate to "Manage Students" to add/edit student records
3. Use "Grade Management" to update student grades
4. Access "Manage Faculty" for faculty record management
5. Use "Register Persons" to create new user accounts

### For Faculty:
1. Login with faculty credentials
2. Access "Manage Courses" to view/update course information
3. Use "Student Reports" to generate performance analytics
4. Update profile information through "My Profile"

### For Students:
1. Login with student credentials
2. Use "Registration" to enroll in new courses
3. View "Transcript" for complete academic record
4. Check "Graduation Audit" for degree progress
5. Review "Course Work" for current semester status

## 8. Testing Guide

### Authentication Testing:
```
Test Case 1: Valid Login
- Input: Valid username/password
- Expected: Access to role-appropriate dashboard

Test Case 2: Invalid Login
- Input: Invalid credentials
- Expected: Error message and login retry

Test Case 3: Role Authorization
- Input: Attempt to access unauthorized features
- Expected: Access denied or redirect to appropriate area
```

### Functionality Testing:
```
Admin Tests:
- Create new student account
- Update student grades
- Generate faculty reports

Faculty Tests:
- Update course information
- View student performance
- Modify personal profile

Student Tests:
- Register for new course
- View updated transcript
- Check graduation requirements
```

## 9. Challenges & Solutions

### Challenge 1: GPA Calculation Integration
**Problem**: Original system had incorrect GPA calculations (showing 1.30 instead of 3.63)
**Solution**: Implemented proper Course class with quality point calculations and real-time GPA computation

### Challenge 2: Data Synchronization
**Problem**: Course registration not reflecting in transcript
**Solution**: Created unified data model where all UI components reference the same StudentProfile course list

### Challenge 3: Role-Based Access Control
**Problem**: Ensuring proper authentication and authorization
**Solution**: Implemented comprehensive user account system with role validation at UI level

### Challenge 4: UI Integration
**Problem**: Multiple panels needed to work together seamlessly
**Solution**: Used CardLayout with proper navigation and state management

## 10. Future Enhancements

- **Email Notifications**: Automated notifications for grade updates and registration
- **Advanced Reporting**: More detailed analytics and performance metrics
- **Mobile Compatibility**: Responsive design for mobile devices
- **Document Management**: Upload and manage academic documents
- **Payment Integration**: Tuition and fee payment processing
- **Calendar Integration**: Class scheduling and academic calendar
- **API Development**: RESTful API for external system integration

## 11. Contribution Breakdown

### Yasmin Almousa - Administrator Use Case:
- **Coding**: Implemented all admin functionality
  - AdminRoleWorkAreaJPanel.java
  - ManageStudentsJPanel.java
  - ManagePersonsJPanel.java
  - ManageFacultyJPanel.java
  - GradeManagementJPanel.java
  - AdminProfileJPanel.java
  - ManageUserAccountsJPanel.java
- **Testing**: Comprehensive admin feature testing
- **Documentation**: Admin section documentation
- **Integration**: Access control integration for admin features
- **UI Design**: Admin interface layout and user experience

### Toluwalase Mbaanne - Faculty Use Case:
- **Coding**: Implemented faculty functionality
  - FacultyWorkAreaJPanel.java
  - ManageCoursesJPanel.java
  - ManageStudentProfilesJPanel.java
  - PerformanceReportsJPanel.java
  - FacultyProfileJPanel.java
- **Testing**: Faculty feature testing and validation
- **Documentation**: Faculty section documentation
- **UI Design**: Faculty interface design and layout
- **Data Integration**: Faculty-student data relationship management

### Paul KWofie - Student Use Case:
- **Coding**: Implemented student functionality
  - StudentWorkAreaJPanel.java
  - RegisterationJPanel.java (Course Registration)
  - TranscriptJPanel.java
  - CourseWorkJPanel.java
  - GraduationAuditJPanel.java
  - StudentProfileJPanel.java
  - SignUpJPanel.java (New student registration)
- **Testing**: Student feature testing
- **Documentation**: Student section documentation
- **Data Model**: Course and GPA calculation implementation
- **UI/UX**: Student interface optimization

### Shared Contributions:
- **System Integration**: All team members
- **Bug Fixes**: Collaborative debugging sessions
- **Code Review**: Peer review and optimization
- **Final Testing**: End-to-end system testing
- **Core Business Logic**: Collaborative work on Business package classes

## Technical Architecture

### Complete Package Structure:
```
Assignment_3/
└── Source Packages/
    ├── Business/
    │   ├── Business.java
    │   ├── ConfigureABusiness.java
    │   └── ProfileWorkAreaMainFrame.java
    ├── Business.Person/
    │   ├── Person.java
    │   └── PersonDirectory.java
    ├── Business.Profiles/
    │   ├── EmployeeDirectory.java
    │   ├── EmployeeProfile.java
    │   ├── FacultyDirectory.java
    │   ├── FacultyProfile.java
    │   ├── Profile.java
    │   ├── StudentDirectory.java
    │   └── StudentProfile.java
    ├── Business.UserAccounts/
    │   ├── UserAccount.java
    │   └── UserAccountDirectory.java
    ├── UserInterface/
    │   └── SignUpJPanel.java
    ├── UserInterface.WorkAreas.AdminRole/
    │   ├── AdminProfileJPanel.java
    │   ├── AdminRoleWorkAreaJPanel.java
    │   └── GradeManagementJPanel.java
    ├── UserInterface.WorkAreas.AdminRole.AdministerUserAccountsWorkResp/
    │   └── ManageUserAccountsJPanel.java
    ├── UserInterface.WorkAreas.AdminRole.ManagePersonnelWorkResp/
    │   ├── ManageFacultyJPanel.java
    │   ├── ManagePersonsJPanel.java
    │   └── ManageStudentsJPanel.java
    ├── UserInterface.WorkAreas.FacultyRole/
    │   ├── FacultyProfileJPanel.java
    │   ├── FacultyWorkAreaJPanel.java
    │   ├── ManageCoursesJPanel.java
    │   ├── ManageStudentProfilesJPanel.java
    │   └── PerformanceReportsJPanel.java
    └── UserInterface.WorkAreas.StudentRole/
        ├── CourseWorkJPanel.java
        ├── GraduationAuditJPanel.java
        ├── RegisterationJPanel.java
        ├── StudentProfileJPanel.java
        ├── StudentWorkAreaJPanel.java
        └── TranscriptJPanel.java
```

### Key Classes by Role:

#### **Core Business Logic:**
- **Business.java**: Main business class coordinating all operations
- **ConfigureABusiness.java**: System initialization and sample data setup
- **ProfileWorkAreaMainFrame.java**: Main application entry point and authentication

#### **Person Management:**
- **Person.java**: Basic person entity with ID and name
- **PersonDirectory.java**: Manages all person records

#### **Profile Management:**
- **Profile.java**: Abstract base class for all user profiles
- **StudentProfile.java**: Student-specific data and GPA calculations
- **FacultyProfile.java**: Faculty-specific information and credentials
- **EmployeeProfile.java**: Administrative staff profiles

#### **Authentication & Authorization:**
- **UserAccount.java**: User credentials and session management
- **UserAccountDirectory.java**: User account repository and authentication logic

#### **Administrator UI Components:**
- **AdminRoleWorkAreaJPanel.java**: Main admin dashboard
- **ManageStudentsJPanel.java**: Student record management interface
- **ManageFacultyJPanel.java**: Faculty record management interface
- **ManagePersonsJPanel.java**: Person registration interface
- **GradeManagementJPanel.java**: Grade update and GPA management
- **AdminProfileJPanel.java**: Admin profile management

#### **Faculty UI Components:**
- **FacultyWorkAreaJPanel.java**: Main faculty dashboard
- **ManageCoursesJPanel.java**: Course management interface
- **ManageStudentProfilesJPanel.java**: Student information viewing
- **PerformanceReportsJPanel.java**: Grade and performance analytics
- **FacultyProfileJPanel.java**: Faculty profile management

#### **Student UI Components:**
- **StudentWorkAreaJPanel.java**: Main student dashboard
- **RegisterationJPanel.java**: Course registration interface
- **TranscriptJPanel.java**: Academic transcript viewing
- **CourseWorkJPanel.java**: Current coursework management
- **GraduationAuditJPanel.java**: Degree progress tracking
- **StudentProfileJPanel.java**: Student profile management

#### **Shared UI Components:**
- **SignUpJPanel.java**: New student registration interface

## System Requirements

- **Memory**: Minimum 512MB RAM
- **Storage**: 50MB available disk space
- **Display**: 1024x768 minimum resolution
- **Java Version**: JDK 8 or higher

---

**Project Completion Date**: 06/07/2025
