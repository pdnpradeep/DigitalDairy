package org.digital.dairy.security;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Pradeep.P on 04-04-2015.
 */
public class UserWebAuthenticationDetails extends WebAuthenticationDetails {

    public UserWebAuthenticationDetails(HttpServletRequest context) {
        super(context);
    }
}
