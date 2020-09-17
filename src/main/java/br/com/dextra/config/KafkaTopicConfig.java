package br.com.dextra.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
    
    @Bean
    public NewTopic topic1() {
         return new NewTopic("usuarioTopic", 1, (short) 1);
    }

}
