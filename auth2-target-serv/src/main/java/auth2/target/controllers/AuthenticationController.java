package auth2.target.controllers;

import auth2.target.services.AuthenticationService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class AuthenticationController  {
    
    private AuthenticationService authenticationService;    
   
    //Here we use a dummy login because the authorization will pass on AuthenticationServer
    private void autoLogin () {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
        updatedAuthorities.add(authority);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("dummy","dummy",updatedAuthorities));
    } 
    
    @RequestMapping(value = { "/", "welcome" }, method = RequestMethod.GET)
    public String welcomePage() {
        autoLogin();         
        return "welcome";
    }
   
    @RequestMapping("/auth2serv/data") 
    public String getAuthentication(Model model){
        model.addAttribute("authentication", getAuthenticationService().getAuthentication());
        return "after-auth2";
    }
              
    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    
}