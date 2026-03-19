package rbac.data;

import rbac.data.Validatable;
import java.util.*;

public class Role implements Validatable {
    private final String id;
    private String name;
    private String description;
    private final Set<Permission> permissions;

    public Role(String name, String description) {
        this.id = "role_" + UUID.randomUUID().toString().substring(0, 8);

        checkValidate(name, "Отсутствует имя роли", "Пустое имя роли");
        checkValidate(description, "Не тописания", "Пустое описание");

        this.name = name;
        this.description = description;
        this.permissions = new HashSet<>();
    }

    public void addPermission(Permission permission) { permissions.add(permission); }
    public void removePermission(Permission permission) { permissions.remove(permission); }
    public boolean hasPermission(Permission p) { return permissions.contains(p); }
    public boolean hasPermission(String name, String resource) { return permissions.stream().anyMatch(p -> p.name().equalsIgnoreCase(name) && p.resource().equalsIgnoreCase(resource)); }
    public Set<Permission> getPermissions() { return Collections.unmodifiableSet(permissions); }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String desc) { this.description = desc; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    public String format() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Роль: %s [ID: %s] Описание: %s\n", name, id, description));
        sb.append(String.format("Права (%d):\n", permissions.size()));
        for (Permission p : permissions) sb.append("  - ").append(p.format()).append("\n");
        return sb.toString().trim();
    }
}