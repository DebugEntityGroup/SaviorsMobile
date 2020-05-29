package models;

import java.util.Objects;

public class UserLogin {
    
    private int id;
    private String username;
    private String password;
    private static UserLogin coUserLogin;
    private static UserLogin instance;

    public UserLogin(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
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

    public static UserLogin getCoUserLogin() {
        return coUserLogin;
    }

    public static void setCoUserLogin(UserLogin coUserLogin) {
        UserLogin.coUserLogin = coUserLogin;
    }

    public static UserLogin getInstance() {
        return instance;
    }

    public static void setInstance(UserLogin instance) {
        UserLogin.instance = instance;
    }

    @Override
    public String toString() {
        return "UserLogin{" + "id="+id+", username=" + username + ", password=" + password + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserLogin other = (UserLogin) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
    
    
    
}
