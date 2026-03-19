package rbac.data;

import java.util.Objects;

public interface Validatable {
    default void checkValidate(String value, String nullMsg, String emptyMsg) {
        Objects.requireNonNull(value, nullMsg);
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(emptyMsg);
        }
    }
}