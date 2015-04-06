package org.digital.dairy.security;

/**
 * Created by Pradeep.P on 04-04-2015.
 */
import java.util.Collection;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Data
@EqualsAndHashCode(callSuper=false)
public class LoggedInUserDetails extends User {

    private static final long serialVersionUID = 2149394973496625539L;

    public LoggedInUserDetails(String userName, String password , Collection<? extends GrantedAuthority> authorities) {
        super(userName, password, authorities);
    }

}