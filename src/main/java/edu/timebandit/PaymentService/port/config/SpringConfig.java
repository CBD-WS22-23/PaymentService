package edu.timebandit.PaymentService.port.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    @Qualifier("ModelMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
