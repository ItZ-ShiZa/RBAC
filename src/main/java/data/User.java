package rbac.data;

import rbac.data.Validatable;
import java.util.regex.Pattern;

public record User(String username, String fullName, String email) implements Validatable {

    private static final Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9_]{3,20}$");
    private static final Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)\\.[A-Za-z]{2,}$");

    public User {
        checkValidate(username, "Нету юзернейма", "Пустой юзернейм");
        checkValidate(fullName, "Нету полного имени", "Пустое имя");
        checkValidate(email, "Почта не добавлена", "Пустая почта");

        if (!usernamePattern.matcher(username).matches())
            throw new IllegalArgumentException("Неверный юзернейм");
        if (!emailPattern.matcher(email).matches())
            throw new IllegalArgumentException("Неверная почта");
    }

    public static User create(String username, String fullName, String email) {
        return new User(username, fullName, email);
    }

    public String format() {
        return String.format("%s (%s) <%s>", username, fullName, email);
    }
}