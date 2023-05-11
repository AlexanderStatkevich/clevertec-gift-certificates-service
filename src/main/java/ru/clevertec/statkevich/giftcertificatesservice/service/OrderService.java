package ru.clevertec.statkevich.giftcertificatesservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Order;
import ru.clevertec.statkevich.giftcertificatesservice.repository.OrderRepository;

import java.util.List;

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
    public List<Order> findByUser(Long customerId) {
        return orderRepository.findByCustomer_Id(customerId);
    }
}
