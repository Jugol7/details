package com.details.repository;

import com.details.entity.BaseMongoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.io.Serializable;
import java.util.List;

/***
 * @author zlp
 * @date 14:33 2020/3/16
 */
public class BaseMongoServiceImpl<T extends BaseMongoEntity, ID extends Serializable, R extends BaseMongoRepository> implements BaseMongoService<T, ID, R> {

    private R repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public R getRepository() {
        return this.repository;
    }

    @Autowired
    public void setRepository(R repository) {
        this.repository = repository;
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    @Override
    public Page<T> findAll(final Example<T> example, final Query query, Pageable pageable) {
        return getRepository().findAll(example, query, pageable);
    }

    @Override
    public List<T> save(Iterable<T> var1) {
        return getRepository().save(var1);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public List<T> findAll(Sort var1) {
        return getRepository().findAll();
    }

    @Override
    public T insert(T var1) {
        return (T) getRepository().insert(var1);
    }

    @Override
    public List<T> insert(Iterable<T> var1) {
        return getRepository().insert(var1);
    }

    @Override
    public List<T> findAll(Example<T> var1) {
        return getRepository().findAll(var1);
    }

    @Override
    public List<T> findAll(Example<T> var1, Sort var2) {
        return getRepository().findAll(var1, var2);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> var1, Pageable var2) {
        return getRepository().findAll(var1,var2);
    }

    @Override
    public T save(T var1) {
        return (T) getRepository().save(var1);
    }

    @Override
    public T findOne(ID var1) {
        return (T) getRepository().findOne(var1);
    }

    @Override
    public boolean exists(ID var1){
        return getRepository().exists(var1);
    }

    @Override
    public Iterable<T> findAll(Iterable<ID> var1){
        return getRepository().findAll(var1);
    }

    @Override
    public long count(){
        return getRepository().count();
    }

    @Override
    public void delete(ID var1){
        getRepository().delete(var1);
    }

    @Override
    public void delete(T var1){
        getRepository().delete(var1);
    }

    @Override
    public void delete(Iterable<? extends T> var1){
        getRepository().delete(var1);
    }

    @Override
    public void deleteAll(){
        getRepository().deleteAll();
    }

}


