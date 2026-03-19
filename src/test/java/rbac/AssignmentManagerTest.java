package rbac;

import rbac.managers.AssignmentManager;
import rbac.data.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssignmentManagerTest {

    @Test
    void assignRole() {
        AssignmentManager manager = new AssignmentManager();

        User user = new User("sergey", "Сергей Ракшун", "rakshun2004@gmail.com");
        Role role = new Role("ADMIN", "admin role");

        RoleAssignment assignment =
                new PermanentAssignment(user, role, AssignmentMetadata.now("system", null));

        manager.add(assignment);

        assertEquals(1, manager.count());
    }

    @Test
    void userHasRole() {
        AssignmentManager manager = new AssignmentManager();

        User user = new User("sergey", "Сергей Ракшун", "rakshun2004@gmail.com");
        Role role = new Role("ADMIN", "admin role");

        RoleAssignment assignment =
                new PermanentAssignment(user, role, AssignmentMetadata.now("system", null));

        manager.add(assignment);

        assertTrue(manager.userHasRole(user, role));
    }
}