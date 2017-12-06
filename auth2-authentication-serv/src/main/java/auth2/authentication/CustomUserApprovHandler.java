package auth2.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.ApprovalStoreUserApprovalHandler;

public class CustomUserApprovHandler extends ApprovalStoreUserApprovalHandler {

    /* Allows request approval  */
    @Override
    public AuthorizationRequest checkForPreApproval(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
        boolean approved = false;
        authorizationRequest = super.checkForPreApproval(authorizationRequest, userAuthentication);
        approved = authorizationRequest.isApproved();
        authorizationRequest.setApproved(approved);
        return authorizationRequest;
    }

}