package jpaspring.service;

import jpaspring.entity.AppRole;
import jpaspring.repository.AppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppRoleServiceImpl implements AppRoleService {

    private final AppRoleRepository appRoleRepository;

    @Autowired
    public AppRoleServiceImpl(AppRoleRepository appRoleRepository) {
        this.appRoleRepository = appRoleRepository;
    }

    public AppRole addRole(AppRole appRole) {
        return appRoleRepository.saveAndFlush(appRole);
    }

    public void delete(long id) {
        appRoleRepository.delete(id);
    }

    public AppRole editRole(AppRole appRole) {
        return appRoleRepository.saveAndFlush(appRole);
    }

    public List<AppRole> findAll() {
        return appRoleRepository.findAll();
    }
}
