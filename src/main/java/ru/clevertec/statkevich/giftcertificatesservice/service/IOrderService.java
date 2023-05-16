package ru.clevertec.statkevich.giftcertificatesservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Order;

public interface IOrderService {

    Long create(Order order);

    Order findById(Long id);

    Page<Order> findByUser(Long customerId, Pageable pageable);
}
