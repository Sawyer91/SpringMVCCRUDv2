package web.dao;

import web.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    public List<Role> readRole();

    public Set<Role> getRoles(String[] ids);
}
