package rbac.data;

import util.Validatable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

public record AssignmentMetadata(String assignedBy, String assignedAt, String reason) implements Validatable {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public AssignmentMetadata {
        checkValidate(assignedBy, "Назначитель не найден", "Назначитель пустой");
        checkValidate(assignedAt, "Дата не найдена", "Дата пустая");
    }

    public static AssignmentMetadata now(String assignedBy, String reason) {
        return new AssignmentMetadata(assignedBy, LocalDateTime.now().format(FORMATTER), reason);
    }

    public Optional<String> getReason() { return Optional.ofNullable(reason); }

    public String format() {
        if (reason != null && !reason.isEmpty())
            return String.format("Назначено: %s в %s. Причина: %s", assignedBy, assignedAt, reason);
        return String.format("Назначено: %s в %s", assignedBy, assignedAt);
    }
}