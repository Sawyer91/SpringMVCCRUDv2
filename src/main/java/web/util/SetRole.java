package web.util;

import org.springframework.stereotype.Component;
import web.model.Role;

import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class SetRole {

    public Set<Role> setRole(String[] ids, TypedQuery<Role> query) {
        Set<Role> roles = new HashSet<>();
        Arrays.stream(ids).forEach(roleId -> {
            query.setParameter("id", Long.parseLong(roleId));
            roles.add(query.getSingleResult());
        });
        return roles;
    }
}
