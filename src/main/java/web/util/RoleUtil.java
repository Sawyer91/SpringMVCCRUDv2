package web.util;
;
import web.model.Role;

import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RoleUtil {

    public static Set<Role> getRoleForUser(String ids, TypedQuery<Role> query) {
        Set<Role> roles = new HashSet<>();
        Arrays.stream(new String[]{ids}).forEach(roleId -> {
            query.setParameter("id", Long.parseLong(roleId));
            roles.add(query.getSingleResult());
        });
        return roles;
    }
}
