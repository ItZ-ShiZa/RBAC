package rbac.data;

import java.util.Objects;
import java.util.UUID;

public abstract class AbstractRoleAssignment implements RoleAssignment {
    private final String assignmentId;
    private final User user;
    private final Role role;
    private final AssignmentMetadata metadata;

    public AbstractRoleAssignment(User user, Role role, AssignmentMetadata metadata) {
        this.assignmentId = "assign_" + UUID.randomUUID().toString().substring(0, 8);
        this.user = Objects.requireNonNull(user, "Посьзователь нет");
        this.role = Objects.requireNonNull(role, "Нет роли");
        this.metadata = Objects.requireNonNull(metadata, "Нет метаданных");
    }

    @Override public String assignmentId() { return assignmentId; }
    @Override public User user() { return user; }
    @Override public Role role() { return role; }
    @Override public AssignmentMetadata metadata() { return metadata; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractRoleAssignment that = (AbstractRoleAssignment) o;
        return Objects.equals(assignmentId, that.assignmentId);
    }

    @Override
    public int hashCode() { return Objects.hash(assignmentId); }

    public String summary() {
        String status = isActive() ? "АКТИВНА" : "НЕАКТИВНА";
        String reason = metadata().getReason().map(r -> "Причина: " + r).orElse("Причина не указана");

        return String.format("[%s] %s назначена пользователю %s от %s в %s %s Статус: %s",
                assignmentType(), role().getName(), user().username(),
                metadata().assignedBy(), metadata().assignedAt(), reason, status);
    }
}