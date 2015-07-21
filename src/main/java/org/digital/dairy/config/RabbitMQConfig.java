package org.digital.dairy.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Map;

/**
 * Created by Pradeep.P on 30-04-2015.
 */

@Configuration
@ImportResource({"classpath*:amqp/producer-context.xml","classpath*:amqp/consumer-context.xml"})
public class RabbitMQConfig {
      /* first following method creates a common rabbit connection factory with specified parameters */
    @Bean(name="connectionFactory")
    public ConnectionFactory connectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();

            connectionFactory.setHost("localhost");
            connectionFactory.setUsername("guest");
            connectionFactory.setPassword("guest");
            connectionFactory.setPort(5672);

        return connectionFactory;
    }
     /* obtain admin rights to create the an exchange */
    @Bean
    public AmqpAdmin amqpAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        return rabbitAdmin ;
    }
}
