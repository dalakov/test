package jpaspring.service;

import jpaspring.entity.AppUser;
import jpaspring.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser addUser(AppUser appUser) {
        return appUserRepository.saveAndFlush(appUser);
    }

    public void delete(long id) {
        appUserRepository.delete(id);
    }

    public AppUser getAppUserById(long id) { return appUserRepository.findOne(id); }

    public AppUser getByName(String name) {
        return appUserRepository.findByName(name);
    }

    public AppUser editUser(AppUser appUser) {
        return appUserRepository.saveAndFlush(appUser);
    }

    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }
}
