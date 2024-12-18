package ma.enset;

import ma.enset.security.SecurityContext;
import ma.enset.security.User;
import ma.enset.security.enums.Role;

import java.util.List;

public class AppInitializer {
    private static final SecurityContext securityContext = SecurityContext.getInstance();
    public static void run() {
        List<Role> roles = List.of(Role.ADMIN, Role.USER);
        securityContext.setInMemoryUsers(List.of(
            new User("admin", "1234", roles),
            new User("user", "1234", List.of(Role.USER))
        ));
    }
}
