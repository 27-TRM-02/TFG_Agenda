package trm.agenda.authentication.utility;

import org.springframework.security.core.context.SecurityContextHolder;

import trm.agenda.authentication.service.UserDetailsImpl;

public class AuthenticationUtility {

    public static UserDetailsImpl getCurrentUser() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
