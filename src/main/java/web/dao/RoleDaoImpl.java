package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.util.SetRole;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

@Repository(value = "roleDaoImpl")
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final SetRole setRole;

    @Autowired
    public RoleDaoImpl(SetRole setRole) {
        this.setRole = setRole;
    }

    @Override
    public List<Role> readRole() {
        TypedQuery<Role> query= entityManager.createQuery("from Role", Role.class);
        return query.getResultList();
    }

    @Override
    public Set<Role> getRoles(String[] ids) {
        TypedQuery<Role> query= entityManager.createQuery("from Role where id = :id", Role.class);
        return setRole.setRole(ids, query);
    }
}
