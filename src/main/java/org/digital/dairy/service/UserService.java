package org.digital.dairy.service;

import org.digital.dairy.model.entity.User;
import org.digital.dairy.model.entity.VerificationToken;

/**
 * Created by Pradeep.P on 11-04-2015.
 */
public interface UserService {
    User createUserAccount(User user);
    void createVerificationToken(String userEmail,String token);
    VerificationToken getVerificationToken(String token);
    void saveRegistrationUser(User user);
    void resendVerificationToken();
    String findUserEmailID(String Username);
}
