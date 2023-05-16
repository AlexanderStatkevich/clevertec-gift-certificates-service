package ru.clevertec.statkevich.giftcertificatesservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Order;
import ru.clevertec.statkevich.giftcertificatesservice.repository.OrderRepository;

@RequiredArgsConstructor
@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public Long create(Order order) {
        return orderRepository.save(order).getId();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<Order> findByUser(Long customerId, Pageable pageable) {
        return orderRepository.findByCustomer_Id(customerId, pageable);
    }
}
