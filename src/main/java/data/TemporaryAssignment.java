package rbac.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TemporaryAssignment extends AbstractRoleAssignment {
    private String expiresAt;
    private boolean autoRenew;
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public TemporaryAssignment(User user, Role role, AssignmentMetadata metadata,
                               String expiresAt, boolean autoRenew) {
        super(user, role, metadata);
        setExpiresAt(expiresAt);
        this.autoRenew = autoRenew;
    }

    @Override public boolean isActive() { return !isExpired(); }
    @Override public String assignmentType() { return "TEMPORARY"; }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(LocalDateTime.parse(expiresAt, FORMATTER));
    }

    public boolean isExpired(LocalDateTime time) {
        return time.isAfter(LocalDateTime.parse(expiresAt, FORMATTER));
    }

    public void extend(String newDate) { setExpiresAt(newDate); }

    private void setExpiresAt(String expiresAt) {
        try {
            LocalDateTime.parse(expiresAt, FORMATTER);
            this.expiresAt = expiresAt;
        } catch (Exception e) {
            throw new IllegalArgumentException("Используйте формат: yyyy-MM-dd HH:mm");
        }
    }

    public String getExpiresAt() { return expiresAt; }
    public boolean isAutoRenew() { return autoRenew; }
    public void setAutoRenew(boolean autoRenew) { this.autoRenew = autoRenew; }

    public String getTimeRemaining() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiry = LocalDateTime.parse(expiresAt, FORMATTER);

        if (now.isAfter(expiry)) return "Expired";

        long days = ChronoUnit.DAYS.between(now, expiry);
        long hours = ChronoUnit.HOURS.between(now, expiry) % 24;
        long minutes = ChronoUnit.MINUTES.between(now, expiry) % 60;

        return String.format("%d дн, %d ч, %d мин", days, hours, minutes);
    }

    @Override
    public String summary() {
        String status = isActive() ? "АКТИВНО" : "ИСТЕКЛО";
        String reason = metadata().getReason().map(r -> "Причина: " + r).orElse("Причина не указана");

        return String.format("[%s] %s назначена пользователю %s от %s в %s %s Истекает: %s Статус: %s",
                assignmentType(), role().getName(), user().username(),
                metadata().assignedBy(), metadata().assignedAt(), reason, expiresAt, status);
    }
}