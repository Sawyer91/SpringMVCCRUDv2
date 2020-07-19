package web.dao;

import web.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    public List<Role> getRoles();

    public Set<Role> getRolesById(String ids);
}
