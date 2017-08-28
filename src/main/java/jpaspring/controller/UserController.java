package jpaspring.controller;

import jpaspring.entity.*;
import jpaspring.service.*;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

@Controller
@Log
public class UserController {

    private final AppUserService appUserService;

    @Autowired
    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @RequestMapping(path="/", method=RequestMethod.GET)
    public String showMain( ) {
        return "main";
    }

    @RequestMapping(value="/main", params={"save"})
    public String saveAppUser(final AppUser appUser, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "main";
        }
        this.appUserService.addUser(appUser);
        model.clear();
        return "redirect:/main";
    }

}