package ru.clevertec.statkevich.giftcertificatesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomer_Id(Long customerId);
}
