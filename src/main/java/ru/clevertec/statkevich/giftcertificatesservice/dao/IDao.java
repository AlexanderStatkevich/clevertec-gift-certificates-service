package ru.clevertec.statkevich.giftcertificatesservice.dao;

import java.util.List;

public interface IDao<T> {

    Long create(T entity);

    T findById(Long id);

    List<T> findAll();

    void update(T entity);

    void delete(Long id);
}
