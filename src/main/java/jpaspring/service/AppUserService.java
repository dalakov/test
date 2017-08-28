package jpaspring.service;

import jpaspring.entity.AppUser;
import java.util.List;

public interface AppUserService {
    AppUser addUser(AppUser appUser);
    void delete(long id);
    AppUser getAppUserById(long id);
    AppUser getByName(String name);
    AppUser editUser(AppUser appUser);
    List<AppUser> findAll();
}
