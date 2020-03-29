package com.example.config;


import com.example.domain.User;
import com.example.service.UserAuditableService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableAutoConfiguration
public class JpaConfig {

    @Bean
    public AuditorAware<User> auditorProvider() {
        return new AuditorAwareImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    private static class AuditorAwareImpl implements AuditorAware<User> {

        @Autowired
        private UserAuditableService userAuditableService;

        /**
         * This method is used to obtain the name of the user who
         * authenticated with spring security, this authentication has the user
         * who authenticated internally.
         *
         * @return username
         */
    	@Override
    	public Optional<User> getCurrentAuditor() {
            return this.userAuditableService.getCurrentUser();
    	}

    }

}


