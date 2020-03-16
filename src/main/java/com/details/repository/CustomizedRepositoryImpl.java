package com.details.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;


/***
 * @author zlp
 * @date 14:29 2020/3/16
 */
public class CustomizedRepositoryImpl<T, ID extends Serializable> extends SimpleMongoRepository<T, ID> implements BaseMongoRepository<T, ID> {
    private final MongoOperations mongoOperations;
    private final MongoEntityInformation<T, ID> entityInformation;

    public CustomizedRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
        this.entityInformation = metadata;
        this.mongoOperations = mongoOperations;
    }

    @Override
    public <S extends T> Page<S> findAll(final Example<S> example, final Query query, Pageable pageable) {
        Assert.notNull(example, "Sample must not be null!");
        query.addCriteria((new Criteria()).alike(example)).with(pageable);
        List<S> list = this.mongoOperations.find(query, example.getProbeType(), this.entityInformation.getCollectionName());
        return PageableExecutionUtils.getPage(list, pageable, () -> mongoOperations.count(query, example.getProbeType(),entityInformation.getCollectionName()));
    }
}

