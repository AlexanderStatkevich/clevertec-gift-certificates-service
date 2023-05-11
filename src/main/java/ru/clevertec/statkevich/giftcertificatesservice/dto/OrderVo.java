package ru.clevertec.statkevich.giftcertificatesservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderVo(

        LocalDateTime purchaseDateTime,
        BigDecimal price
) {
}
