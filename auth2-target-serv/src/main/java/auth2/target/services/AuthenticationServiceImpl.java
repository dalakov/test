package auth2.target.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

public class AuthenticationServiceImpl implements AuthenticationService {
    
    private RestOperations restTemplate;
    private String authURL;
	
    public List<String> getAuthentication(){
        try {
	    InputStream dataXML = new ByteArrayInputStream(restTemplate.getForObject(URI.create(authURL), byte[].class));
            final List<String> params = new ArrayList<String>();
            /* parse dataXML and get data */  
            return params;
        } catch (RestClientException e) {
            throw new IllegalStateException(e);
        }
    }

    public RestOperations getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAuthURL() {
        return authURL;
    }

    public void setAuthURL(String authURL) {
        this.authURL = authURL;
    }
    
}