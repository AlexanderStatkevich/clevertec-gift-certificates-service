package ru.clevertec.statkevich.giftcertificatesservice.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.clevertec.statkevich.giftcertificatesservice.domain.GiftCertificate;
import ru.clevertec.statkevich.giftcertificatesservice.filter.GiftCertificateFilter;
import ru.clevertec.statkevich.giftcertificatesservice.filter.SortOrder;
import ru.clevertec.statkevich.giftcertificatesservice.filter.SortOrderData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Repository
public class GiftCertificateCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public GiftCertificateCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<GiftCertificate> findAllFiltered(Pageable pageable, GiftCertificateFilter filter) {

        CriteriaQuery<GiftCertificate> query = criteriaBuilder.createQuery(GiftCertificate.class);
        Root<GiftCertificate> root = query.from(GiftCertificate.class);
        Predicate predicate = getPredicate(filter, root);
        query.where(predicate);

        setOrder(filter, query, root);

        TypedQuery<GiftCertificate> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        long giftCertificatesCount = getEmployeesCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, giftCertificatesCount);
    }

    private Predicate getPredicate(GiftCertificateFilter filter,
                                   Root<GiftCertificate> root) {

        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(filter.tag())) {
            predicates.add(
                    criteriaBuilder.like(root.get("tag"),
                            "%" + filter.tag() + "%")
            );
        }
        if (Objects.nonNull(filter.name())) {
            predicates.add(
                    criteriaBuilder.like(root.get("name"),
                            "%" + filter.name() + "%")
            );
        }
        if (Objects.nonNull(filter.description())) {
            predicates.add(
                    criteriaBuilder.like(root.get("description"),
                            "%" + filter.description() + "%")
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


    private void setOrder(GiftCertificateFilter filter,
                          CriteriaQuery<GiftCertificate> query,
                          Root<GiftCertificate> root) {

        List<SortOrderData> sortOrderData = filter.sortOrderData();
        if (sortOrderData != null && !sortOrderData.isEmpty()) {
            List<Order> orderList = sortOrderData.stream()
                    .map(orderData -> addOrder(criteriaBuilder, root, orderData))
                    .collect(Collectors.toList());
            query.orderBy(orderList);
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

    private long getEmployeesCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<GiftCertificate> countRoot = countQuery.from(GiftCertificate.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
