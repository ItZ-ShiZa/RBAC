package rbac.filters;

import rbac.data.Permission;
import rbac.data.Role;

public class RoleFilters {

    public static RoleFilter byName(String name) {
        return r -> r.getName().equals(name);
    }

    public static RoleFilter byNameContains(String substring) {
        String s = substring.toLowerCase();
        return r -> r.getName().toLowerCase().contains(s);
    }

    public static RoleFilter hasPermission(Permission permission) {
        return r -> r.hasPermission(permission);
    }

    public static RoleFilter hasPermission(String name, String resource) {
        return r -> r.hasPermission(name, resource);
    }

    public static RoleFilter hasAtLeastNPermissions(int n) {
        return r -> r.getPermissions().size() >= n;
    }
}