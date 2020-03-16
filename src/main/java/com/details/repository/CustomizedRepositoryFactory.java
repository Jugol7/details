package com.details.repository;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.QueryDslMongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.QueryDslUtils;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.util.Assert;

import java.io.Serializable;

public class CustomizedRepositoryFactory extends MongoRepositoryFactory {

    private final MongoOperations operations;

    public CustomizedRepositoryFactory(MongoOperations mongoOperations) {
        super(mongoOperations);
        Assert.notNull(mongoOperations, "MongoOperations must not be null!");
        this.operations = mongoOperations;
    }

    @Override
    protected Object getTargetRepository(RepositoryInformation information) {
        Class<?> repositoryInterface = information.getRepositoryInterface();
        MongoEntityInformation<?, Serializable> entityInformation = getEntityInformation(information.getDomainType());
        if (isQueryDslRepository(repositoryInterface)) {
            return new QueryDslMongoRepository(entityInformation, operations);
        } else {
            return new CustomizedRepositoryImpl<>((MongoEntityInformation) entityInformation, operations);
        }
    }

    private static boolean isQueryDslRepository(Class<?> repositoryInterface) {
        return QueryDslUtils.QUERY_DSL_PRESENT && QueryDslPredicateExecutor.class.isAssignableFrom(repositoryInterface);
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return isQueryDslRepository(metadata.getRepositoryInterface()) ? QueryDslMongoRepository.class : BaseMongoServiceImpl.class;
    }
}
