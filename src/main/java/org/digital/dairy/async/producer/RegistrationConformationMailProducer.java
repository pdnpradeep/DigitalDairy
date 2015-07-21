package org.digital.dairy.async.producer;

import org.digital.dairy.async.vo.AsyncVO;
import org.digital.dairy.async.vo.ConformationMailVO;
import org.digital.dairy.async.vo.ResentConformationMailVO;
import org.digital.dairy.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Created by Pradeep.P on 04-05-2015.
 */
@Service(value="registrationConformationMailProducer")
public class RegistrationConformationMailProducer {
    public static final String EMAIL_CONFROMATION = "CONFORMATION.data.v1";

    @Autowired
    AmqpTemplate asyncConfromationMailProducer;
    public void sendConformstionMail(User user, Locale Locale, String appUrl){
        ConformationMailVO conformationMailVO = new ConformationMailVO(user.getEmail(),Locale,appUrl);
        produceAsyncTask(EMAIL_CONFROMATION,conformationMailVO);

    }
    public void resendConformstionMail(String  email,String token){
        ResentConformationMailVO resentConformationMailVO = new ResentConformationMailVO(email,token);
        produceAsyncTask(EMAIL_CONFROMATION,resentConformationMailVO);

    }
    private void produceAsyncTask(String routingKey,AsyncVO task){
        try {
            System.out.println("produceAsyncTask");
            /*asyncConfromationMailProducer.convertAndSend(routingKey,"this is testing");*/
            asyncConfromationMailProducer.convertAndSend(routingKey,task);
            System.out.println("this is testing");
        }catch(org.springframework.amqp.AmqpConnectException amqpEx){
            /*logger.error("Error while posting message to Rabbit MQ for task " + task.toString() ,amqpEx);*/
            System.out.println("Error while posting message to Rabbit MQ for task " + task.toString() +amqpEx);
        }catch(Exception amqpEx){
            /*logger.error("Error while posting message to Rabbit MQ for task " + task.toString() ,amqpEx);*/
            System.out.println("Error while posting message to Rabbit MQ for task " + task.toString() +amqpEx);
        }
    }
}