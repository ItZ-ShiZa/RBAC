package rbac;

import rbac.managers.UserManager;
import rbac.data.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserManagerTest {

    @Test
    void addUser() {
        UserManager manager = new UserManager();
        User user = new User("sergey", "Сергей Ракшун", "rakshun2004@gmail.com");

        manager.add(user);

        assertEquals(1, manager.count());
    }

    @Test
    void duplicateUser() {
        UserManager manager = new UserManager();
        User user = new User("sergey", "Сергей Ракшун", "rakshun2004@gmail.com");

        manager.add(user);

        assertThrows(IllegalArgumentException.class, () -> manager.add(user));
    }

    @Test
    void findUser() {
        UserManager manager = new UserManager();
        User user = new User("sergey", "Сергей Ракшун", "rakshun2004@gmail.com");

        manager.add(user);

        assertTrue(manager.findByUsername("sergey").isPresent());
    }
}