package org.digital.dairy.service;

import org.digital.dairy.entity.User;
import org.digital.dairy.entity.VerificationToken;

/**
 * Created by Pradeep.P on 11-04-2015.
 */
public interface UserService {
    User createUserAccount(User user);
    void createVerificationToken(User user,String token);
    VerificationToken getVerificationToken(String token);
    void saveRegistrationUser(User user);
}
