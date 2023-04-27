package ru.clevertec.statkevich.giftcertificatesservice.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.clevertec.statkevich.giftcertificatesservice.domain.GiftCertificate;
import ru.clevertec.statkevich.giftcertificatesservice.filter.GiftCertificateFilter;
import ru.clevertec.statkevich.giftcertificatesservice.filter.SortOrder;
import ru.clevertec.statkevich.giftcertificatesservice.filter.SortOrderData;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class GiftCertificateDao extends BaseDao<GiftCertificate> implements IGiftCertificateDao {

    public GiftCertificateDao(SessionFactory sessionFactory, Class<GiftCertificate> clazz) {
        super(sessionFactory, clazz);
    }

    @Override
    public List<GiftCertificate> findAll(GiftCertificateFilter filter) {
        try (Session session = getCurrentSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<GiftCertificate> query = criteriaBuilder.createQuery(GiftCertificate.class);
            Root<GiftCertificate> root = query.from(GiftCertificate.class);
            if (null != filter.tag()) {
                query.where(criteriaBuilder.equal(root.get("tag"), filter.tag()));
            }
            if (null != filter.name()) {
                query.where(criteriaBuilder.like(root.get("name"), filter.name()));
            }
            if (null != filter.description()) {
                query.where(criteriaBuilder.like(root.get("description"), filter.description()));
            }

            List<SortOrderData> sortOrderData = filter.sortOrderData();
            if (sortOrderData != null && !sortOrderData.isEmpty()) {
                List<Order> orderList = sortOrderData.stream()
                        .map(orderData -> addOrder(criteriaBuilder, root, orderData))
                        .collect(Collectors.toList());
                query.orderBy(orderList);
            }
            CriteriaQuery<GiftCertificate> select = query.select(root);
            return session.createQuery(select).getResultList();
        }
    }

    private Order addOrder(CriteriaBuilder criteriaBuilder, Root<GiftCertificate> root, SortOrderData order) {
        Path<Object> path = root.get(order.field());
        if (order.sortOrder() == SortOrder.DESC) {
            return criteriaBuilder.desc(path);
        } else {
            return criteriaBuilder.asc(path);
        }
    }
}
