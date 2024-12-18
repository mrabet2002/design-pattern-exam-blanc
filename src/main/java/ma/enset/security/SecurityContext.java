package ma.enset.security;

import ma.enset.security.enums.Role;

import java.util.List;

public class SecurityContext {
    private User user;
    private List<User> inMemoryUsers;
    private static SecurityContext instance;

    public static SecurityContext getInstance() {
        if (instance == null) {
            instance = new SecurityContext();
        }
        return instance;
    }

    public User authenticate(String username, String password) {
        for (User user : inMemoryUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                this.user = user;
                return user;
            }
        }
        throw new RuntimeException("Invalid credentials");
    }

    public boolean hasRole(String role) {
        for (Role userRole : user.getRoles()) {
            if (userRole.name().equals(role)) {
                return true;
            }
        }
        return false;
    }

    public User getUser() {
        return user;
    }

    public void setInMemoryUsers(List<User> inMemoryUsers) {
        this.inMemoryUsers = inMemoryUsers;
    }

    public boolean hasRoles(String[] roles) {
        for (String role : roles) {
            if (!hasRole(role)) {
                return false;
            }
        }
        return true;
    }
}
