package com.details.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/***
 * @author zlp
 * @date 14:27 2020/3/16
 */
@Configuration
@EnableMongoRepositories(repositoryBaseClass = com.details.repository.CustomizedRepositoryImpl.class,repositoryFactoryBeanClass = com.details.repository.CustomizedRepositoryFactoryBean.class,basePackages = "com.details.repository")
public class MongoConfig {
}
