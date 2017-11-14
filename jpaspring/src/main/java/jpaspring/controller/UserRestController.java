package jpaspring.controller;

import jpaspring.entity.AppUser;
import jpaspring.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

    private final AppUserService appUserService;

    @Autowired
    public UserRestController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @RequestMapping(value = "/appusers", method= RequestMethod.GET)
    public List<AppUser> getAllAppUser() {
        return this.appUserService.findAll();
    }

    @RequestMapping(value = "/appuser/{id}", method = RequestMethod.GET)
    public AppUser getEmployeeById(@PathVariable("id") long id){
        return appUserService.getAppUserById(id);
    }


}
