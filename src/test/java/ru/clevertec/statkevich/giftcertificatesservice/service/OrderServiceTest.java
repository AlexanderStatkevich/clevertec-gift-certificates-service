package ru.clevertec.statkevich.giftcertificatesservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Order;
import ru.clevertec.statkevich.giftcertificatesservice.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void checkCreateReturnCorrectId() {
        Order order = Order.builder().id(1L).price(BigDecimal.ONE).build();
        when(orderRepository.save(order)).thenReturn(order);
        Long actual = orderService.create(order);
        assertThat(actual).isEqualTo(1L);
    }

    @Test
    void checkFindByIdMethodReturningCorrectEntity() {
        Order expected = Order.builder().price(BigDecimal.ONE).build();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(expected));
        Order actual = orderService.findById(1L);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkFindByUserReturnCorrectList() {
        Order order = Order.builder().price(BigDecimal.ONE).build();
        List<Order> expected = List.of(order);
        when(orderRepository.findByCustomer_Id(1L)).thenReturn(expected);
        List<Order> actual = orderService.findByUser(1L);
        assertThat(actual).isEqualTo(expected);
    }
}
