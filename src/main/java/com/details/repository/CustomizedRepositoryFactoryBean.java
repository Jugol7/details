package com.details.repository;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.io.Serializable;

/***
 * @author zlp
 * @date 14:31 2020/3/16
 */
public class CustomizedRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable> extends MongoRepositoryFactoryBean<T,S,ID > {

    private CustomizedRepositoryFactory customizedRepositoryFactory;

    public CustomizedRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    public RepositoryFactorySupport getFactoryInstance(MongoOperations operations) {
        return customizedRepositoryFactory = new CustomizedRepositoryFactory(operations);
    }

    public CustomizedRepositoryFactory getCustomizedRepositoryFactory(){
        return this.customizedRepositoryFactory;
    }

}

