package rbac.filters;

import rbac.data.RoleAssignment;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class AssignmentSorters {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Comparator<RoleAssignment> byUsername() {
        return Comparator.comparing(a -> a.user().username());
    }

    public static Comparator<RoleAssignment> byRoleName() {
        return Comparator.comparing(a -> a.role().getName());
    }

    public static Comparator<RoleAssignment> byAssignmentDate() {
        return Comparator.comparing(a -> LocalDateTime.parse(a.metadata().assignedAt(), FORMAT));
    }
}