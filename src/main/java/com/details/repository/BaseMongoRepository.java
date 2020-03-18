package com.details.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/***
 * @author zlp
 * @date 14:30 2020/3/16
 */
//@NoRepositoryBean
public interface BaseMongoRepository<T,ID extends Serializable> extends MongoRepository<T,ID> {
    <S extends T> Page<S> findAll(final Example<S> example, final Query query, Pageable pageable);
}
