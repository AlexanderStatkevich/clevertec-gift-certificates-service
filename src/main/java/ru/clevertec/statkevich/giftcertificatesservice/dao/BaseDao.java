package ru.clevertec.statkevich.giftcertificatesservice.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.clevertec.statkevich.giftcertificatesservice.domain.BaseEntity;

import java.util.List;

@RequiredArgsConstructor
public class BaseDao<T extends BaseEntity> implements IDao<T> {

    private final SessionFactory sessionFactory;

    private final Class<T> clazz;

    @Override
    public Long create(T entity) {
        try (Session session = getCurrentSession()) {
            session.getTransaction().begin();
            session.persist(entity);
            session.getTransaction().commit();
            return entity.getId();
        }
    }

    @Override
    public T findById(Long id) {
        try (Session session = getCurrentSession()) {
            session.getTransaction().begin();
            T entity = session.find(clazz, id);
            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = getCurrentSession()) {
            session.getTransaction().begin();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
            Root<T> root = query.from(clazz);
            CriteriaQuery<T> select = query.select(root);
            List<T> resultList = session.createQuery(select).getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

    @Override
    public void update(T entity) {
        try (Session session = getCurrentSession()) {
            session.getTransaction().begin();
            session.merge(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = getCurrentSession()) {
            session.getTransaction().begin();
            T entity = session.find(clazz, id);
            session.remove(entity);
            session.getTransaction().commit();
        }
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
