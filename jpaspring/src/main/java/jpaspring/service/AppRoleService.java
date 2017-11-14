package jpaspring.service;

import jpaspring.entity.AppRole;
import java.util.List;

public interface AppRoleService {
    AppRole addRole(AppRole appRole);
    void delete(long id);
    AppRole editRole(AppRole appRole);
    List<AppRole> findAll();
}
