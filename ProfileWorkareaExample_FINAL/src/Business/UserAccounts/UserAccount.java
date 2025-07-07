/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.UserAccounts;

import Business.Profiles.Profile;
import java.util.Date;

/**
 *
 * @author student
 */
public class UserAccount {
    
    Profile profile;
    String username;
    String password;
    Date lastLoginTime;
    Date lastUpdatedTime;
    boolean isActive;
    
    public UserAccount(Profile profile, String un, String pw) {
        username = un;
        password = pw;
        this.profile = profile;
        this.lastLoginTime = null;
        this.lastUpdatedTime = new Date();
        this.isActive = true;
    }

    public String getPersonId() {
        return profile.getPerson().getPersonId();
    }
    
    public String getUserLoginName() {
        return username;
    }

    public boolean isMatch(String id) {
        if (getPersonId().equals(id)) return true;
        return false;
    }
    
    public boolean IsValidUser(String un, String pw) {
        if (username.equalsIgnoreCase(un) && password.equals(pw)) {
            updateLastLoginTime();
            return true;
        } else {
            return false;
        }
    }
    
    public String getRole() {
        return profile.getRole();
    }
        
    public Profile getAssociatedPersonProfile() {
        return profile;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void updateLastLoginTime() {
        this.lastLoginTime = new Date();
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void updateLastUpdatedTime() {
        this.lastUpdatedTime = new Date();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
        updateLastUpdatedTime();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        updateLastUpdatedTime();
    }

    public void setUsername(String username) {
        this.username = username;
        updateLastUpdatedTime();
    }

    public String getStatus() {
        return isActive ? "Active" : "Inactive";
    }

    public String getLastLoginString() {
        if (lastLoginTime == null) {
            return "Never logged in";
        }
        return lastLoginTime.toString();
    }

    public String getLastUpdatedString() {
        if (lastUpdatedTime == null) {
            return "Never updated";
        }
        return lastUpdatedTime.toString();
    }
        
    @Override
    public String toString() {
        return getUserLoginName();
    }
}