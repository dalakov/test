package auth2.controllers;

import auth2.authentication.CustomUserApprovHandler;
import auth2.services.DataViewService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private CustomUserApprovHandler userApprovalHandler;
        
    public void setUserApprovalHandler(CustomUserApprovHandler userApprovalHandler) {
	this.userApprovalHandler = userApprovalHandler;
    }
   
   //we don't need to login so we use dummy autologin 
   private void autoLogin () {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
        updatedAuthorities.add(authority);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("dummy","dummy",updatedAuthorities));
    } 
  
    
    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage() {
        autoLogin();   
        return "welcome";
    }
    
    @RequestMapping(value = { "/viewdbusers" }, method = RequestMethod.GET)
    public String viewDbUsers() {
        DataViewService.runDataViewer();
        return "welcome";
    }

    @RequestMapping(value = "/data")
    public ResponseEntity<String> getData(@RequestParam(value = "callback", required = false) String callback, Principal principal) {
	StringBuilder out = new StringBuilder();
	/* out.append("<data>"); */
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/xml");
	return new ResponseEntity<String>(out.toString(), headers, HttpStatus.OK);
    }

}
