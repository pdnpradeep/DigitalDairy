package org.digital.dairy.serviceimp;

import org.digital.dairy.async.producer.RegistrationConformationMailProducer;
import org.digital.dairy.model.entity.Role;
import org.digital.dairy.model.entity.User;
import org.digital.dairy.model.entity.VerificationToken;
import org.digital.dairy.repository.rdbmysql.UserRepository;
import org.digital.dairy.repository.rdbmysql.VerificationTokenRepository;
import org.digital.dairy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Autowired
    RegistrationConformationMailProducer registrationConformationMailProducer;

    @Override
    public User createUserAccount(User user) {
        Role role = new Role();
        role.setUser(user);
        role.setName(1);
        user.setRoles(role);
        return userRepository.save(user);
    }

    @Override
    public void createVerificationToken(String userEmail, String token) {
        User user = userRepository.findByEmail(userEmail);
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

    @Override
    public void resendVerificationToken() {
        /*List<ResentConformationMailVO> VerificationEmail = new ArrayList<ResentConformationMailVO>();*/
       List<VerificationToken> VerificationUserId = new ArrayList<VerificationToken>();

        System.out.println("Date formate is used in server end is : "+new Date());
        VerificationUserId = tokenRepository.findAll();
        for(int i=0;i<VerificationUserId.size();i++){
            System.out.println("user :"+i+VerificationUserId.get(i).getUser().getEmail());
            registrationConformationMailProducer.resendConformstionMail(VerificationUserId.get(i).getUser().getEmail(),VerificationUserId.get(i).getToken());
        }
        System.out.println("last in service");
      /*  VerificationEmail = tokenRepository.findTokensForUnExpireUsers();*/
       /* for(int i=0;i<VerificationEmail.size();i++){
                registrationConformationMailProducer.resendConformstionMail(VerificationEmail.get(i).getUserEmail(),VerificationEmail.get(i).getExisttoken());
        }*/
    }
    @Override
    @Cacheable(value = "userData",key = "#Username")
    public String findUserEmailID(String Username) {
        User user = userRepository.findByUsername(Username);
        String userEmail = user.getEmail();
        return userEmail;
    }
}
