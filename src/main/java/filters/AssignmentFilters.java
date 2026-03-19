package rbac.filters;

import rbac.data.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AssignmentFilters {

    private static final DateTimeFormatter META_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter TEMP_FORMAT = TemporaryAssignment.FORMATTER;

    public static AssignmentFilter byUser(User user) {
        return a -> a.user().equals(user);
    }

    public static AssignmentFilter byUsername(String username) {
        return a -> a.user().username().equals(username);
    }

    public static AssignmentFilter byRole(Role role) {
        return a -> a.role().equals(role);
    }

    public static AssignmentFilter byRoleName(String roleName) {
        return a -> a.role().getName().equals(roleName);
    }

    public static AssignmentFilter activeOnly() {
        return RoleAssignment::isActive;
    }

    public static AssignmentFilter inactiveOnly() {
        return a -> !a.isActive();
    }

    public static AssignmentFilter byType(String type) {
        return a -> a.assignmentType().equalsIgnoreCase(type);
    }

    public static AssignmentFilter assignedBy(String username) {
        return a -> a.metadata().assignedBy().equals(username);
    }

    public static AssignmentFilter assignedAfter(String date) {
        LocalDateTime d = LocalDateTime.parse(date, META_FORMAT);
        return a -> LocalDateTime.parse(a.metadata().assignedAt(), META_FORMAT).isAfter(d);
    }

    public static AssignmentFilter expiringBefore(String date) {
        LocalDateTime d = LocalDateTime.parse(date, TEMP_FORMAT);
        return a -> {
            if (a instanceof TemporaryAssignment t)
                return LocalDateTime.parse(t.getExpiresAt(), TEMP_FORMAT).isBefore(d);
            return false;
        };
    }
}