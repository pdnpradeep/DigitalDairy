package org.digital.dairy.scheduler.serviceimp;

import org.digital.dairy.async.producer.RegistrationConformationMailProducer;
import org.digital.dairy.entity.VerificationToken;
import org.digital.dairy.repository.rdbmysql.VerificationTokenRepository;
import org.digital.dairy.scheduler.service.ResendVerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Pradeep.P on 07-06-2015.
 */
@Service
public class ResendVerificationTokenServiceImp implements ResendVerificationTokenService {

    @Autowired
    RegistrationConformationMailProducer registrationConformationMailProducer;
    @Autowired
    VerificationTokenRepository tokenRepository;
    @Resource(name="stringRedisTemplate")
    ValueOperations<String,Long> valueOperations;

    @Override
    public void resendVerificationToken() {
        System.out.println("11111111111111111111111111");
        Calendar aGMTCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        System.out.println("resendVerificationToken call time............."+aGMTCalendar.getTimeInMillis());
        valueOperations.set("resendVerificationTokenJob",aGMTCalendar.getTimeInMillis());
        /*List<ResentConformationMailVO> VerificationEmail = new ArrayList<ResentConformationMailVO>();*/
        List<VerificationToken> VerificationUserId = new ArrayList<VerificationToken>();

        System.out.println("Date formate is used in server end is 1: "+new Date());
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
}
