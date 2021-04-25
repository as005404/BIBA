package com.foxrider.service.test_config;

import com.foxrider.service.ShiftService;
import com.foxrider.service.ShiftServiceImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.foxrider.dao")
@EntityScan("com.foxrider.entity")
public class Config {

    @Bean
    ShiftService shiftService() {
        return new ShiftServiceImpl();
    }

}
