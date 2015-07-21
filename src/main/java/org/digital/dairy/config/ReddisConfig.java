package org.digital.dairy.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by Pradeep.P on 20-06-2015.
 */
@Configuration
@EnableCaching
public class ReddisConfig {

    public static final String REDIS_ADDRESS_PROP_NAME ="PARAM1";
    public static final long DEFAULT_EXPIRATION_TIME = 86400l;

    @Bean
    JedisConnectionFactory jedisConnectionFactory(){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        String address = System.getProperty(REDIS_ADDRESS_PROP_NAME);
        if(!StringUtils.isEmpty(address)){
            String[] addressProps = address.split(":");
            String cacheHostName = addressProps[0];
            String port = addressProps[1];
            factory.setHostName(cacheHostName);			//default is localhost
            factory.setPort(Integer.parseInt(port));	//default port is 6379
          //  factory.setDatabase(1);
            if (addressProps.length>2) {
                Integer dbIndex  = new Integer(addressProps[2]); //default dbIndex 0
                if (dbIndex!=null){
                    factory.setDatabase(dbIndex);
                }
            }
        }
        factory.setUsePool(true);
        return factory;
    }

    @Bean
    StringRedisTemplate stringRedisTemplate() {
    //By using stringRedisTemplate key and value store like a stirng forma i.e;user read format
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(jedisConnectionFactory());
        return stringRedisTemplate;
    }

    @Bean
    RedisTemplate<String, Object> redisTemplate() {
        //By using redisTemplate key and value store like a  binary values (byte arrays).
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean
    CacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate());
        cacheManager.setDefaultExpiration(DEFAULT_EXPIRATION_TIME);
        return cacheManager;
    }

}