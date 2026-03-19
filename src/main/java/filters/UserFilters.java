package rbac.filters;

import rbac.data.User;

public class UserFilters {

    public static UserFilter byUsername(String username) {
        return u -> u.username().equals(username);
    }

    public static UserFilter byUsernameContains(String substring) {
        String s = substring.toLowerCase();
        return u -> u.username().toLowerCase().contains(s);
    }

    public static UserFilter byEmail(String email) {
        return u -> u.email().equals(email);
    }

    public static UserFilter byEmailDomain(String domain) {
        return u -> u.email().toLowerCase().endsWith(domain.toLowerCase());
    }

    public static UserFilter byFullNameContains(String substring) {
        String s = substring.toLowerCase();
        return u -> u.fullName().toLowerCase().contains(s);
    }
}