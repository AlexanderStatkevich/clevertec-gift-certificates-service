package ru.clevertec.statkevich.giftcertificatesservice.service;

import ru.clevertec.statkevich.giftcertificatesservice.domain.Order;

import java.util.List;

public interface IOrderService {

    Long create(Order order);

    Order findById(Long id);

    List<Order> findByUser(Long customerId);
}
