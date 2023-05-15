package ru.clevertec.statkevich.giftcertificatesservice.integration;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.clevertec.statkevich.giftcertificatesservice.domain.GiftCertificate;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Order;
import ru.clevertec.statkevich.giftcertificatesservice.service.OrderService;
import ru.clevertec.statkevich.giftcertificatesservice.utils.TestData;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceTest extends BaseIntegrationTest {

    private final OrderService orderService;


    @Test
    void integrationOrderServiceTest() throws IOException {
        long existedId = 3L;
        Order orderByExistedId = orderService.findById(existedId);
        assertThat(orderByExistedId).isNotNull();
        long notYetAddedOrderId = 5L;
        Assertions.assertThrows(EntityNotFoundException.class, () -> orderService.findById(notYetAddedOrderId));
        LocalDateTime now = LocalDateTime.now();
        orderService.create(Order.builder()
                .purchaseDateTime(now)
                .certificate(GiftCertificate.builder().id(3L).name("test").description("smth").price(BigDecimal.ONE).duration(25).build())
                .customer(TestData.buildUser())
                .price(BigDecimal.ONE).build());
        long addedOrderId = 5L;
        Order orderAfterAddition = orderService.findById(addedOrderId);
        assertThat(orderAfterAddition).isNotNull();
    }
}
