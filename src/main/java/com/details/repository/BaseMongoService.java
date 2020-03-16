package com.details.repository;

import com.details.entity.BaseMongoEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

import java.io.Serializable;
import java.util.List;

/***
 * @author zlp
 * @date 14:35 2020/3/16
 */
public interface BaseMongoService<T extends BaseMongoEntity, ID extends Serializable, R extends BaseMongoRepository> {
    Page<T> findAll(final Example<T> example, final Query query, Pageable pageable);

    List<T> save(Iterable<T> var1);

    List<T> findAll();

    List<T> findAll(Sort var1);

    T insert(T var1);

    List<T> insert(Iterable<T> var1);

    List<T> findAll(Example<T> var1);

    List<T> findAll(Example<T> var1, Sort var2);

    <S extends T> Page<S> findAll(Example<S> var1, Pageable var2);

    T save(T var1);

    T findOne(ID var1);

    boolean exists(ID var1);

    Iterable<T> findAll(Iterable<ID> var1);

    long count();

    void delete(ID var1);

    void delete(T var1);

    void delete(Iterable<? extends T> var1);

    void deleteAll();
}
