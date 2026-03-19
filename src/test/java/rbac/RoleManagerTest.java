package rbac;

import managers.RoleManager;
import model.Permission;
import model.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoleManagerTest {

    @Test
    void addRole() {
        RoleManager manager = new RoleManager();
        Role role = new Role("ADMIN", "admin role");

        manager.add(role);

        assertEquals(1, manager.count());
    }

    @Test
    void addPermission() {
        RoleManager manager = new RoleManager();
        Role role = new Role("ADMIN", "admin role");

        manager.add(role);

        Permission p = new Permission("READ", "server", "read access");

        manager.addPermissionToRole("ADMIN", p);

        assertTrue(role.hasPermission(p));
    }
}