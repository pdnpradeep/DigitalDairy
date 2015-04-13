package org.digital.dairy.serviceimp;

import org.digital.dairy.entity.Role;
import org.digital.dairy.entity.User;
import org.digital.dairy.entity.VerificationToken;
import org.digital.dairy.repository.rdbmysql.UserRepository;
import org.digital.dairy.repository.rdbmysql.VerificationTokenRepository;
import org.digital.dairy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Pradeep.P on 11-04-2015.
 */
@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    VerificationTokenRepository tokenRepository;

    @Override
    public User createUserAccount(User user) {
        Role role = new Role();
        role.setUser(user);
        role.setName(1);
        user.setRoles(role);
        return userRepository.save(user);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken newTokern = new VerificationToken(token,user);
        tokenRepository.save(newTokern);
    }

    @Override
    public VerificationToken getVerificationToken(String verificaationToken) {
       return tokenRepository.findByToken(verificaationToken);
    }

    @Override
    public void saveRegistrationUser(User user) {
        userRepository.save(user);
    }
}
