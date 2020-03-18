package com.details.config;

import com.details.repository.SpringSecurityAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/***
 * @author zlp
 * @date 14:22 2020/3/16
 */
//@Configuration
//@EnableMongoRepositories()
public class MongoAuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new SpringSecurityAuditorAware();
    }

}
