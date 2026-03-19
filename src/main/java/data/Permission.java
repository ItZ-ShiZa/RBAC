package rbac.data;

import util.Validatable;
import java.util.Objects;

public record Permission(String name, String resource, String description) implements Validatable {

    public Permission {
        checkValidate(name, "Нету названия", "Пустое название");
        checkValidate(resource, "Нету ресурса", "Пустой ресурс");
        checkValidate(description, "нету описания", "Пустое описание");

        if (name.contains(" ")) throw new IllegalArgumentException("Название должно быть без пробелов");

        name = name.toUpperCase();
        resource = resource.toLowerCase();
    }

    public String format() {
        return String.format("%s на %s: %s", name, resource, description);
    }

    public boolean matches(String namePattern, String resourcePattern) {
        boolean nameMatches = namePattern == null || namePattern.isEmpty() || name.contains(namePattern) || name.matches(namePattern);
        boolean resourceMatches = resourcePattern == null || resourcePattern.isEmpty() || resource.contains(resourcePattern) || resource.matches(resourcePattern);
        return nameMatches && resourceMatches;
    }
}