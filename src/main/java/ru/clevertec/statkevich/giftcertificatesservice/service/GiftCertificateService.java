package ru.clevertec.statkevich.giftcertificatesservice.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.statkevich.giftcertificatesservice.domain.GiftCertificate;
import ru.clevertec.statkevich.giftcertificatesservice.dto.GiftCertificateCreateUpdateDto;
import ru.clevertec.statkevich.giftcertificatesservice.filter.GiftCertificateFilter;
import ru.clevertec.statkevich.giftcertificatesservice.filter.SortOrder;
import ru.clevertec.statkevich.giftcertificatesservice.filter.SortOrderData;
import ru.clevertec.statkevich.giftcertificatesservice.mapper.GiftCertificateMapper;
import ru.clevertec.statkevich.giftcertificatesservice.repository.GiftCertificateRepository;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Described class bind storage and output of application.
 * As well as uses CRUD operations for this purpose.
 */
@RequiredArgsConstructor
@Service
public class GiftCertificateService implements IGiftCertificateService {

    private final GiftCertificateRepository giftCertificateRepository;
    @PersistenceContext
    private EntityManager entityManager;

    private final GiftCertificateMapper giftCertificateMapper;

    @Override
    public Long create(GiftCertificate giftCertificate) {
        return giftCertificateRepository.save(giftCertificate).getId();
    }

    @Override
    public GiftCertificate findById(Long id) {
        return giftCertificateRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public List<GiftCertificate> findAll(GiftCertificateFilter filter) {
        return filter != null
                ? findAllFiltered(filter)
                : giftCertificateRepository.findAll();
    }

    private List<GiftCertificate> findAllFiltered(GiftCertificateFilter filter) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
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
        return entityManager.createQuery(select).getResultList();
    }

    private Order addOrder(CriteriaBuilder criteriaBuilder, Root<GiftCertificate> root, SortOrderData order) {
        Path<Object> path = root.get(order.field());
        if (order.sortOrder() == SortOrder.DESC) {
            return criteriaBuilder.desc(path);
        } else {
            return criteriaBuilder.asc(path);
        }
    }

    @Transactional
    @Override
    public void update(Long id, GiftCertificateCreateUpdateDto giftCertificateCreateUpdateDto) {
        GiftCertificate giftCertificate = giftCertificateRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        giftCertificateMapper.map(giftCertificateCreateUpdateDto, giftCertificate);
    }

    @Override
    public void delete(Long id) {
        giftCertificateRepository.deleteById(id);
    }
}
