package models;

import java.util.ArrayList;
import java.util.List;

public class UserSignup {

    private int id;
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String role;

    public UserSignup(int id, String username, String email, String password, String confirmPassword, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;
    }

    public UserSignup(String username, String email, String password, String confirmPassword, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;
    }

    public UserSignup(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public UserSignup(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserSignup() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRoles(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        if (role.equals("[ROLE_ASSOC, ROLE_USER]")) {
            this.setRoles("Association");
        }
        if (role.equals("[ROLE_MEMBER, ROLE_USER]")) {
            this.setRoles("Membre");
        }
        if (role.equals("[ROLE_FOURN, ROLE_USER]")) {
            this.setRoles("Fournisseur");
        }
        setPassword("*******");
        return "Username=" + username + ", password=" + password + ", email=" + email + ", role=" + role;

    }

}
